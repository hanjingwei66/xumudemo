package cn.iocoder.yudao.module.cumu.dal.mysql.lefter;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cumu.controller.admin.joiner.Enmu.JoinLeftEnmu;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joiner.JoinerDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.lefter.LefterDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cumu.controller.admin.lefter.vo.*;

/**
 * 畜牧离场 Mapper
 *
 * @author 管理员
 */
@Mapper
public interface LefterMapper extends BaseMapperX<LefterDO> {

    default PageResult<LefterDO> selectPage(LefterPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LefterDO>()
                .eqIfPresent(LefterDO::getCustomId, reqVO.getCustomId())
                .eqIfPresent(LefterDO::getCarId, reqVO.getCarId())
                .eqIfPresent(LefterDO::getType, reqVO.getType())
                .eqIfPresent(LefterDO::getNumber, reqVO.getNumber())
                .eqIfPresent(LefterDO::getNumberReal, reqVO.getNumberReal())
                .eqIfPresent(LefterDO::getRemeber, reqVO.getRemeber())
                .betweenIfPresent(LefterDO::getLeftTime, reqVO.getJoinTime())
                .betweenIfPresent(LefterDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(LefterDO::getId));
    }

    default List<LefterDO> selectList(LefterExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<LefterDO>()
                .eqIfPresent(LefterDO::getCustomId, reqVO.getCustomId())
                .eqIfPresent(LefterDO::getCarId, reqVO.getCarId())
                .eqIfPresent(LefterDO::getType, reqVO.getType())
                .eqIfPresent(LefterDO::getNumber, reqVO.getNumber())
                .eqIfPresent(LefterDO::getNumberReal, reqVO.getNumberReal())
                .eqIfPresent(LefterDO::getRemeber, reqVO.getRemeber())
                .betweenIfPresent(LefterDO::getLeftTime, reqVO.getLeftTime())
                .betweenIfPresent(LefterDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(LefterDO::getId));
    }
    default LefterDO selectByCustomId(Long id) {
        return selectOne(new LambdaQueryWrapperX<LefterDO>()
                .eqIfPresent(LefterDO::getCustomId, id)
                .eqIfPresent(LefterDO::getJoinleftFlag, JoinLeftEnmu.JOIN_FLAG.getStatus())
                .orderByDesc(LefterDO::getCreateTime).last("limit 1")
        );
    }
    default LefterDO selectOneByJoinId(Long joinId){
        return selectOne(new LambdaQueryWrapperX<LefterDO>()
                .eqIfPresent(LefterDO::getJoinId,joinId)
                .orderByDesc(LefterDO::getCreateTime).last("limit 1")
        );
    }

}
