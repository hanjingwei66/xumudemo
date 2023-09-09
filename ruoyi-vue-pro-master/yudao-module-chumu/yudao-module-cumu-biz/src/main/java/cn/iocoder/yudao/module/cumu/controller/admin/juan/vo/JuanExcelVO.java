package cn.iocoder.yudao.module.cumu.controller.admin.juan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 圈 Excel VO
 *
 * @author 管理员
 */
@Data
public class JuanExcelVO {

    @ExcelProperty("牛圈id")
    private Integer id;

    @ExcelProperty("牛圈区")
    private Integer region;

    @ExcelProperty("牛圈号")
    private Integer regionNum;

    @ExcelProperty("0未使用 1已使用")
    private Integer status;

    @ExcelProperty("0临时1长期")
    private Integer type;

    @ExcelProperty("到期时间")
    private LocalDateTime endTime;

    @ExcelProperty("用户卡号")
    private Long customId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
