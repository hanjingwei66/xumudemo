package cn.iocoder.yudao.module.cumu.controller.admin.joininfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 畜牧入场详情 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JoinInfoRespVO extends JoinInfoBaseVO {

    @Schema(description = "orderInfoID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26453")
    private Long id;

}
