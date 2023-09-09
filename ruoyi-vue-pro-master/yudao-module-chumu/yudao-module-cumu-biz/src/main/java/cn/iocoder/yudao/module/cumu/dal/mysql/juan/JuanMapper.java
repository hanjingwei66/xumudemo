package cn.iocoder.yudao.module.cumu.dal.mysql.juan;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cumu.dal.dataobject.juan.JuanDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cumu.controller.admin.juan.vo.*;

/**
 * 圈 Mapper
 *
 * @author 管理员
 */
@Mapper
public interface JuanMapper extends BaseMapperX<JuanDO> {

    default PageResult<JuanDO> selectPage(JuanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JuanDO>()
                .eqIfPresent(JuanDO::getRegion, reqVO.getRegion())
                .eqIfPresent(JuanDO::getRegionNum, reqVO.getRegionNum())
                .eqIfPresent(JuanDO::getStatus, reqVO.getStatus())
                .eqIfPresent(JuanDO::getType, reqVO.getType())
                .betweenIfPresent(JuanDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(JuanDO::getCustomId, reqVO.getCustomId())
                .betweenIfPresent(JuanDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(JuanDO::getId));
    }

    default List<JuanDO> selectList(JuanExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<JuanDO>()
                .eqIfPresent(JuanDO::getRegion, reqVO.getRegion())
                .eqIfPresent(JuanDO::getRegionNum, reqVO.getRegionNum())
                .eqIfPresent(JuanDO::getStatus, reqVO.getStatus())
                .eqIfPresent(JuanDO::getType, reqVO.getType())
                .betweenIfPresent(JuanDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(JuanDO::getCustomId, reqVO.getCustomId())
                .betweenIfPresent(JuanDO::getCreateTime, reqVO.getCreateTime())
                .orderByAsc(JuanDO::getId));
    }

}
