package cn.iocoder.yudao.module.cumu.controller.admin.leftinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 畜牧离场详情 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LeftInfoRespVO extends LeftInfoBaseVO {

    @Schema(description = "orderInfoID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29557")
    private Long id;

}
