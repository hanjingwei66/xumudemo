package cn.iocoder.yudao.module.cumu.controller.admin.lefter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 畜牧离场 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LefterRespVO extends LefterBaseVO {

    @Schema(description = "离场登记id", requiredMode = Schema.RequiredMode.REQUIRED, example = "27719")
    private Long id;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
