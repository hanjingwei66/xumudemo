package cn.iocoder.yudao.module.cumu.service.joininfo;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.cumu.controller.admin.joininfo.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo.JoinInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.cumu.convert.joininfo.JoinInfoConvert;
import cn.iocoder.yudao.module.cumu.dal.mysql.joininfo.JoinInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cumu.enums.ErrorCodeConstants.*;

/**
 * 畜牧入场详情 Service 实现类
 *
 * @author 管理员
 */
@Service
@Validated
public class JoinInfoServiceImpl implements JoinInfoService {

    @Resource
    private JoinInfoMapper joinInfoMapper;

    @Override
    public Long createJoinInfo(JoinInfoCreateReqVO createReqVO) {
        // 插入
        JoinInfoDO joinInfo = JoinInfoConvert.INSTANCE.convert(createReqVO);
        joinInfoMapper.insert(joinInfo);
        // 返回
        return joinInfo.getId();
    }


    @Override
    public void updateJoinInfo(JoinInfoUpdateReqVO updateReqVO) {
        // 校验存在
        validateJoinInfoExists(updateReqVO.getId());
        // 更新
        JoinInfoDO updateObj = JoinInfoConvert.INSTANCE.convert(updateReqVO);
        joinInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteJoinInfo(Long id) {
        // 校验存在
        validateJoinInfoExists(id);
        // 删除
        joinInfoMapper.deleteById(id);
    }

    private void validateJoinInfoExists(Long id) {
        if (joinInfoMapper.selectById(id) == null) {
            throw exception(JOIN_INFO_NOT_EXISTS);
        }
    }

    @Override
    public JoinInfoDO getJoinInfo(Long id) {
        return joinInfoMapper.selectById(id);
    }

    @Override
    public List<JoinInfoDO> getJoinInfoList(Collection<Long> ids) {
        return joinInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<JoinInfoDO> getJoinInfoPage(JoinInfoPageReqVO pageReqVO) {
        return joinInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<JoinInfoDO> getJoinInfoList(JoinInfoExportReqVO exportReqVO) {
        return joinInfoMapper.selectList(exportReqVO);
    }

}
