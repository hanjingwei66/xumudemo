package cn.iocoder.yudao.module.cumu.controller.admin.joininfo;

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

import cn.iocoder.yudao.module.cumu.controller.admin.joininfo.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo.JoinInfoDO;
import cn.iocoder.yudao.module.cumu.convert.joininfo.JoinInfoConvert;
import cn.iocoder.yudao.module.cumu.service.joininfo.JoinInfoService;

@Tag(name = "管理后台 - 畜牧入场详情")
@RestController
@RequestMapping("/cumu/join-info")
@Validated
public class JoinInfoController {

    @Resource
    private JoinInfoService joinInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建畜牧入场详情")
    @PreAuthorize("@ss.hasPermission('cumu:join-info:create')")
    public CommonResult<Long> createJoinInfo(@Valid @RequestBody JoinInfoCreateReqVO createReqVO) {
        return success(joinInfoService.createJoinInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新畜牧入场详情")
    @PreAuthorize("@ss.hasPermission('cumu:join-info:update')")
    public CommonResult<Boolean> updateJoinInfo(@Valid @RequestBody JoinInfoUpdateReqVO updateReqVO) {
        joinInfoService.updateJoinInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除畜牧入场详情")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cumu:join-info:delete')")
    public CommonResult<Boolean> deleteJoinInfo(@RequestParam("id") Long id) {
        joinInfoService.deleteJoinInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得畜牧入场详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cumu:join-info:query')")
    public CommonResult<JoinInfoRespVO> getJoinInfo(@RequestParam("id") Long id) {
        JoinInfoDO joinInfo = joinInfoService.getJoinInfo(id);
        return success(JoinInfoConvert.INSTANCE.convert(joinInfo));
    }

    @GetMapping("/list")
    @Operation(summary = "获得畜牧入场详情列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cumu:join-info:query')")
    public CommonResult<List<JoinInfoRespVO>> getJoinInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<JoinInfoDO> list = joinInfoService.getJoinInfoList(ids);
        return success(JoinInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得畜牧入场详情分页")
    @PreAuthorize("@ss.hasPermission('cumu:join-info:query')")
    public CommonResult<PageResult<JoinInfoRespVO>> getJoinInfoPage(@Valid JoinInfoPageReqVO pageVO) {
        PageResult<JoinInfoDO> pageResult = joinInfoService.getJoinInfoPage(pageVO);
        return success(JoinInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出畜牧入场详情 Excel")
    @PreAuthorize("@ss.hasPermission('cumu:join-info:export')")
    @OperateLog(type = EXPORT)
    public void exportJoinInfoExcel(@Valid JoinInfoExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<JoinInfoDO> list = joinInfoService.getJoinInfoList(exportReqVO);
        // 导出 Excel
        List<JoinInfoExcelVO> datas = JoinInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "畜牧入场详情.xls", "数据", JoinInfoExcelVO.class, datas);
    }

}
