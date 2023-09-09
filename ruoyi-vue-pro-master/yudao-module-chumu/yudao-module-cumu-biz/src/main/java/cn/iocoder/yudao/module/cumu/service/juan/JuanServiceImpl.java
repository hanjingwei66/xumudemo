package cn.iocoder.yudao.module.cumu.service.juan;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.cumu.controller.admin.juan.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.juan.JuanDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.cumu.convert.juan.JuanConvert;
import cn.iocoder.yudao.module.cumu.dal.mysql.juan.JuanMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cumu.enums.ErrorCodeConstants.*;

/**
 * 圈 Service 实现类
 *
 * @author 管理员
 */
@Service
@Validated
public class JuanServiceImpl implements JuanService {

    @Resource
    private JuanMapper juanMapper;

    @Override
    public Integer createJuan(JuanCreateReqVO createReqVO) {
        // 插入
        JuanDO juan = JuanConvert.INSTANCE.convert(createReqVO);
        juanMapper.insert(juan);
        // 返回
        return juan.getId();
    }

    @Override
    public void updateJuan(JuanUpdateReqVO updateReqVO) {
        // 校验存在
        validateJuanExists(updateReqVO.getId());
        // 更新
        JuanDO updateObj = JuanConvert.INSTANCE.convert(updateReqVO);
        juanMapper.updateById(updateObj);
    }

    @Override
    public void deleteJuan(Integer id) {
        // 校验存在
        validateJuanExists(id);
        // 删除
        juanMapper.deleteById(id);
    }

    private void validateJuanExists(Integer id) {
        if (juanMapper.selectById(id) == null) {
            throw exception(JUAN_NOT_EXISTS);
        }
    }

    @Override
    public JuanDO getJuan(Integer id) {
        return juanMapper.selectById(id);
    }
    @Override
    public List<JuanDO> getByRegionId(Integer id) {
        JuanExportReqVO juanExportReqVO = new JuanExportReqVO();
        juanExportReqVO.setRegion(id);
        return juanMapper.selectList(juanExportReqVO);
    }
    @Override
    public List<JuanDO> getJuanList(Collection<Integer> ids) {
        return juanMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<JuanDO> getJuanPage(JuanPageReqVO pageReqVO) {
        return juanMapper.selectPage(pageReqVO);
    }

    @Override
    public List<JuanDO> getJuanList(JuanExportReqVO exportReqVO) {
        return juanMapper.selectList(exportReqVO);
    }

}
