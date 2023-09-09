package cn.iocoder.yudao.module.cumu.controller.admin.joiner.vo;

import cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo.JoinInfoDO;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 畜牧进场创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JoinerCreateReqVO extends JoinerBaseVO {
    private List<JoinInfoDO> joinInfoDOS;
}
