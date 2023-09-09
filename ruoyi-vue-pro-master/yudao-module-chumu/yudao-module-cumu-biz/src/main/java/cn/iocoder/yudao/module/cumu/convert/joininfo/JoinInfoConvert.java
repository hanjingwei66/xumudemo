package cn.iocoder.yudao.module.cumu.convert.joininfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.cumu.controller.admin.joininfo.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo.JoinInfoDO;

/**
 * 畜牧入场详情 Convert
 *
 * @author 管理员
 */
@Mapper
public interface JoinInfoConvert {

    JoinInfoConvert INSTANCE = Mappers.getMapper(JoinInfoConvert.class);

    JoinInfoDO convert(JoinInfoCreateReqVO bean);

    JoinInfoDO convert(JoinInfoUpdateReqVO bean);

    JoinInfoRespVO convert(JoinInfoDO bean);

    List<JoinInfoRespVO> convertList(List<JoinInfoDO> list);

    PageResult<JoinInfoRespVO> convertPage(PageResult<JoinInfoDO> page);

    List<JoinInfoExcelVO> convertList02(List<JoinInfoDO> list);

}
