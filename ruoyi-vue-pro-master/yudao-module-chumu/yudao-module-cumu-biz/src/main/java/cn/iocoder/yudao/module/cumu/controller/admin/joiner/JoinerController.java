package cn.iocoder.yudao.module.cumu.controller.admin.joiner;

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

import cn.iocoder.yudao.module.cumu.controller.admin.joiner.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joiner.JoinerDO;
import cn.iocoder.yudao.module.cumu.convert.joiner.JoinerConvert;
import cn.iocoder.yudao.module.cumu.service.joiner.JoinerService;

@Tag(name = "管理后台 - 畜牧进场")
@RestController
@RequestMapping("/cumu/joiner")
@Validated
public class JoinerController {

    @Resource
    private JoinerService joinerService;

//    @PostMapping("/create")
//    @Operation(summary = "创建畜牧进场")
//    @PreAuthorize("@ss.hasPermission('cumu:joiner:create')")
//    public CommonResult<Long> createJoiner(@Valid @RequestBody JoinerCreateReqVO createReqVO) {
//        return success(joinerService.createJoiner(createReqVO));
//    }
    @PostMapping("/create")
    @Operation(summary = "创建畜牧进场")
    @PreAuthorize("@ss.hasPermission('cumu:joiner:create')")
    public CommonResult<Long> createJoinerNew(@Valid @RequestBody JoinerCreateReqVO createReqVO) {
        return success(joinerService.createJoiner(createReqVO));
    }
    @PutMapping("/update")
    @Operation(summary = "更新畜牧进场")
    @PreAuthorize("@ss.hasPermission('cumu:joiner:update')")
    public CommonResult<Boolean> updateJoiner(@Valid @RequestBody JoinerUpdateReqVO updateReqVO) {
        joinerService.updateJoiner(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除畜牧进场")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('cumu:joiner:delete')")
    public CommonResult<Boolean> deleteJoiner(@RequestParam("id") Long id) {
        joinerService.deleteJoiner(id);
        return success(true);
    }

//    @GetMapping("/get")
//    @Operation(summary = "获得畜牧进场")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('cumu:joiner:query')")
//    public CommonResult<JoinerRespVO> getJoiner(@RequestParam("id") Long id) {
//        JoinerDO joiner = joinerService.getJoiner(id);
//        return success(JoinerConvert.INSTANCE.convert(joiner));
//    }
    @GetMapping("/get")
    @Operation(summary = "获得畜牧进场")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('cumu:joiner:query')")
    public CommonResult<JoinerResponsVO> getJoinerByCustomId(@RequestParam("id") Long customId) {

        JoinerResponsVO joiner = joinerService.getJoiner(customId);
        return success(joiner);
    }
    @GetMapping("/getJoinerById")
    @Operation(summary = "获得畜牧进场byid")
    @Parameter(name = "id", description = "编号", required = true, example = "3024")
    @PreAuthorize("@ss.hasPermission('cumu:joiner:query')")
    public CommonResult<JoinerResponsVO> getJoinerById(@RequestParam("id") Long customId) {

        JoinerResponsVO joiner = joinerService.getJoinerById(customId);
        return success(joiner);
    }
    @GetMapping("/getJoinerByPhone")
    @Operation(summary = "获得畜牧进场byid")
    @Parameter(name = "id", description = "编号", required = true, example = "3024")
    @PreAuthorize("@ss.hasPermission('cumu:joiner:query')")
    public CommonResult<JoinerResponsVO> getJoinerByPhone(@RequestParam("id") CustomDO vo) {

        JoinerResponsVO joiner = joinerService.getJoinerByVo(vo);
        return success(joiner);
    }
//    @GetMapping("/readCard")
//    @Operation(summary = "获得畜牧进场")
//    @Parameter(name = "id", description = "卡号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('cumu:joiner:query')")
//    public CommonResult<JoinerRespVO> readCard(@RequestParam("id") Long id) {
//        JoinerDO joiner = joinerService.getJoiner(id);
//        return success(JoinerConvert.INSTANCE.convert(joiner));
//    }
    @GetMapping("/list")
    @Operation(summary = "获得畜牧进场列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('cumu:joiner:query')")
    public CommonResult<List<JoinerRespVO>> getJoinerList(@RequestParam("ids") Collection<Long> ids) {
        List<JoinerDO> list = joinerService.getJoinerList(ids);
        return success(JoinerConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得畜牧进场分页")
    @PreAuthorize("@ss.hasPermission('cumu:joiner:query')")
    public CommonResult<PageResult<JoinerResponsVO>> getJoinerPage(@Valid JoinerPageReqVO pageVO) {
        PageResult<JoinerResponsVO> pageResult = joinerService.getJoinerPage(pageVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出畜牧进场 Excel")
    @PreAuthorize("@ss.hasPermission('cumu:joiner:export')")
    @OperateLog(type = EXPORT)
    public void exportJoinerExcel(@Valid JoinerExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<JoinerDO> list = joinerService.getJoinerList(exportReqVO);
        // 导出 Excel
        List<JoinerExcelVO> datas = JoinerConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "畜牧进场.xls", "数据", JoinerExcelVO.class, datas);
    }

}
