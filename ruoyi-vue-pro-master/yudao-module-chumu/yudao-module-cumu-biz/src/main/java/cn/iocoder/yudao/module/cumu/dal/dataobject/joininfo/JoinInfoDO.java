package cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 畜牧入场详情 DO
 *
 * @author 管理员
 */
@TableName("cumu_join_info")
@KeySequence("cumu_join_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinInfoDO extends BaseDO {

    /**
     * orderInfoID
     */
    @TableId
    private Long id;
    /**
     * 入场id
     */
    private Long joinId;
    /**
     * 畜牧类型
     */
    private String type;
    /**
     * 畜牧数量
     */
    private Integer num;

}
