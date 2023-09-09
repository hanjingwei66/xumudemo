package cn.iocoder.yudao.module.cumu.controller.admin.juan;

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

import cn.iocoder.yudao.module.cumu.controller.admin.juan.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.juan.JuanDO;
import cn.iocoder.yudao.module.cumu.convert.juan.JuanConvert;
import cn.iocoder.yudao.module.cumu.service.juan.JuanService;

@Tag(name = "管理后台 - 圈")
@RestController
@RequestMapping("/cumu/juan")
@Validated
public class JuanController {

    @Resource
    private JuanService juanService;

    @PostMapping("/create")
    @Operation(summary = "创建圈")
    @PreAuthorize("@ss.hasPermission('cumu:juan:create')")
    public CommonResult<Integer> createJuan(@Valid @RequestBody JuanCreateReqVO createReqVO) {
        return success(juanService.createJuan(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新圈")
    @PreAuthorize("@ss.hasPermission('cumu:juan:update')")
    public CommonResult<Boolean> updateJuan(@Valid @RequestBody JuanUpdateReqVO updateReqVO) {
        juanService.updateJuan(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除圈")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cumu:juan:delete')")
    public CommonResult<Boolean> deleteJuan(@RequestParam("id") Integer id) {
        juanService.deleteJuan(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得圈")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cumu:juan:query')")
    public CommonResult<JuanRespVO> getJuan(@RequestParam("id") Integer id) {
        JuanDO juan = juanService.getJuan(id);
        return success(JuanConvert.INSTANCE.convert(juan));
    }
    @GetMapping("/getByRegionId")
    @Operation(summary = "获得圈")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cumu:juan:query')")
    public CommonResult<List<JuanExcelVO> > getByRegionId(@RequestParam("id") Integer id) {
        List<JuanDO> juan = juanService.getByRegionId(id);
        return success(JuanConvert.INSTANCE.convertList02(juan));
    }

    @GetMapping("/list")
    @Operation(summary = "获得圈列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cumu:juan:query')")
    public CommonResult<List<JuanRespVO>> getJuanList(@RequestParam("ids") Collection<Integer> ids) {
        List<JuanDO> list = juanService.getJuanList(ids);
        return success(JuanConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得圈分页")
    @PreAuthorize("@ss.hasPermission('cumu:juan:query')")
    public CommonResult<PageResult<JuanRespVO>> getJuanPage(@Valid JuanPageReqVO pageVO) {
        PageResult<JuanDO> pageResult = juanService.getJuanPage(pageVO);
        return success(JuanConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出圈 Excel")
    @PreAuthorize("@ss.hasPermission('cumu:juan:export')")
    @OperateLog(type = EXPORT)
    public void exportJuanExcel(@Valid JuanExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<JuanDO> list = juanService.getJuanList(exportReqVO);
        // 导出 Excel
        List<JuanExcelVO> datas = JuanConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "圈.xls", "数据", JuanExcelVO.class, datas);
    }

}
