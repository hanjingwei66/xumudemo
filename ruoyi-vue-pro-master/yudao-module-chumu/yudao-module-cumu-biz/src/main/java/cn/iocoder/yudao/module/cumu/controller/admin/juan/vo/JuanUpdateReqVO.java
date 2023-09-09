package cn.iocoder.yudao.module.cumu.controller.admin.juan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 圈更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JuanUpdateReqVO extends JuanBaseVO {

    @Schema(description = "牛圈id", requiredMode = Schema.RequiredMode.REQUIRED, example = "12769")
    @NotNull(message = "牛圈id不能为空")
    private Integer id;

}
