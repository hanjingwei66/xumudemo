package cn.iocoder.yudao.module.cumu.controller.admin.joininfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 畜牧入场详情分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JoinInfoPageReqVO extends PageParam {

    @Schema(description = "入场id", example = "28677")
    private Long joinId;

    @Schema(description = "畜牧类型", example = "1")
    private String type;

    @Schema(description = "畜牧数量")
    private Integer num;

}
