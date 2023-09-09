package cn.iocoder.yudao.module.cumu.service.leftinfo;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.cumu.controller.admin.leftinfo.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.leftinfo.LeftInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 畜牧离场详情 Service 接口
 *
 * @author 管理员
 */
public interface LeftInfoService {

    /**
     * 创建畜牧离场详情
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLeftInfo(@Valid LeftInfoCreateReqVO createReqVO);

    /**
     * 更新畜牧离场详情
     *
     * @param updateReqVO 更新信息
     */
    void updateLeftInfo(@Valid LeftInfoUpdateReqVO updateReqVO);

    /**
     * 删除畜牧离场详情
     *
     * @param id 编号
     */
    void deleteLeftInfo(Long id);

    /**
     * 获得畜牧离场详情
     *
     * @param id 编号
     * @return 畜牧离场详情
     */
    LeftInfoDO getLeftInfo(Long id);

    /**
     * 获得畜牧离场详情列表
     *
     * @param ids 编号
     * @return 畜牧离场详情列表
     */
    List<LeftInfoDO> getLeftInfoList(Collection<Long> ids);

    /**
     * 获得畜牧离场详情分页
     *
     * @param pageReqVO 分页查询
     * @return 畜牧离场详情分页
     */
    PageResult<LeftInfoDO> getLeftInfoPage(LeftInfoPageReqVO pageReqVO);

    /**
     * 获得畜牧离场详情列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 畜牧离场详情列表
     */
    List<LeftInfoDO> getLeftInfoList(LeftInfoExportReqVO exportReqVO);

}
