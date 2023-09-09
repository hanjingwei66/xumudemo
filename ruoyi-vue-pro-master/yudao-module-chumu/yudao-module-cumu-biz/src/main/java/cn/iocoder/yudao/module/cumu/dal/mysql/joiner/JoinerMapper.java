package cn.iocoder.yudao.module.cumu.dal.mysql.joiner;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cumu.controller.admin.joiner.Enmu.JoinLeftEnmu;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joiner.JoinerDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cumu.controller.admin.joiner.vo.*;

/**
 * 畜牧进场 Mapper
 *
 * @author 管理员
 */
@Mapper
public interface JoinerMapper extends BaseMapperX<JoinerDO> {

    default PageResult<JoinerDO> selectPage(JoinerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JoinerDO>()
                .eqIfPresent(JoinerDO::getCustomId, reqVO.getCustomId())
                .eqIfPresent(JoinerDO::getCarId, reqVO.getCarId())
                .eqIfPresent(JoinerDO::getType, reqVO.getType())
                .eqIfPresent(JoinerDO::getNumber, reqVO.getNumber())
                .eqIfPresent(JoinerDO::getRemeber, reqVO.getRemeber())
                .betweenIfPresent(JoinerDO::getJoinTime, reqVO.getJoinTime())
                .betweenIfPresent(JoinerDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(JoinerDO::getId));
    }

    default List<JoinerDO> selectList(JoinerExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<JoinerDO>()
                .eqIfPresent(JoinerDO::getCustomId, reqVO.getCustomId())
                .eqIfPresent(JoinerDO::getCarId, reqVO.getCarId())
                .eqIfPresent(JoinerDO::getType, reqVO.getType())
                .eqIfPresent(JoinerDO::getNumber, reqVO.getNumber())
                .eqIfPresent(JoinerDO::getRemeber, reqVO.getRemeber())
                .betweenIfPresent(JoinerDO::getJoinTime, reqVO.getJoinTime())
                .betweenIfPresent(JoinerDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(JoinerDO::getId));
    }
    default JoinerDO selectByCustomId(Long id) {
        return selectOne(new LambdaQueryWrapperX<JoinerDO>()
                .eqIfPresent(JoinerDO::getCustomId, id)
                .eqIfPresent(JoinerDO::getJoinleftFlag, JoinLeftEnmu.JOIN_FLAG.getStatus())
                .orderByDesc(JoinerDO::getCreateTime).last("limit 1")
        );
    }

}
