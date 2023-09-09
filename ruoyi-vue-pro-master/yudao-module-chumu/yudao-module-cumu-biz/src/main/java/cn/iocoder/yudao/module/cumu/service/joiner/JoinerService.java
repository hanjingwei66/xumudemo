package cn.iocoder.yudao.module.cumu.service.joiner;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.cumu.controller.admin.joiner.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.custom.CustomDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joiner.JoinerDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 畜牧进场 Service 接口
 *
 * @author 管理员
 */
public interface JoinerService {

    /**
     * 创建畜牧进场
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createJoiner(@Valid JoinerCreateReqVO createReqVO);

    /**
     * 更新畜牧进场
     *
     * @param updateReqVO 更新信息
     */
    void updateJoiner(@Valid JoinerUpdateReqVO updateReqVO);

    /**
     * 删除畜牧进场
     *
     * @param id 编号
     */
    void deleteJoiner(Long id);

    /**
     * 获得畜牧进场
     *
     * @param id 编号
     * @return 畜牧进场
     */
    JoinerResponsVO getJoiner(Long id);
    JoinerResponsVO getJoinerById(Long id);
    JoinerResponsVO getJoinerByVo(CustomDO vo);

    /**
     * 获得畜牧进场列表
     *
     * @param ids 编号
     * @return 畜牧进场列表
     */
    List<JoinerDO> getJoinerList(Collection<Long> ids);

    /**
     * 获得畜牧进场分页
     *
     * @param pageReqVO 分页查询
     * @return 畜牧进场分页
     */
    PageResult<JoinerResponsVO> getJoinerPage(JoinerPageReqVO pageReqVO);

    /**
     * 获得畜牧进场列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 畜牧进场列表
     */
    List<JoinerDO> getJoinerList(JoinerExportReqVO exportReqVO);

}
