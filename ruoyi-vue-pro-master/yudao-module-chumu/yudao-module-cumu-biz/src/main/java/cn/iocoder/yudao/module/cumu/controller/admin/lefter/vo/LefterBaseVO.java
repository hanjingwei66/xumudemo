package cn.iocoder.yudao.module.cumu.controller.admin.lefter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 畜牧离场 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class LefterBaseVO {

    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "17020")
    @NotNull(message = "用户id不能为空")
    private Long customId;

    @Schema(description = "车牌号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8207")
    @NotNull(message = "车牌号不能为空")
    private String carId;

    @Schema(description = "畜牧类别", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Integer type;

    @Schema(description = "预计畜牧数量")
    private Integer number;

    @Schema(description = "实际畜牧数量")
    private Integer numberReal;

    @Schema(description = "备注")
    private String remeber;

    @Schema(description = "离场时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "离场时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime leftTime;
    private Integer joinleftFlag; //入场离场标识 1入场 2离场
    private String typeString;
    private Long joinId;
    private Integer region; //圈区域
    private Integer regionNum;// 圈号
    private Integer tempRegion; //临时圈区域
    private Integer tempRegionNum;// 临时圈号
    private Integer regionType;
    private LocalDateTime regionEndTime;
    private LocalDateTime regionStartTime;
}
