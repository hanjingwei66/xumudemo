package cn.iocoder.yudao.module.cumu.controller.admin.custom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 畜牧用户 Excel VO
 *
 * @author 管理员
 */
@Data
public class CustomExcelVO {

    @ExcelProperty("卡号")
    private Long id;

    @ExcelProperty("用户姓名")
    private String customName;

    @ExcelProperty("电话")
    private String customPhone;

    @ExcelProperty("身份证")
    private String customCardId;

    @ExcelProperty("车牌号")
    private String carId;

    @ExcelProperty("凭证开始号")
    private Integer tokenStartNo;

    @ExcelProperty("凭证结束号")
    private Integer tokenEndNo;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
