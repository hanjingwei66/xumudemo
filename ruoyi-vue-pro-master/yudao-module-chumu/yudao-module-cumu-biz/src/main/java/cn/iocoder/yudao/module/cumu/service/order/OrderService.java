package cn.iocoder.yudao.module.cumu.service.order;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.cumu.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.order.OrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 畜牧订单交易 Service 接口
 *
 * @author 管理员
 */
public interface OrderService {

    /**
     * 创建畜牧订单交易
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrder(@Valid OrderCreateReqVO createReqVO);

    /**
     * 更新畜牧订单交易
     *
     * @param updateReqVO 更新信息
     */
    void updateOrder(@Valid OrderUpdateReqVO updateReqVO);

    /**
     * 删除畜牧订单交易
     *
     * @param id 编号
     */
    void deleteOrder(Long id);

    /**
     * 获得畜牧订单交易
     *
     * @param id 编号
     * @return 畜牧订单交易
     */
    OrderDO getOrder(Long id);

    /**
     * 获得畜牧订单交易列表
     *
     * @param ids 编号
     * @return 畜牧订单交易列表
     */
    List<OrderDO> getOrderList(Collection<Long> ids);

    /**
     * 获得畜牧订单交易分页
     *
     * @param pageReqVO 分页查询
     * @return 畜牧订单交易分页
     */
    PageResult<OderTableRespVO> getOrderPage(OrderPageReqVO pageReqVO);

    /**
     * 获得畜牧订单交易列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 畜牧订单交易列表
     */
    List<OrderDO> getOrderList(OrderExportReqVO exportReqVO);

}
