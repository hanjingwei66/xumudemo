package cn.iocoder.yudao.module.cumu.controller.admin.orderinfo;

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

import cn.iocoder.yudao.module.cumu.controller.admin.orderinfo.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.orderinfo.OrderInfoDO;
import cn.iocoder.yudao.module.cumu.convert.orderinfo.OrderInfoConvert;
import cn.iocoder.yudao.module.cumu.service.orderinfo.OrderInfoService;

@Tag(name = "管理后台 - 畜牧订单详情")
@RestController
@RequestMapping("/cumu/order-info")
@Validated
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建畜牧订单详情")
    @PreAuthorize("@ss.hasPermission('cumu:order-info:create')")
    public CommonResult<Long> createOrderInfo(@Valid @RequestBody OrderInfoCreateReqVO createReqVO) {
        return success(orderInfoService.createOrderInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新畜牧订单详情")
    @PreAuthorize("@ss.hasPermission('cumu:order-info:update')")
    public CommonResult<Boolean> updateOrderInfo(@Valid @RequestBody OrderInfoUpdateReqVO updateReqVO) {
        orderInfoService.updateOrderInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除畜牧订单详情")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cumu:order-info:delete')")
    public CommonResult<Boolean> deleteOrderInfo(@RequestParam("id") Long id) {
        orderInfoService.deleteOrderInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得畜牧订单详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cumu:order-info:query')")
    public CommonResult<OrderInfoRespVO> getOrderInfo(@RequestParam("id") Long id) {
        OrderInfoDO orderInfo = orderInfoService.getOrderInfo(id);
        return success(OrderInfoConvert.INSTANCE.convert(orderInfo));
    }

    @GetMapping("/list")
    @Operation(summary = "获得畜牧订单详情列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cumu:order-info:query')")
    public CommonResult<List<OrderInfoRespVO>> getOrderInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<OrderInfoDO> list = orderInfoService.getOrderInfoList(ids);
        return success(OrderInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得畜牧订单详情分页")
    @PreAuthorize("@ss.hasPermission('cumu:order-info:query')")
    public CommonResult<PageResult<OrderInfoRespVO>> getOrderInfoPage(@Valid OrderInfoPageReqVO pageVO) {
        PageResult<OrderInfoDO> pageResult = orderInfoService.getOrderInfoPage(pageVO);
        return success(OrderInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出畜牧订单详情 Excel")
    @PreAuthorize("@ss.hasPermission('cumu:order-info:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderInfoExcel(@Valid OrderInfoExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderInfoDO> list = orderInfoService.getOrderInfoList(exportReqVO);
        // 导出 Excel
        List<OrderInfoExcelVO> datas = OrderInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "畜牧订单详情.xls", "数据", OrderInfoExcelVO.class, datas);
    }

}
