package cn.iocoder.yudao.module.cumu.controller.admin.order.vo;

import cn.iocoder.yudao.module.cumu.dal.dataobject.custom.CustomDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.orderinfo.OrderInfoDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 畜牧订单交易 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OderTableRespVO extends OrderBaseVO {
    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "8729")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;
    private CustomDO buyDO;
    private CustomDO saleDO;
    private List<OrderInfoDO> orderInfoDOS;
    private String typeString;
    private String nikename;
}
