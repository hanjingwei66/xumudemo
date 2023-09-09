package cn.iocoder.yudao.module.cumu.dal.mysql.leftinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cumu.dal.dataobject.leftinfo.LeftInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cumu.controller.admin.leftinfo.vo.*;

/**
 * 畜牧离场详情 Mapper
 *
 * @author 管理员
 */
@Mapper
public interface LeftInfoMapper extends BaseMapperX<LeftInfoDO> {

    default PageResult<LeftInfoDO> selectPage(LeftInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LeftInfoDO>()
                .eqIfPresent(LeftInfoDO::getLeftId, reqVO.getLeftId())
                .eqIfPresent(LeftInfoDO::getType, reqVO.getType())
                .eqIfPresent(LeftInfoDO::getNum, reqVO.getNum())
                .orderByDesc(LeftInfoDO::getId));
    }

    default List<LeftInfoDO> selectList(LeftInfoExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<LeftInfoDO>()
                .eqIfPresent(LeftInfoDO::getLeftId, reqVO.getLeftId())
                .eqIfPresent(LeftInfoDO::getType, reqVO.getType())
                .eqIfPresent(LeftInfoDO::getNum, reqVO.getNum())
                .orderByAsc(LeftInfoDO::getType));
    }

}
