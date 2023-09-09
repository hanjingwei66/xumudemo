package cn.iocoder.yudao.module.cumu.convert.leftinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.cumu.controller.admin.leftinfo.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.leftinfo.LeftInfoDO;

/**
 * 畜牧离场详情 Convert
 *
 * @author 管理员
 */
@Mapper
public interface LeftInfoConvert {

    LeftInfoConvert INSTANCE = Mappers.getMapper(LeftInfoConvert.class);

    LeftInfoDO convert(LeftInfoCreateReqVO bean);

    LeftInfoDO convert(LeftInfoUpdateReqVO bean);

    LeftInfoRespVO convert(LeftInfoDO bean);

    List<LeftInfoRespVO> convertList(List<LeftInfoDO> list);

    PageResult<LeftInfoRespVO> convertPage(PageResult<LeftInfoDO> page);

    List<LeftInfoExcelVO> convertList02(List<LeftInfoDO> list);

}
