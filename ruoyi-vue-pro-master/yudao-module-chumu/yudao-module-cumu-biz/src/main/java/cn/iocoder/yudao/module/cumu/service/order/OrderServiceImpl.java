package cn.iocoder.yudao.module.cumu.service.order;

import cn.hutool.core.util.IdUtil;
import cn.iocoder.yudao.framework.dict.core.util.DictFrameworkUtils;
import cn.iocoder.yudao.module.cumu.controller.admin.joiner.vo.JoinerResponsVO;
import cn.iocoder.yudao.module.cumu.controller.admin.lefter.vo.LefterResponsVO;
import cn.iocoder.yudao.module.cumu.controller.admin.orderinfo.vo.OrderInfoExportReqVO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.custom.CustomDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo.JoinInfoDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.lefter.LefterDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.leftinfo.LeftInfoDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.orderinfo.OrderInfoDO;
import cn.iocoder.yudao.module.cumu.dal.mysql.lefter.LefterMapper;
import cn.iocoder.yudao.module.cumu.dal.mysql.leftinfo.LeftInfoMapper;
import cn.iocoder.yudao.module.cumu.dal.mysql.orderinfo.OrderInfoMapper;
import cn.iocoder.yudao.module.cumu.service.custom.CustomService;
import cn.iocoder.yudao.module.cumu.service.joiner.JoinerService;
import cn.iocoder.yudao.module.cumu.service.lefter.LefterService;
import cn.iocoder.yudao.module.cumu.service.leftinfo.LeftInfoService;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.cumu.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.order.OrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.cumu.convert.order.OrderConvert;
import cn.iocoder.yudao.module.cumu.dal.mysql.order.OrderMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cumu.enums.ErrorCodeConstants.*;

/**
 * 畜牧订单交易 Service 实现类
 *
 * @author 管理员
 */
