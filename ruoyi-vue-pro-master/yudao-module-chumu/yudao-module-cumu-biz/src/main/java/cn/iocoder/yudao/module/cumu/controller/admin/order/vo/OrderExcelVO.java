package cn.iocoder.yudao.module.cumu.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 畜牧订单交易 Excel VO
 *
 * @author 管理员
 */
@Data
public class OrderExcelVO {

    @ExcelProperty("订单id")
    private Long id;

    @ExcelProperty("买方id")
    private Long buyId;

    @ExcelProperty("卖方id")
    private Long saleId;

    @ExcelProperty("订单数量")
    private Integer orderNum;

    @ExcelProperty("结算凭证号")
    private Long tokenNum;

    @ExcelProperty("订单单价")
    private BigDecimal orderPrice;

    @ExcelProperty("订单金额")
    private BigDecimal orderCost;

    @ExcelProperty("订单时间")
    private LocalDateTime orderTime;

    @ExcelProperty("订单状态0未支付1支付2退款")
    private Integer orderStatus;

    @ExcelProperty("备注")
    private String rember;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
