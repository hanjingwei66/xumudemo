package cn.iocoder.yudao.module.cumu.service.orderinfo;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.cumu.controller.admin.orderinfo.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.orderinfo.OrderInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 畜牧订单详情 Service 接口
 *
 * @author 管理员
 */
public interface OrderInfoService {

    /**
     * 创建畜牧订单详情
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderInfo(@Valid OrderInfoCreateReqVO createReqVO);

    /**
     * 更新畜牧订单详情
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderInfo(@Valid OrderInfoUpdateReqVO updateReqVO);

    /**
     * 删除畜牧订单详情
     *
     * @param id 编号
     */
    void deleteOrderInfo(Long id);

    /**
     * 获得畜牧订单详情
     *
     * @param id 编号
     * @return 畜牧订单详情
     */
    OrderInfoDO getOrderInfo(Long id);

    /**
     * 获得畜牧订单详情列表
     *
     * @param ids 编号
     * @return 畜牧订单详情列表
     */
    List<OrderInfoDO> getOrderInfoList(Collection<Long> ids);

    /**
     * 获得畜牧订单详情分页
     *
     * @param pageReqVO 分页查询
     * @return 畜牧订单详情分页
     */
    PageResult<OrderInfoDO> getOrderInfoPage(OrderInfoPageReqVO pageReqVO);

    /**
     * 获得畜牧订单详情列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 畜牧订单详情列表
     */
    List<OrderInfoDO> getOrderInfoList(OrderInfoExportReqVO exportReqVO);

}
