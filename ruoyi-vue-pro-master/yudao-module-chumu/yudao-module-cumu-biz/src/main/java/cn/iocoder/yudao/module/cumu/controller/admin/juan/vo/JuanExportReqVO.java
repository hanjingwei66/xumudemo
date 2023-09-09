package cn.iocoder.yudao.module.cumu.controller.admin.juan.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 圈 Excel 导出 Request VO，参数和 JuanPageReqVO 是一致的")
@Data
public class JuanExportReqVO {

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
    private LocalDateTime[] endTime;

    @Schema(description = "用户卡号", example = "17303")
    private Long customId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
