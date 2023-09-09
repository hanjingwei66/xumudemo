package cn.iocoder.yudao.module.cumu.controller.admin.juan.vo;

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
 * 圈 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class JuanBaseVO {

    @Schema(description = "牛圈区")
    private Integer region;

    @Schema(description = "牛圈号")
    private Integer regionNum;

    @Schema(description = "0未使用 1已使用", example = "1")
    private Integer status;

    @Schema(description = "0临时1长期", example = "2")
    private Integer type;

    @Schema(description = "到期时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;

    @Schema(description = "用户卡号", example = "17303")
    private Long customId;

}
