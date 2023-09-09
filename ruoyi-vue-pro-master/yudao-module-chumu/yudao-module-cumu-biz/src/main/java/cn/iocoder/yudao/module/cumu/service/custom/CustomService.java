package cn.iocoder.yudao.module.cumu.service.custom;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.cumu.controller.admin.custom.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.custom.CustomDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 畜牧用户 Service 接口
 *
 * @author 管理员
 */
public interface CustomService {

    /**
     * 创建畜牧用户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustom(@Valid CustomCreateReqVO createReqVO);

    /**
     * 更新畜牧用户
     *
     * @param updateReqVO 更新信息
     */
    void updateCustom(@Valid CustomUpdateReqVO updateReqVO);

    /**
     * 删除畜牧用户
     *
     * @param id 编号
     */
    void deleteCustom(Long id);

    /**
     * 获得畜牧用户
     *
     * @param id 编号
     * @return 畜牧用户
     */
    CustomDO getCustom(Long id);

    /**
     * 获得畜牧用户列表
     *
     * @param ids 编号
     * @return 畜牧用户列表
     */
    List<CustomDO> getCustomList(Collection<Long> ids);

    /**
     * 获得畜牧用户分页
     *
     * @param pageReqVO 分页查询
     * @return 畜牧用户分页
     */
    PageResult<CustomDO> getCustomPage(CustomPageReqVO pageReqVO);

    /**
     * 获得畜牧用户列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 畜牧用户列表
     */
    List<CustomDO> getCustomList(CustomExportReqVO exportReqVO);

}
