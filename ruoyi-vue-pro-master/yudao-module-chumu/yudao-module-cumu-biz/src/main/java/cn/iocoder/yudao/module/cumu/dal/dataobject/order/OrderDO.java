package cn.iocoder.yudao.module.cumu.dal.dataobject.order;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 畜牧订单交易 DO
 *
 * @author 管理员
 */
@TableName("cumu_order")
@KeySequence("cumu_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDO extends BaseDO {

    /**
     * 订单id
     */
    @TableId
    private Long id;
    /**
     * 买方id
     */
    private Long buyId;
    /**
     * 卖方id
     */
    private Long saleId;
    /**
     * 订单数量
     */
    private Integer orderNum;
    /**
     * 结算凭证号
     */
    private Long tokenNum;
    /**
     * 订单单价
     */
    private BigDecimal orderPrice;
    /**
     * 订单金额
     */
    private BigDecimal orderCost;
    /**
     * 订单时间
     */
    private LocalDateTime orderTime;
    /**
     * 订单状态0未支付1支付2退款
     */
    private Integer orderStatus;
    /**
     * 备注
     */
    private String rember;
    private Long buyJoinId;
    private Long saleJoinId;
    private Integer region; //圈区域
    private Integer regionNum;// 圈号

}
