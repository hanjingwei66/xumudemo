package cn.iocoder.yudao.module.cumu.controller.admin.orderinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 畜牧订单详情 Excel 导出 Request VO，参数和 OrderInfoPageReqVO 是一致的")
@Data
public class OrderInfoExportReqVO {

    @Schema(description = "订单id", example = "10656")
    private Long orderId;

    @Schema(description = "畜牧类型", example = "1")
    private String type;

    @Schema(description = "畜牧数量")
    private Integer num;

}
