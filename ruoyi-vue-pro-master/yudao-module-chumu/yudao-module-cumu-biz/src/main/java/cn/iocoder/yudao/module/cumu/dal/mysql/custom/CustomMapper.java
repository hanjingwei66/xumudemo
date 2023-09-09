package cn.iocoder.yudao.module.cumu.dal.mysql.custom;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.cumu.dal.dataobject.custom.CustomDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.cumu.controller.admin.custom.vo.*;

/**
 * 畜牧用户 Mapper
 *
 * @author 管理员
 */
@Mapper
public interface CustomMapper extends BaseMapperX<CustomDO> {

    default PageResult<CustomDO> selectPage(CustomPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomDO>()
                .eqIfPresent(CustomDO::getId, reqVO.getId())
                .likeIfPresent(CustomDO::getCustomName, reqVO.getCustomName())
                .eqIfPresent(CustomDO::getCustomPhone, reqVO.getCustomPhone())
                .eqIfPresent(CustomDO::getCustomCardId, reqVO.getCustomCardId())
                .eqIfPresent(CustomDO::getCarId, reqVO.getCarId())
                .eqIfPresent(CustomDO::getTokenStartNo, reqVO.getTokenStartNo())
                .eqIfPresent(CustomDO::getTokenEndNo, reqVO.getTokenEndNo())
                .betweenIfPresent(CustomDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomDO::getId));
    }

    default List<CustomDO> selectList(CustomExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CustomDO>()
                .eqIfPresent(CustomDO::getId, reqVO.getId())
                .likeIfPresent(CustomDO::getCustomName, reqVO.getCustomName())
                .eqIfPresent(CustomDO::getCustomPhone, reqVO.getCustomPhone())
                .eqIfPresent(CustomDO::getCustomCardId, reqVO.getCustomCardId())
                .eqIfPresent(CustomDO::getCarId, reqVO.getCarId())
                .eqIfPresent(CustomDO::getTokenStartNo, reqVO.getTokenStartNo())
                .eqIfPresent(CustomDO::getTokenEndNo, reqVO.getTokenEndNo())
                .betweenIfPresent(CustomDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomDO::getId));
    }

}
