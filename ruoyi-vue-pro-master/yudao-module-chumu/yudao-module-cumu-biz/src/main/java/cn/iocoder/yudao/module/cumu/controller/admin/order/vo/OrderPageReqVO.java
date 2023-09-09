package cn.iocoder.yudao.module.cumu.controller.admin.order.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 畜牧订单交易分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderPageReqVO extends PageParam {

    @Schema(description = "买方id", example = "7564")
    private Long buyId;

    @Schema(description = "卖方id", example = "16891")
    private Long saleId;

    @Schema(description = "订单数量")
    private Integer orderNum;

    @Schema(description = "结算凭证号")
    private Long tokenNum;

    @Schema(description = "订单单价", example = "28909")
    private BigDecimal orderPrice;

    @Schema(description = "订单金额")
    private BigDecimal orderCost;

    @Schema(description = "订单时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] orderTime;

    @Schema(description = "订单状态0未支付1支付2退款", example = "2")
    private Integer orderStatus;

    @Schema(description = "备注")
    private String rember;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
