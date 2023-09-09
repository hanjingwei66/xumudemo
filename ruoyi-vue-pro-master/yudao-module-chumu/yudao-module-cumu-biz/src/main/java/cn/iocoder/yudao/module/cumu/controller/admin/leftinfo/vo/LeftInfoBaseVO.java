package cn.iocoder.yudao.module.cumu.controller.admin.leftinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

/**
 * 畜牧离场详情 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class LeftInfoBaseVO {

    @Schema(description = "入场id", example = "31395")
    private Long leftId;

    @Schema(description = "畜牧类型", example = "2")
    private String type;

    @Schema(description = "畜牧数量")
    private Integer num;

}
