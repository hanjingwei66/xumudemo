package cn.iocoder.yudao.module.cumu.controller.admin.order.vo;

import cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo.JoinInfoDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.orderinfo.OrderInfoDO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 畜牧订单交易创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderCreateReqVO extends OrderBaseVO {
    private List<OrderInfoDO> orderInfoDOS;
    private Long buyJoinId;
    private Long saleJoinId;

}
