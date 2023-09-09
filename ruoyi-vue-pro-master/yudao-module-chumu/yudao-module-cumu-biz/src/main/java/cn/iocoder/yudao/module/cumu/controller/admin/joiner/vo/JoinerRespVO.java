package cn.iocoder.yudao.module.cumu.controller.admin.joiner.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 畜牧进场 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JoinerRespVO extends JoinerBaseVO {

    @Schema(description = "入场登记id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10889")
    private Long id;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
