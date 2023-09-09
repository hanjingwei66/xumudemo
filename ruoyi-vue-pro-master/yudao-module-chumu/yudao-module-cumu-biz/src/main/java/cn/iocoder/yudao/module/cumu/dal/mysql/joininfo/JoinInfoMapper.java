package cn.iocoder.yudao.module.cumu.dal.mysql.joininfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo.JoinInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cumu.controller.admin.joininfo.vo.*;

/**
 * 畜牧入场详情 Mapper
 *
 * @author 管理员
 */
@Mapper
public interface JoinInfoMapper extends BaseMapperX<JoinInfoDO> {

    default PageResult<JoinInfoDO> selectPage(JoinInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JoinInfoDO>()
                .eqIfPresent(JoinInfoDO::getJoinId, reqVO.getJoinId())
                .eqIfPresent(JoinInfoDO::getType, reqVO.getType())
                .eqIfPresent(JoinInfoDO::getNum, reqVO.getNum())
                .orderByDesc(JoinInfoDO::getId));
    }

    default List<JoinInfoDO> selectList(JoinInfoExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<JoinInfoDO>()
                .eqIfPresent(JoinInfoDO::getJoinId, reqVO.getJoinId())
                .eqIfPresent(JoinInfoDO::getType, reqVO.getType())
                .eqIfPresent(JoinInfoDO::getNum, reqVO.getNum())
                .orderByAsc(JoinInfoDO::getType));
    }

}
