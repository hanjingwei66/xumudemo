package cn.iocoder.yudao.module.cumu.dal.dataobject.lefter;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 畜牧离场 DO
 *
 * @author 管理员
 */
@TableName("cumu_lefter")
@KeySequence("cumu_lefter_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LefterDO extends BaseDO {

    /**
     * 离场登记id
     */
    @TableId
    private Long id;
    /**
     * 用户id
     */
    private Long customId;
    /**
     * 车牌号
     */
    private String carId;
    /**
     * 畜牧类别
     *
     * 枚举 {@link TODO cumu_type 对应的类}
     */
    private Integer type;
    /**
     * 预计畜牧数量
     */
    private Integer number;
    /**
     * 实际畜牧数量
     */
    private Integer numberReal;
    /**
     * 备注
     */
    private String remeber;
    /**
     * 离场时间
     */
    private LocalDateTime leftTime;

    private Long joinId;
    private Integer joinleftFlag;
    private String typeString;
    private Integer region; //圈区域
    private Integer regionNum;// 圈号
    private Integer tempRegion; //临时圈区域
    private Integer tempRegionNum;// 临时圈号
    private Integer regionType;
    private LocalDateTime regionEndTime;
    private LocalDateTime regionStartTime;
}
