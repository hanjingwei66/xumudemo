package cn.iocoder.yudao.module.cumu.convert.joiner;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.cumu.controller.admin.joiner.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joiner.JoinerDO;

/**
 * 畜牧进场 Convert
 *
 * @author 管理员
 */
@Mapper
public interface JoinerConvert {

    JoinerConvert INSTANCE = Mappers.getMapper(JoinerConvert.class);

    JoinerDO convert(JoinerCreateReqVO bean);

    JoinerDO convert(JoinerUpdateReqVO bean);

    JoinerRespVO convert(JoinerDO bean);

    List<JoinerRespVO> convertList(List<JoinerDO> list);

    PageResult<JoinerRespVO> convertPage(PageResult<JoinerDO> page);

    List<JoinerExcelVO> convertList02(List<JoinerDO> list);

}
