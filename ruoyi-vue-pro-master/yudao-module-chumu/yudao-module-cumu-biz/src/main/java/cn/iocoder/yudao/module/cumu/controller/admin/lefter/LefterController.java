package cn.iocoder.yudao.module.cumu.controller.admin.lefter;

import cn.iocoder.yudao.module.cumu.dal.dataobject.custom.CustomDO;
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

import cn.iocoder.yudao.module.cumu.controller.admin.lefter.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.lefter.LefterDO;
import cn.iocoder.yudao.module.cumu.convert.lefter.LefterConvert;
import cn.iocoder.yudao.module.cumu.service.lefter.LefterService;

@Tag(name = "管理后台 - 畜牧离场")
@RestController
@RequestMapping("/cumu/lefter")
@Validated
public class LefterController {

    @Resource
    private LefterService lefterService;

    @PostMapping("/create")
    @Operation(summary = "创建畜牧离场")
    @PreAuthorize("@ss.hasPermission('cumu:lefter:create')")
    public CommonResult<Long> createLefter(@Valid @RequestBody LefterCreateReqVO createReqVO) {
        return success(lefterService.createLefter(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新畜牧离场")
    @PreAuthorize("@ss.hasPermission('cumu:lefter:update')")
    public CommonResult<Boolean> updateLefter(@Valid @RequestBody LefterUpdateReqVO updateReqVO) {
        lefterService.updateLefter(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除畜牧离场")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cumu:lefter:delete')")
    public CommonResult<Boolean> deleteLefter(@RequestParam("id") Long id) {
        lefterService.deleteLefter(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得畜牧离场")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cumu:lefter:query')")
    public CommonResult<LefterResponsVO> getLefter(@RequestParam("id") Long id) {
        LefterResponsVO lefter = lefterService.getLefter(id);
        return success(lefter);
    }
    @GetMapping("/getLefterById")
    @Operation(summary = "获得畜牧离场")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cumu:lefter:query')")
    public CommonResult<LefterResponsVO> getLefterById(@RequestParam("id") Long id) {
        LefterResponsVO lefter = lefterService.getLefterById(id);
        return success(lefter);
    }
    @GetMapping("/getJoinerByVo")
    @Operation(summary = "获得畜牧离场通过手机号")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cumu:lefter:query')")
    public CommonResult<LefterResponsVO> getJoinerByVo( CustomDO vo) {
        LefterResponsVO lefter = lefterService.getJoinerByVo(vo);
        return success(lefter);
    }
    @GetMapping("/list")
    @Operation(summary = "获得畜牧离场列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cumu:lefter:query')")
    public CommonResult<List<LefterRespVO>> getLefterList(@RequestParam("ids") Collection<Long> ids) {
        List<LefterDO> list = lefterService.getLefterList(ids);
        return success(LefterConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得畜牧离场分页")
    @PreAuthorize("@ss.hasPermission('cumu:lefter:query')")
    public CommonResult<PageResult<LefterResponsVO>> getLefterPage(@Valid LefterPageReqVO pageVO) {
        PageResult<LefterResponsVO> pageResult = lefterService.getLefterPage(pageVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出畜牧离场 Excel")
    @PreAuthorize("@ss.hasPermission('cumu:lefter:export')")
    @OperateLog(type = EXPORT)
    public void exportLefterExcel(@Valid LefterExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<LefterDO> list = lefterService.getLefterList(exportReqVO);
        // 导出 Excel
        List<LefterExcelVO> datas = LefterConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "畜牧离场.xls", "数据", LefterExcelVO.class, datas);
    }

}
