package cn.iocoder.yudao.module.cumu.dal.mysql.order;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cumu.dal.dataobject.order.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cumu.controller.admin.order.vo.*;

/**
 * 畜牧订单交易 Mapper
 *
 * @author 管理员
 */
@Mapper
public interface OrderMapper extends BaseMapperX<OrderDO> {

    default PageResult<OrderDO> selectPage(OrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getBuyId, reqVO.getBuyId())
                .eqIfPresent(OrderDO::getSaleId, reqVO.getSaleId())
                .eqIfPresent(OrderDO::getOrderNum, reqVO.getOrderNum())
                .eqIfPresent(OrderDO::getTokenNum, reqVO.getTokenNum())
                .eqIfPresent(OrderDO::getOrderPrice, reqVO.getOrderPrice())
                .eqIfPresent(OrderDO::getOrderCost, reqVO.getOrderCost())
                .betweenIfPresent(OrderDO::getOrderTime, reqVO.getOrderTime())
                .eqIfPresent(OrderDO::getOrderStatus, reqVO.getOrderStatus())
                .eqIfPresent(OrderDO::getRember, reqVO.getRember())
                .betweenIfPresent(OrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderDO::getId));
    }

    default List<OrderDO> selectList(OrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getBuyId, reqVO.getBuyId())
                .eqIfPresent(OrderDO::getSaleId, reqVO.getSaleId())
                .eqIfPresent(OrderDO::getOrderNum, reqVO.getOrderNum())
                .eqIfPresent(OrderDO::getTokenNum, reqVO.getTokenNum())
                .eqIfPresent(OrderDO::getOrderPrice, reqVO.getOrderPrice())
                .eqIfPresent(OrderDO::getOrderCost, reqVO.getOrderCost())
                .betweenIfPresent(OrderDO::getOrderTime, reqVO.getOrderTime())
                .eqIfPresent(OrderDO::getOrderStatus, reqVO.getOrderStatus())
                .eqIfPresent(OrderDO::getRember, reqVO.getRember())
                .betweenIfPresent(OrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderDO::getId));
    }

}
