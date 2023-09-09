package cn.iocoder.yudao.module.cumu.convert.custom;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.cumu.controller.admin.custom.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.custom.CustomDO;

/**
 * 畜牧用户 Convert
 *
 * @author 管理员
 */
@Mapper
public interface CustomConvert {

    CustomConvert INSTANCE = Mappers.getMapper(CustomConvert.class);

    CustomDO convert(CustomCreateReqVO bean);

    CustomDO convert(CustomUpdateReqVO bean);

    CustomRespVO convert(CustomDO bean);

    List<CustomRespVO> convertList(List<CustomDO> list);

    PageResult<CustomRespVO> convertPage(PageResult<CustomDO> page);

    List<CustomExcelVO> convertList02(List<CustomDO> list);

}
