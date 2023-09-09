package cn.iocoder.yudao.module.cumu.controller.admin.orderinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 畜牧订单详情 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderInfoRespVO extends OrderInfoBaseVO {

    @Schema(description = "orderInfoID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3474")
    private Long id;

}
