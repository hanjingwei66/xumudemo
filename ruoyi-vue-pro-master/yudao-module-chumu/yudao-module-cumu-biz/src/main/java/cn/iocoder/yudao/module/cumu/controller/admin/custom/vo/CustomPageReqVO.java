package cn.iocoder.yudao.module.cumu.controller.admin.custom.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 畜牧用户分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CustomPageReqVO extends PageParam {

    @Schema(description = "卡号", example = "2467")
    private Long id;

    @Schema(description = "用户姓名", example = "李四")
    private String customName;

    @Schema(description = "电话")
    private String customPhone;

    @Schema(description = "身份证", example = "30994")
    private String customCardId;

    @Schema(description = "车牌号", example = "29677")
    private String carId;

    @Schema(description = "凭证开始号")
    private Integer tokenStartNo;

    @Schema(description = "凭证结束号")
    private Integer tokenEndNo;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