@Service
@Validated
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private JoinerService joinerService;
    @Resource
    private LefterService lefterService;
    @Resource
    private LefterMapper lefterMapper;
    @Resource
    private LeftInfoService leftInfoService;
    @Resource
    private LeftInfoMapper leftInfoMapper;
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private CustomService customService;
    @Resource
    private AdminUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(OrderCreateReqVO createReqVO) {


        //获取买卖方的离场信息
        if (StringUtils.isEmpty(createReqVO.getSaleJoinId())) {
            throw exception(SALE_ERROR);
        }
        if (StringUtils.isEmpty(createReqVO.getBuyJoinId())) {
            throw exception(BUY_ERROR);
        }
        LefterResponsVO saler = lefterService.getLefterByJoinId(createReqVO.getSaleJoinId());
        LefterResponsVO buyer = lefterService.getLefterByJoinId(createReqVO.getBuyJoinId());
        if (null == saler) {
            throw exception(SALE_ERROR);
        }
        if (null == buyer) {
            throw exception(BUY_ERROR);
        }
        //校验
        check(createReqVO, saler, buyer);
        List<OrderInfoDO> orderInfoDOS = createReqVO.getOrderInfoDOS();
        //根据订单信息对买卖方信息更新
        List<LeftInfoDO> salerList = saler.getLeftInfoDOList();
        List<LeftInfoDO> buyerList = buyer.getLeftInfoDOList();
        Integer saleCountNum = 0;
        Integer buyCountNum = 0;
        String saleTypeString = "";
        String buyTypeString = "";
        for (int i = 0; i < salerList.size(); i++) {
            LeftInfoDO salerInfo = salerList.get(i);
            LeftInfoDO buyerInfo = buyerList.get(i);
            String type = salerInfo.getType();
            OrderInfoDO orderInfoDO = findObjectWithSameType(orderInfoDOS, type);
            if (salerInfo.getNum() - orderInfoDO.getNum() < 0) {
                throw exception(ORDER_ERROR);
            }
            Integer saleNum = salerInfo.getNum() - orderInfoDO.getNum();
            Integer buyNum = buyerInfo.getNum() + orderInfoDO.getNum();

            salerInfo.setNum(saleNum);
            buyerInfo.setNum(buyNum);
            leftInfoMapper.updateById(salerInfo);
            leftInfoMapper.updateById(buyerInfo);
            String label = DictFrameworkUtils.getDictDataLabel("cumu_type", orderInfoDO.getType());
            if (orderInfoDO.getNum() != 0) {
                saleTypeString += label + ":" + saleNum + ";";
                buyTypeString += label + ":" + buyNum + ";";
            }
            saleCountNum += saleNum;
            buyCountNum += buyNum;
        }
        LefterDO sale = new LefterDO();
        sale.setId(saler.getId());
        sale.setTypeString(saleTypeString);
        sale.setNumber(saleCountNum);
        LefterDO buy = new LefterDO();
        buy.setId(buyer.getId());
        buy.setTypeString(buyTypeString);
        buy.setNumber(buyCountNum);
        lefterMapper.updateById(sale);
        lefterMapper.updateById(buy);
        //更新离场登记表

        // 插入
        OrderDO order = OrderConvert.INSTANCE.convert(createReqVO);
        Long orderId = IdUtil.getSnowflakeNextId();
        order.setId(orderId);
        order.setBuyJoinId(buyer.getId());
        order.setSaleJoinId(saler.getId());
        orderMapper.insert(order);
        // 返回
        List<OrderInfoDO> collect1 = orderInfoDOS.stream().
                map(item -> {
                    OrderInfoDO orderInfoDO = new OrderInfoDO();
                    orderInfoDO.setId(IdUtil.getSnowflakeNextId());
                    orderInfoDO.setOrderId(orderId);
                    orderInfoDO.setType(item.getType());
                    orderInfoDO.setNum(item.getNum());
                    return orderInfoDO;
                }).collect(Collectors.toList());
        orderInfoMapper.insertBatch(collect1);
        return order.getId();
    }

    private void check(OrderCreateReqVO createReqVO, LefterResponsVO saler, LefterResponsVO buyer) {
        String errMsg = "";
        Long tokenNum = createReqVO.getTokenNum();
        boolean b = tokenNum <= saler.getCustomDO().getTokenEndNo();
        boolean b1 = tokenNum >= saler.getCustomDO().getTokenStartNo();
        boolean b2 = tokenNum <= buyer.getCustomDO().getTokenStartNo();
        boolean b3 = tokenNum >= buyer.getCustomDO().getTokenStartNo();
        if ((b && b1) || (b2 && b3)) {
        } else {
            throw exception(INVALID_ERROR);
        }
    }

    // 在集合中查找具有相同 type 值的对象
    static OrderInfoDO findObjectWithSameType(List<OrderInfoDO> orderInfoDOS, String type) {
        for (OrderInfoDO obj : orderInfoDOS) {
            if (obj.getType().equals(type)) {
                return obj;
            }
        }
        return null; // 如果找不到匹配项，返回 null
    }

    @Override
    public void updateOrder(OrderUpdateReqVO updateReqVO) {
        // 校验存在
        validateOrderExists(updateReqVO.getId());
        // 更新
        OrderDO updateObj = OrderConvert.INSTANCE.convert(updateReqVO);
        orderMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrder(Long id) {
        // 校验存在
        validateOrderExists(id);
        // 删除
        orderMapper.deleteById(id);
    }

    private void validateOrderExists(Long id) {
        if (orderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    public OrderDO getOrder(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public List<OrderDO> getOrderList(Collection<Long> ids) {
        return orderMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OderTableRespVO> getOrderPage(OrderPageReqVO pageReqVO) {
        PageResult<OrderDO> pageResult = orderMapper.selectPage(pageReqVO);
        List<OrderDO> orderDOs = pageResult.getList();
        List<Long> buyIds = orderDOs.stream().map(item -> item.getBuyId()).collect(Collectors.toList());
        List<Long> saleIds = orderDOs.stream().map(item -> item.getSaleId()).collect(Collectors.toList());
        List<CustomDO> buyList = customService.getCustomList(buyIds);
        List<CustomDO> saleList = customService.getCustomList(saleIds);
        //转换操作人为中文
        Collection<Long> userIds = pageResult.getList().stream().map(item -> Long.valueOf(item.getCreator())).collect(Collectors.toList());
        List<AdminUserDO> userList = userService.getUserList(userIds);
        List<Long> ids = orderDOs.stream().map(orderDO -> orderDO.getId()).collect(Collectors.toList());
//        OrderInfoExportReqVO orderInfoExportReqVO = new OrderInfoExportReqVO();
//        orderInfoExportReqVO.setOrderId()
//        orderInfoMapper.selectList((OrderInfoExportReqVO) ids);
        List<OderTableRespVO> list = orderDOs.stream().map(orderDO -> {
            OderTableRespVO oderTableRespVO = new OderTableRespVO();
            BeanUtils.copyProperties(orderDO, oderTableRespVO);
            buyList.stream().filter(buy -> buy.getId().equals(orderDO.getBuyId()))
                    .findFirst()
                    .ifPresent(buy -> oderTableRespVO.setBuyDO(buy));
            saleList.stream().filter(sale -> sale.getId().equals(orderDO.getSaleId()))
                    .findFirst()
                    .ifPresent(sale -> oderTableRespVO.setSaleDO(sale));
            userList.stream().filter(user -> user.getId().toString().equals(orderDO.getCreator()))
                    .findFirst()
                    .ifPresent(user -> oderTableRespVO.setNikename(user.getNickname()));
            OrderInfoExportReqVO orderInfoExportReqVO = new OrderInfoExportReqVO();
            OrderInfoExportReqVO reqVO = orderInfoExportReqVO.setOrderId(orderDO.getId());
            List<OrderInfoDO> infoDOList = orderInfoMapper.selectList(reqVO);
            oderTableRespVO.setOrderInfoDOS(infoDOList);
            String typeString = "";
            for (OrderInfoDO orderInfoDO : infoDOList) {
                String label = DictFrameworkUtils.getDictDataLabel("cumu_type", orderInfoDO.getType());
                if (orderInfoDO.getNum() != 0) {
                    typeString += label + ":" + orderInfoDO.getNum() + ";";
                }
            }
            oderTableRespVO.setTypeString(typeString);
            return oderTableRespVO;
        }).collect(Collectors.toList());
        PageResult<OderTableRespVO> jpageResult = new PageResult<>(list, pageResult.getTotal());
        return jpageResult;
    }

    @Override
    public List<OrderDO> getOrderList(OrderExportReqVO exportReqVO) {
        return orderMapper.selectList(exportReqVO);
    }

}
