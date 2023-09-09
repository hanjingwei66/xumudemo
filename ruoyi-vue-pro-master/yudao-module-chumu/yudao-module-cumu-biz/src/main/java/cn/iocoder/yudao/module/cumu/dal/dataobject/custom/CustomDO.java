package cn.iocoder.yudao.module.cumu.dal.dataobject.custom;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 畜牧用户 DO
 *
 * @author 管理员
 */
@TableName("cumu_custom")
@KeySequence("cumu_custom_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomDO extends BaseDO {

    /**
     * 卡号
     */
    @TableId
    private Long id;
    /**
     * 用户姓名
     */
    private String customName;
    /**
     * 电话
     */
    private String customPhone;
    /**
     * 身份证
     */
    private String customCardId;
    /**
     * 车牌号
     */
    private String carId;
    /**
     * 凭证开始号
     */
    private Integer tokenStartNo;
    /**
     * 凭证结束号
     */
    private Integer tokenEndNo;
    private Integer region;
    private Integer regionNum;
    private LocalDateTime regionStartTime;
    private LocalDateTime regionEndTime;

}
