package cn.iocoder.yudao.module.cumu.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 畜牧订单交易更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderUpdateReqVO extends OrderBaseVO {

    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "8729")
    @NotNull(message = "订单id不能为空")
    private Long id;

}
