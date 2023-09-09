package cn.iocoder.yudao.module.cumu.controller.admin.joiner.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 畜牧入场 real Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JoinerReadCardVO extends JoinerBaseVO{

    @Schema(description = "用户姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "用户姓名不能为空")
    private String customName;

    @Schema(description = "电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "电话不能为空")
    private String customPhone;

    @Schema(description = "身份证", requiredMode = Schema.RequiredMode.REQUIRED, example = "30994")
    @NotNull(message = "身份证不能为空")
    private String customCardId;

    @Schema(description = "车牌号", example = "29677")
    private String carId;

    @Schema(description = "凭证开始号")
    private Integer tokenStartNo;

    @Schema(description = "凭证结束号")
    private Integer tokenEndNo;
}
