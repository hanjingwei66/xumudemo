package cn.iocoder.yudao.module.cumu.service.joininfo;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.cumu.controller.admin.joininfo.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo.JoinInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 畜牧入场详情 Service 接口
 *
 * @author 管理员
 */
public interface JoinInfoService {

    /**
     * 创建畜牧入场详情
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createJoinInfo(@Valid JoinInfoCreateReqVO createReqVO);

    /**
     * 更新畜牧入场详情
     *
     * @param updateReqVO 更新信息
     */
    void updateJoinInfo(@Valid JoinInfoUpdateReqVO updateReqVO);

    /**
     * 删除畜牧入场详情
     *
     * @param id 编号
     */
    void deleteJoinInfo(Long id);

    /**
     * 获得畜牧入场详情
     *
     * @param id 编号
     * @return 畜牧入场详情
     */
    JoinInfoDO getJoinInfo(Long id);

    /**
     * 获得畜牧入场详情列表
     *
     * @param ids 编号
     * @return 畜牧入场详情列表
     */
    List<JoinInfoDO> getJoinInfoList(Collection<Long> ids);

    /**
     * 获得畜牧入场详情分页
     *
     * @param pageReqVO 分页查询
     * @return 畜牧入场详情分页
     */
    PageResult<JoinInfoDO> getJoinInfoPage(JoinInfoPageReqVO pageReqVO);

    /**
     * 获得畜牧入场详情列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 畜牧入场详情列表
     */
    List<JoinInfoDO> getJoinInfoList(JoinInfoExportReqVO exportReqVO);

}
