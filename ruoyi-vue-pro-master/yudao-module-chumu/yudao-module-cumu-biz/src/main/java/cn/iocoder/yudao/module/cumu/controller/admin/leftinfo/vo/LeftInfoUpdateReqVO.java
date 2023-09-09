package cn.iocoder.yudao.module.cumu.controller.admin.leftinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 畜牧离场详情更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LeftInfoUpdateReqVO extends LeftInfoBaseVO {

    @Schema(description = "orderInfoID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29557")
    @NotNull(message = "orderInfoID不能为空")
    private Long id;

}
