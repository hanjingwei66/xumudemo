package cn.iocoder.yudao.module.cumu.service.juan;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.cumu.controller.admin.juan.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.juan.JuanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 圈 Service 接口
 *
 * @author 管理员
 */
public interface JuanService {

    /**
     * 创建圈
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createJuan(@Valid JuanCreateReqVO createReqVO);

    /**
     * 更新圈
     *
     * @param updateReqVO 更新信息
     */
    void updateJuan(@Valid JuanUpdateReqVO updateReqVO);

    /**
     * 删除圈
     *
     * @param id 编号
     */
    void deleteJuan(Integer id);

    /**
     * 获得圈
     *
     * @param id 编号
     * @return 圈
     */
    JuanDO getJuan(Integer id);
    List<JuanDO> getByRegionId(Integer id);

    /**
     * 获得圈列表
     *
     * @param ids 编号
     * @return 圈列表
     */
    List<JuanDO> getJuanList(Collection<Integer> ids);

    /**
     * 获得圈分页
     *
     * @param pageReqVO 分页查询
     * @return 圈分页
     */
    PageResult<JuanDO> getJuanPage(JuanPageReqVO pageReqVO);

    /**
     * 获得圈列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 圈列表
     */
    List<JuanDO> getJuanList(JuanExportReqVO exportReqVO);

}
