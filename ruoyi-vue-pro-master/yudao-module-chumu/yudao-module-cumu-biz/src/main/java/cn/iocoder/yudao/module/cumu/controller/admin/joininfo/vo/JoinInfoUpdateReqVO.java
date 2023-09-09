package cn.iocoder.yudao.module.cumu.controller.admin.joininfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 畜牧入场详情更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JoinInfoUpdateReqVO extends JoinInfoBaseVO {

    @Schema(description = "orderInfoID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26453")
    @NotNull(message = "orderInfoID不能为空")
    private Long id;

}
