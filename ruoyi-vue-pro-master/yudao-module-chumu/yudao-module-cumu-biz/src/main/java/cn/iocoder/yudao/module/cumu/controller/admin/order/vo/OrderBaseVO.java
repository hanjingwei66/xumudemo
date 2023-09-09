package cn.iocoder.yudao.module.cumu.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 畜牧订单交易 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class OrderBaseVO {

    @Schema(description = "买方id", requiredMode = Schema.RequiredMode.REQUIRED, example = "7564")
    @NotNull(message = "买方id不能为空")
    private Long buyId;

    @Schema(description = "卖方id", requiredMode = Schema.RequiredMode.REQUIRED, example = "16891")
    @NotNull(message = "卖方id不能为空")
    private Long saleId;

    @Schema(description = "订单数量")
    private Integer orderNum;

    @Schema(description = "结算凭证号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "结算凭证号不能为空")
    private Long tokenNum;

    @Schema(description = "订单单价", example = "28909")
    private BigDecimal orderPrice;

    @Schema(description = "订单金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "订单金额不能为空")
    private BigDecimal orderCost;

    @Schema(description = "订单时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "订单时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime orderTime;

    @Schema(description = "订单状态0未支付1支付2退款", example = "2")
    private Integer orderStatus;

    @Schema(description = "备注")
    private String rember;
    private Integer region; //圈区域
    private Integer regionNum;// 圈号

}
