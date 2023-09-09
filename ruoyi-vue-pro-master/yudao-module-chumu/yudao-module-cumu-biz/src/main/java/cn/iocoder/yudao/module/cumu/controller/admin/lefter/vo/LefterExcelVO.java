package cn.iocoder.yudao.module.cumu.controller.admin.lefter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 畜牧离场 Excel VO
 *
 * @author 管理员
 */
@Data
public class LefterExcelVO {

    @ExcelProperty("离场登记id")
    private Long id;

    @ExcelProperty("用户id")
    private Long customId;

    @ExcelProperty("车牌号")
    private String carId;

    @ExcelProperty(value = "畜牧类别", converter = DictConvert.class)
    @DictFormat("cumu_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer type;

    @ExcelProperty("预计畜牧数量")
    private Integer number;

    @ExcelProperty("实际畜牧数量")
    private Integer numberReal;

    @ExcelProperty("备注")
    private String remeber;

    @ExcelProperty("离场时间")
    private LocalDateTime joinTime;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
