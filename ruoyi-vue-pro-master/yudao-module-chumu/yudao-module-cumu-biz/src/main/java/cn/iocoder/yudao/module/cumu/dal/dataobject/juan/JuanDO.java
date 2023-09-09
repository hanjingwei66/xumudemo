package cn.iocoder.yudao.module.cumu.dal.dataobject.juan;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 圈 DO
 *
 * @author 管理员
 */
@TableName("cumu_juan")
@KeySequence("cumu_juan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JuanDO extends BaseDO {

    /**
     * 牛圈id
     */
    @TableId
    private Integer id;
    /**
     * 牛圈区
     */
    private Integer region;
    /**
     * 牛圈号
     */
    private Integer regionNum;
    /**
     * 0未使用 1已使用
     */
    private Integer status;
    /**
     * 0临时1长期
     */
    private Integer type;
    /**
     * 到期时间
     */
    private LocalDateTime endTime;
    /**
     * 用户卡号
     */
    private Long customId;

}
