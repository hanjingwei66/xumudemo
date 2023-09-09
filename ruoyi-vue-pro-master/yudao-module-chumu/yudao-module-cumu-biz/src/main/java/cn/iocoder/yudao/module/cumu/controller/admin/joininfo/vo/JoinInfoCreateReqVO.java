package cn.iocoder.yudao.module.cumu.controller.admin.joininfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 畜牧入场详情创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JoinInfoCreateReqVO extends JoinInfoBaseVO {

}
