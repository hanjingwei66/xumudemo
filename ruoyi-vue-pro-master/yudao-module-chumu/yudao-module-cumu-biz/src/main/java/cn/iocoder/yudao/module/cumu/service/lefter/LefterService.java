package cn.iocoder.yudao.module.cumu.service.lefter;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.cumu.controller.admin.lefter.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.custom.CustomDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.lefter.LefterDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 畜牧离场 Service 接口
 *
 * @author 管理员
 */
public interface LefterService {

    /**
     * 创建畜牧离场
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLefter(@Valid LefterCreateReqVO createReqVO);

    /**
     * 更新畜牧离场
     *
     * @param updateReqVO 更新信息
     */
    void updateLefter(@Valid LefterUpdateReqVO updateReqVO);

    /**
     * 删除畜牧离场
     *
     * @param id 编号
     */
    void deleteLefter(Long id);

    /**
     * 获得畜牧离场
     *
     * @param id 编号
     * @return 畜牧离场
     */
    LefterResponsVO getLefter(Long id);
    LefterResponsVO getLefterById(Long id);
    LefterResponsVO getJoinerByVo(CustomDO vo);
    LefterResponsVO getLefterByCustomId(Long customId);
    LefterResponsVO getLefterByJoinId(Long joinId);

    /**
     * 获得畜牧离场列表
     *
     * @param ids 编号
     * @return 畜牧离场列表
     */
    List<LefterDO> getLefterList(Collection<Long> ids);

    /**
     * 获得畜牧离场分页
     *
     * @param pageReqVO 分页查询
     * @return 畜牧离场分页
     */
    PageResult<LefterResponsVO> getLefterPage(LefterPageReqVO pageReqVO);

    /**
     * 获得畜牧离场列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 畜牧离场列表
     */
    List<LefterDO> getLefterList(LefterExportReqVO exportReqVO);

}
