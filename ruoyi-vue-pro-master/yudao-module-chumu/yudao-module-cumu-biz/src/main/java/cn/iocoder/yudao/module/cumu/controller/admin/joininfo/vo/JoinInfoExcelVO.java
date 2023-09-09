package cn.iocoder.yudao.module.cumu.controller.admin.joininfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 畜牧入场详情 Excel VO
 *
 * @author 管理员
 */
@Data
public class JoinInfoExcelVO {

    @ExcelProperty("orderInfoID")
    private Long id;

    @ExcelProperty("入场id")
    private Long joinId;

    @ExcelProperty("畜牧类型")
    private String type;

    @ExcelProperty("畜牧数量")
    private Integer num;

}
