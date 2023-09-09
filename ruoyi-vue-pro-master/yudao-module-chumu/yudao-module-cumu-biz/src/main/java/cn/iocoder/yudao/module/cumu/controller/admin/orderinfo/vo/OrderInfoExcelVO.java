package cn.iocoder.yudao.module.cumu.controller.admin.orderinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 畜牧订单详情 Excel VO
 *
 * @author 管理员
 */
@Data
public class OrderInfoExcelVO {

    @ExcelProperty("orderInfoID")
    private Long id;

    @ExcelProperty("订单id")
    private Long orderId;

    @ExcelProperty("畜牧类型")
    private String type;

    @ExcelProperty("畜牧数量")
    private Integer num;

}
