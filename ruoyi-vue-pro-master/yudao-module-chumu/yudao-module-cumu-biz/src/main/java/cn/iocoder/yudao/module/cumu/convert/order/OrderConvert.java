package cn.iocoder.yudao.module.cumu.convert.order;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.cumu.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.order.OrderDO;

/**
 * 畜牧订单交易 Convert
 *
 * @author 管理员
 */
@Mapper
public interface OrderConvert {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    OrderDO convert(OrderCreateReqVO bean);

    OrderDO convert(OrderUpdateReqVO bean);

    OrderRespVO convert(OrderDO bean);

    List<OrderRespVO> convertList(List<OrderDO> list);

    PageResult<OrderRespVO> convertPage(PageResult<OrderDO> page);

    List<OrderExcelVO> convertList02(List<OrderDO> list);

}
