package cn.iocoder.yudao.module.cumu.controller.admin.leftinfo;

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

import cn.iocoder.yudao.module.cumu.controller.admin.leftinfo.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.leftinfo.LeftInfoDO;
import cn.iocoder.yudao.module.cumu.convert.leftinfo.LeftInfoConvert;
import cn.iocoder.yudao.module.cumu.service.leftinfo.LeftInfoService;

@Tag(name = "管理后台 - 畜牧离场详情")
@RestController
@RequestMapping("/cumu/left-info")
@Validated
public class LeftInfoController {

    @Resource
    private LeftInfoService leftInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建畜牧离场详情")
    @PreAuthorize("@ss.hasPermission('cumu:left-info:create')")
    public CommonResult<Long> createLeftInfo(@Valid @RequestBody LeftInfoCreateReqVO createReqVO) {
        return success(leftInfoService.createLeftInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新畜牧离场详情")
    @PreAuthorize("@ss.hasPermission('cumu:left-info:update')")
    public CommonResult<Boolean> updateLeftInfo(@Valid @RequestBody LeftInfoUpdateReqVO updateReqVO) {
        leftInfoService.updateLeftInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除畜牧离场详情")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cumu:left-info:delete')")
    public CommonResult<Boolean> deleteLeftInfo(@RequestParam("id") Long id) {
        leftInfoService.deleteLeftInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得畜牧离场详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cumu:left-info:query')")
    public CommonResult<LeftInfoRespVO> getLeftInfo(@RequestParam("id") Long id) {
        LeftInfoDO leftInfo = leftInfoService.getLeftInfo(id);
        return success(LeftInfoConvert.INSTANCE.convert(leftInfo));
    }

    @GetMapping("/list")
    @Operation(summary = "获得畜牧离场详情列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cumu:left-info:query')")
    public CommonResult<List<LeftInfoRespVO>> getLeftInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<LeftInfoDO> list = leftInfoService.getLeftInfoList(ids);
        return success(LeftInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得畜牧离场详情分页")
    @PreAuthorize("@ss.hasPermission('cumu:left-info:query')")
    public CommonResult<PageResult<LeftInfoRespVO>> getLeftInfoPage(@Valid LeftInfoPageReqVO pageVO) {
        PageResult<LeftInfoDO> pageResult = leftInfoService.getLeftInfoPage(pageVO);
        return success(LeftInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出畜牧离场详情 Excel")
    @PreAuthorize("@ss.hasPermission('cumu:left-info:export')")
    @OperateLog(type = EXPORT)
    public void exportLeftInfoExcel(@Valid LeftInfoExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<LeftInfoDO> list = leftInfoService.getLeftInfoList(exportReqVO);
        // 导出 Excel
        List<LeftInfoExcelVO> datas = LeftInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "畜牧离场详情.xls", "数据", LeftInfoExcelVO.class, datas);
    }

}
