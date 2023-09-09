package cn.iocoder.yudao.module.cumu.controller.admin.order;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.cumu.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.order.OrderDO;
import cn.iocoder.yudao.module.cumu.convert.order.OrderConvert;
import cn.iocoder.yudao.module.cumu.service.order.OrderService;

@Tag(name = "管理后台 - 畜牧订单交易")
@RestController
@RequestMapping("/cumu/order")
@Validated
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "创建畜牧订单交易")
    @PreAuthorize("@ss.hasPermission('cumu:order:create')")
    public CommonResult<Long> createOrder(@Valid @RequestBody OrderCreateReqVO createReqVO) {
        return success(orderService.createOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新畜牧订单交易")
    @PreAuthorize("@ss.hasPermission('cumu:order:update')")
    public CommonResult<Boolean> updateOrder(@Valid @RequestBody OrderUpdateReqVO updateReqVO) {
        orderService.updateOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除畜牧订单交易")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cumu:order:delete')")
    public CommonResult<Boolean> deleteOrder(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得畜牧订单交易")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cumu:order:query')")
    public CommonResult<OrderRespVO> getOrder(@RequestParam("id") Long id) {
        OrderDO order = orderService.getOrder(id);
        return success(OrderConvert.INSTANCE.convert(order));
    }

    @GetMapping("/list")
    @Operation(summary = "获得畜牧订单交易列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cumu:order:query')")
    public CommonResult<List<OrderRespVO>> getOrderList(@RequestParam("ids") Collection<Long> ids) {
        List<OrderDO> list = orderService.getOrderList(ids);
        return success(OrderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得畜牧订单交易分页")
    @PreAuthorize("@ss.hasPermission('cumu:order:query')")
    public CommonResult<PageResult<OderTableRespVO>> getOrderPage(@Valid OrderPageReqVO pageVO) {
        PageResult<OderTableRespVO> pageResult = orderService.getOrderPage(pageVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出畜牧订单交易 Excel")
    @PreAuthorize("@ss.hasPermission('cumu:order:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderExcel(@Valid OrderExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderDO> list = orderService.getOrderList(exportReqVO);
        // 导出 Excel
        List<OrderExcelVO> datas = OrderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "畜牧订单交易.xls", "数据", OrderExcelVO.class, datas);
    }

}
