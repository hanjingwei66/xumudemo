package cn.iocoder.yudao.module.cumu.dal.mysql.orderinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cumu.dal.dataobject.orderinfo.OrderInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cumu.controller.admin.orderinfo.vo.*;

/**
 * 畜牧订单详情 Mapper
 *
 * @author 管理员
 */
@Mapper
public interface OrderInfoMapper extends BaseMapperX<OrderInfoDO> {

    default PageResult<OrderInfoDO> selectPage(OrderInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderInfoDO>()
                .eqIfPresent(OrderInfoDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(OrderInfoDO::getType, reqVO.getType())
                .eqIfPresent(OrderInfoDO::getNum, reqVO.getNum())
                .orderByDesc(OrderInfoDO::getId));
    }

    default List<OrderInfoDO> selectList(OrderInfoExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderInfoDO>()
                .eqIfPresent(OrderInfoDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(OrderInfoDO::getType, reqVO.getType())
                .eqIfPresent(OrderInfoDO::getNum, reqVO.getNum())
                .orderByDesc(OrderInfoDO::getId));
    }

}
