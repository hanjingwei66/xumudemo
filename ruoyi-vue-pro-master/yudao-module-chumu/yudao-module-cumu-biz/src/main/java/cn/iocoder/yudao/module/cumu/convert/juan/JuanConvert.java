package cn.iocoder.yudao.module.cumu.convert.juan;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.cumu.controller.admin.juan.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.juan.JuanDO;

/**
 * 圈 Convert
 *
 * @author 管理员
 */
@Mapper
public interface JuanConvert {

    JuanConvert INSTANCE = Mappers.getMapper(JuanConvert.class);

    JuanDO convert(JuanCreateReqVO bean);

    JuanDO convert(JuanUpdateReqVO bean);

    JuanRespVO convert(JuanDO bean);

    List<JuanRespVO> convertList(List<JuanDO> list);

    PageResult<JuanRespVO> convertPage(PageResult<JuanDO> page);

    List<JuanExcelVO> convertList02(List<JuanDO> list);

}
