package cn.iocoder.yudao.module.cumu.service.orderinfo;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.cumu.controller.admin.orderinfo.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.orderinfo.OrderInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.cumu.convert.orderinfo.OrderInfoConvert;
import cn.iocoder.yudao.module.cumu.dal.mysql.orderinfo.OrderInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cumu.enums.ErrorCodeConstants.*;

/**
 * 畜牧订单详情 Service 实现类
 *
 * @author 管理员
 */
@Service
@Validated
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public Long createOrderInfo(OrderInfoCreateReqVO createReqVO) {
        // 插入
        OrderInfoDO orderInfo = OrderInfoConvert.INSTANCE.convert(createReqVO);
        orderInfoMapper.insert(orderInfo);
        // 返回
        return orderInfo.getId();
    }

    @Override
    public void updateOrderInfo(OrderInfoUpdateReqVO updateReqVO) {
        // 校验存在
        validateOrderInfoExists(updateReqVO.getId());
        // 更新
        OrderInfoDO updateObj = OrderInfoConvert.INSTANCE.convert(updateReqVO);
        orderInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderInfo(Long id) {
        // 校验存在
        validateOrderInfoExists(id);
        // 删除
        orderInfoMapper.deleteById(id);
    }

    private void validateOrderInfoExists(Long id) {
        if (orderInfoMapper.selectById(id) == null) {
            throw exception(ORDER_INFO_NOT_EXISTS);
        }
    }

    @Override
    public OrderInfoDO getOrderInfo(Long id) {
        return orderInfoMapper.selectById(id);
    }

    @Override
    public List<OrderInfoDO> getOrderInfoList(Collection<Long> ids) {
        return orderInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OrderInfoDO> getOrderInfoPage(OrderInfoPageReqVO pageReqVO) {
        return orderInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OrderInfoDO> getOrderInfoList(OrderInfoExportReqVO exportReqVO) {
        return orderInfoMapper.selectList(exportReqVO);
    }

}
