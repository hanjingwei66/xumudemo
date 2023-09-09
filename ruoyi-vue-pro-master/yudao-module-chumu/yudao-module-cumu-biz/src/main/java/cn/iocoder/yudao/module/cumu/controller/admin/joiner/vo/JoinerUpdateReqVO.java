package cn.iocoder.yudao.module.cumu.controller.admin.joiner.vo;

import cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo.JoinInfoDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 畜牧进场更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JoinerUpdateReqVO extends JoinerBaseVO {

    @Schema(description = "入场登记id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10889")
    @NotNull(message = "入场登记id不能为空")
    private Long id;
    private List<JoinInfoDO> joinInfoDOList;
}
