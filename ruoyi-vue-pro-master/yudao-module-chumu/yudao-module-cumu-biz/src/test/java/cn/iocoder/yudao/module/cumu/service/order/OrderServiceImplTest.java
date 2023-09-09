package cn.iocoder.yudao.module.cumu.service.order;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.cumu.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.order.OrderDO;
import cn.iocoder.yudao.module.cumu.dal.mysql.order.OrderMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.cumu.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link OrderServiceImpl} 的单元测试类
 *
 * @author 管理员
 */
@Import(OrderServiceImpl.class)
public class OrderServiceImplTest extends BaseDbUnitTest {

    @Resource
    private OrderServiceImpl orderService;

    @Resource
    private OrderMapper orderMapper;

    @Test
    public void testCreateOrder_success() {
        // 准备参数
        OrderCreateReqVO reqVO = randomPojo(OrderCreateReqVO.class);

        // 调用
        Long orderId = orderService.createOrder(reqVO);
        // 断言
        assertNotNull(orderId);
        // 校验记录的属性是否正确
        OrderDO order = orderMapper.selectById(orderId);
        assertPojoEquals(reqVO, order);
    }

    @Test
    public void testUpdateOrder_success() {
        // mock 数据
        OrderDO dbOrder = randomPojo(OrderDO.class);
        orderMapper.insert(dbOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        OrderUpdateReqVO reqVO = randomPojo(OrderUpdateReqVO.class, o -> {
            o.setId(dbOrder.getId()); // 设置更新的 ID
        });

        // 调用
        orderService.updateOrder(reqVO);
        // 校验是否更新正确
        OrderDO order = orderMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, order);
    }

    @Test
    public void testUpdateOrder_notExists() {
        // 准备参数
        OrderUpdateReqVO reqVO = randomPojo(OrderUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> orderService.updateOrder(reqVO), ORDER_NOT_EXISTS);
    }

    @Test
    public void testDeleteOrder_success() {
        // mock 数据
        OrderDO dbOrder = randomPojo(OrderDO.class);
        orderMapper.insert(dbOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbOrder.getId();

        // 调用
        orderService.deleteOrder(id);
       // 校验数据不存在了
       assertNull(orderMapper.selectById(id));
    }

    @Test
    public void testDeleteOrder_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> orderService.deleteOrder(id), ORDER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderPage() {
       // mock 数据
       OrderDO dbOrder = randomPojo(OrderDO.class, o -> { // 等会查询到
           o.setBuyId(null);
           o.setSaleId(null);
           o.setOrderNum(null);
           o.setTokenNum(null);
           o.setOrderPrice(null);
           o.setOrderCost(null);
           o.setOrderTime(null);
           o.setOrderStatus(null);
           o.setRember(null);
           o.setCreateTime(null);
       });
       orderMapper.insert(dbOrder);
       // 测试 buyId 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setBuyId(null)));
       // 测试 saleId 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setSaleId(null)));
       // 测试 orderNum 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderNum(null)));
       // 测试 tokenNum 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setTokenNum(null)));
       // 测试 orderPrice 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderPrice(null)));
       // 测试 orderCost 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderCost(null)));
       // 测试 orderTime 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderTime(null)));
       // 测试 orderStatus 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderStatus(null)));
       // 测试 rember 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setRember(null)));
       // 测试 createTime 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setCreateTime(null)));
       // 准备参数
       OrderPageReqVO reqVO = new OrderPageReqVO();
       reqVO.setBuyId(null);
       reqVO.setSaleId(null);
       reqVO.setOrderNum(null);
       reqVO.setTokenNum(null);
       reqVO.setOrderPrice(null);
       reqVO.setOrderCost(null);
       reqVO.setOrderTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setOrderStatus(null);
       reqVO.setRember(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
//       PageResult<OrderDO> pageResult = orderService.getOrderPage(reqVO);
//       // 断言
//       assertEquals(1, pageResult.getTotal());
//       assertEquals(1, pageResult.getList().size());
//       assertPojoEquals(dbOrder, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderList() {
       // mock 数据
       OrderDO dbOrder = randomPojo(OrderDO.class, o -> { // 等会查询到
           o.setBuyId(null);
           o.setSaleId(null);
           o.setOrderNum(null);
           o.setTokenNum(null);
           o.setOrderPrice(null);
           o.setOrderCost(null);
           o.setOrderTime(null);
           o.setOrderStatus(null);
           o.setRember(null);
           o.setCreateTime(null);
       });
       orderMapper.insert(dbOrder);
       // 测试 buyId 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setBuyId(null)));
       // 测试 saleId 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setSaleId(null)));
       // 测试 orderNum 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderNum(null)));
       // 测试 tokenNum 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setTokenNum(null)));
       // 测试 orderPrice 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderPrice(null)));
       // 测试 orderCost 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderCost(null)));
       // 测试 orderTime 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderTime(null)));
       // 测试 orderStatus 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderStatus(null)));
       // 测试 rember 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setRember(null)));
       // 测试 createTime 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setCreateTime(null)));
       // 准备参数
       OrderExportReqVO reqVO = new OrderExportReqVO();
       reqVO.setBuyId(null);
       reqVO.setSaleId(null);
       reqVO.setOrderNum(null);
       reqVO.setTokenNum(null);
       reqVO.setOrderPrice(null);
       reqVO.setOrderCost(null);
       reqVO.setOrderTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setOrderStatus(null);
       reqVO.setRember(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<OrderDO> list = orderService.getOrderList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbOrder, list.get(0));
    }

}
