package cn.iocoder.yudao.module.cumu.controller.admin.custom;

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

import cn.iocoder.yudao.module.cumu.controller.admin.custom.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.custom.CustomDO;
import cn.iocoder.yudao.module.cumu.convert.custom.CustomConvert;
import cn.iocoder.yudao.module.cumu.service.custom.CustomService;

@Tag(name = "管理后台 - 畜牧用户")
@RestController
@RequestMapping("/cumu/custom")
@Validated
public class CustomController {

    @Resource
    private CustomService customService;

    @PostMapping("/create")
    @Operation(summary = "创建畜牧用户")
    @PreAuthorize("@ss.hasPermission('cumu:custom:create')")
    public CommonResult<Long> createCustom(@Valid @RequestBody CustomCreateReqVO createReqVO) {
        return success(customService.createCustom(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新畜牧用户")
    @PreAuthorize("@ss.hasPermission('cumu:custom:update')")
    public CommonResult<Boolean> updateCustom(@Valid @RequestBody CustomUpdateReqVO updateReqVO) {
        customService.updateCustom(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除畜牧用户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cumu:custom:delete')")
    public CommonResult<Boolean> deleteCustom(@RequestParam("id") Long id) {
        customService.deleteCustom(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得畜牧用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cumu:custom:query')")
    public CommonResult<CustomRespVO> getCustom(@RequestParam("id") Long id) {
        CustomDO custom = customService.getCustom(id);
        return success(CustomConvert.INSTANCE.convert(custom));
    }

    @GetMapping("/list")
    @Operation(summary = "获得畜牧用户列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cumu:custom:query')")
    public CommonResult<List<CustomRespVO>> getCustomList(@RequestParam("ids") Collection<Long> ids) {
        List<CustomDO> list = customService.getCustomList(ids);
        return success(CustomConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得畜牧用户分页")
    @PreAuthorize("@ss.hasPermission('cumu:custom:query')")
    public CommonResult<PageResult<CustomRespVO>> getCustomPage(@Valid CustomPageReqVO pageVO) {
        PageResult<CustomDO> pageResult = customService.getCustomPage(pageVO);
        return success(CustomConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出畜牧用户 Excel")
    @PreAuthorize("@ss.hasPermission('cumu:custom:export')")
    @OperateLog(type = EXPORT)
    public void exportCustomExcel(@Valid CustomExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CustomDO> list = customService.getCustomList(exportReqVO);
        // 导出 Excel
        List<CustomExcelVO> datas = CustomConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "畜牧用户.xls", "数据", CustomExcelVO.class, datas);
    }

}
