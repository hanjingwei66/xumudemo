package cn.iocoder.yudao.module.cumu.controller.admin.lefter.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 畜牧离场 Excel 导出 Request VO，参数和 LefterPageReqVO 是一致的")
@Data
public class LefterExportReqVO {

    @Schema(description = "用户id", example = "17020")
    private Long customId;

    @Schema(description = "车牌号", example = "8207")
    private String carId;

    @Schema(description = "畜牧类别", example = "2")
    private Integer type;

    @Schema(description = "预计畜牧数量")
    private Integer number;

    @Schema(description = "实际畜牧数量")
    private Integer numberReal;

    @Schema(description = "备注")
    private String remeber;

    @Schema(description = "离场时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] leftTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
