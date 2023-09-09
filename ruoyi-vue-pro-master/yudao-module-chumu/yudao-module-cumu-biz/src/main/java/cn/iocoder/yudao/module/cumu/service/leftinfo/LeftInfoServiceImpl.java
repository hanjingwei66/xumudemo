package cn.iocoder.yudao.module.cumu.service.leftinfo;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.cumu.controller.admin.leftinfo.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.leftinfo.LeftInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.cumu.convert.leftinfo.LeftInfoConvert;
import cn.iocoder.yudao.module.cumu.dal.mysql.leftinfo.LeftInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cumu.enums.ErrorCodeConstants.*;

/**
 * 畜牧离场详情 Service 实现类
 *
 * @author 管理员
 */
@Service
@Validated
public class LeftInfoServiceImpl implements LeftInfoService {

    @Resource
    private LeftInfoMapper leftInfoMapper;

    @Override
    public Long createLeftInfo(LeftInfoCreateReqVO createReqVO) {
        // 插入
        LeftInfoDO leftInfo = LeftInfoConvert.INSTANCE.convert(createReqVO);
        leftInfoMapper.insert(leftInfo);
        // 返回
        return leftInfo.getId();
    }

    @Override
    public void updateLeftInfo(LeftInfoUpdateReqVO updateReqVO) {
        // 校验存在
        validateLeftInfoExists(updateReqVO.getId());
        // 更新
        LeftInfoDO updateObj = LeftInfoConvert.INSTANCE.convert(updateReqVO);
        leftInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteLeftInfo(Long id) {
        // 校验存在
        validateLeftInfoExists(id);
        // 删除
        leftInfoMapper.deleteById(id);
    }

    private void validateLeftInfoExists(Long id) {
        if (leftInfoMapper.selectById(id) == null) {
            throw exception(LEFT_INFO_NOT_EXISTS);
        }
    }

    @Override
    public LeftInfoDO getLeftInfo(Long id) {
        return leftInfoMapper.selectById(id);
    }

    @Override
    public List<LeftInfoDO> getLeftInfoList(Collection<Long> ids) {
        return leftInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<LeftInfoDO> getLeftInfoPage(LeftInfoPageReqVO pageReqVO) {
        return leftInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<LeftInfoDO> getLeftInfoList(LeftInfoExportReqVO exportReqVO) {
        return leftInfoMapper.selectList(exportReqVO);
    }

}
