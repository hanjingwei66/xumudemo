package cn.iocoder.yudao.module.cumu.controller.admin.custom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 畜牧用户 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CustomBaseVO {

    @Schema(description = "卡号", requiredMode = Schema.RequiredMode.REQUIRED, example = "2467")
    @NotNull(message = "卡号不能为空")
    private Long id;

    @Schema(description = "用户姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "用户姓名不能为空")
    private String customName;

    @Schema(description = "电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "电话不能为空")
    private String customPhone;

    @Schema(description = "身份证", requiredMode = Schema.RequiredMode.REQUIRED, example = "30994")
    @NotNull(message = "身份证不能为空")
    private String customCardId;

    @Schema(description = "车牌号", example = "29677")
    private String carId;

    @Schema(description = "凭证开始号")
    private Integer tokenStartNo;

    @Schema(description = "凭证结束号")
    private Integer tokenEndNo;
    private Integer region;
    private Integer regionNum;
    @Schema(description = "圈开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime regionStartTime;
    @Schema(description = "圈开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime regionEndTime;

}
