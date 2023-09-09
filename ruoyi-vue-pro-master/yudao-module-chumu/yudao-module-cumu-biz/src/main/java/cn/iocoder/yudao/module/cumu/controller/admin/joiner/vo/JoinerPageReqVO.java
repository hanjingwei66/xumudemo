package cn.iocoder.yudao.module.cumu.controller.admin.joiner.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 畜牧进场分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JoinerPageReqVO extends PageParam {

    @Schema(description = "用户id", example = "17836")
    private Long customId;

    @Schema(description = "车牌号", example = "12003")
    private String carId;

    @Schema(description = "畜牧类别", example = "1")
    private Integer type;

    @Schema(description = "畜牧数量")
    private Integer number;

    @Schema(description = "备注")
    private String remeber;

    @Schema(description = "入场时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] joinTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
