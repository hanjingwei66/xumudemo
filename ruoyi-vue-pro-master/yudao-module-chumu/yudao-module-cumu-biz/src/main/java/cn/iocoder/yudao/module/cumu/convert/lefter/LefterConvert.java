package cn.iocoder.yudao.module.cumu.convert.lefter;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.cumu.controller.admin.lefter.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.lefter.LefterDO;

/**
 * 畜牧离场 Convert
 *
 * @author 管理员
 */
@Mapper
public interface LefterConvert {

    LefterConvert INSTANCE = Mappers.getMapper(LefterConvert.class);

    LefterDO convert(LefterCreateReqVO bean);

    LefterDO convert(LefterUpdateReqVO bean);

    LefterRespVO convert(LefterDO bean);

    List<LefterRespVO> convertList(List<LefterDO> list);

    PageResult<LefterRespVO> convertPage(PageResult<LefterDO> page);

    List<LefterExcelVO> convertList02(List<LefterDO> list);

}
