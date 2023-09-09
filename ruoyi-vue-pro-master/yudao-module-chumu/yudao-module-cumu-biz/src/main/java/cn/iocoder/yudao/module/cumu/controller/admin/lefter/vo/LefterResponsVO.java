package cn.iocoder.yudao.module.cumu.controller.admin.lefter.vo;

import cn.iocoder.yudao.module.cumu.dal.dataobject.custom.CustomDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo.JoinInfoDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.leftinfo.LeftInfoDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
@Schema(description = "管理后台 - 畜牧离场 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LefterResponsVO extends LefterBaseVO{
    private Long id;
    private CustomDO customDO;
    private List<LeftInfoDO> leftInfoDOList;
    private Long joinId;
    private String nikename;
    private LocalDateTime updateTime;
    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;
}
