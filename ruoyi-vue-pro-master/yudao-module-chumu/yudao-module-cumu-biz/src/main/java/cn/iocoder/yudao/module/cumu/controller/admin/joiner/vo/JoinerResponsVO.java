package cn.iocoder.yudao.module.cumu.controller.admin.joiner.vo;

import cn.iocoder.yudao.module.cumu.dal.dataobject.custom.CustomDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo.JoinInfoDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 畜牧进场 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JoinerResponsVO extends JoinerBaseVO{
    private Long id;
    private CustomDO customDO;
    private List<JoinInfoDO> joinInfoDOList;
    private Long joinId;
    private String nikname;
    private LocalDateTime updateTime;
    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;
}
