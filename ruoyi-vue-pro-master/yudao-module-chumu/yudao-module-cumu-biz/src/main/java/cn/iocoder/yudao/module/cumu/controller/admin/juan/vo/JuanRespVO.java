package cn.iocoder.yudao.module.cumu.controller.admin.juan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 圈 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JuanRespVO extends JuanBaseVO {

    @Schema(description = "牛圈id", requiredMode = Schema.RequiredMode.REQUIRED, example = "12769")
    private Integer id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
