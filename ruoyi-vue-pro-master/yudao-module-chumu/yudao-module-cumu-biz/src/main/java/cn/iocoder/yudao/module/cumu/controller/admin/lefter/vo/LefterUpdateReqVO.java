package cn.iocoder.yudao.module.cumu.controller.admin.lefter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 畜牧离场更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LefterUpdateReqVO extends LefterBaseVO {

    @Schema(description = "离场登记id", requiredMode = Schema.RequiredMode.REQUIRED, example = "27719")
    @NotNull(message = "离场登记id不能为空")
    private Long id;

}
