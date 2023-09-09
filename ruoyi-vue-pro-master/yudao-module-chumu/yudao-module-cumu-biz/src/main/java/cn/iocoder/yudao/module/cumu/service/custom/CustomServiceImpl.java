package cn.iocoder.yudao.module.cumu.service.custom;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.cumu.dal.dataobject.juan.JuanDO;
import cn.iocoder.yudao.module.cumu.dal.mysql.juan.JuanMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.cumu.controller.admin.custom.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.custom.CustomDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.cumu.convert.custom.CustomConvert;
import cn.iocoder.yudao.module.cumu.dal.mysql.custom.CustomMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cumu.enums.ErrorCodeConstants.*;

/**
 * 畜牧用户 Service 实现类
 *
 * @author 管理员
 */
@Service
@Validated
public class CustomServiceImpl implements CustomService {

    @Resource
    private CustomMapper customMapper;
    @Resource
    private JuanMapper juanMapper;
    @Override
    @Transactional
    public Long createCustom(CustomCreateReqVO createReqVO) {
        // 插入
        this.checkRegion(createReqVO);
        createReqVO.setRegionStartTime(LocalDateTime.now());
        CustomDO custom = CustomConvert.INSTANCE.convert(createReqVO);
        customMapper.insert(custom);
        //标记当前圈为已使用

        // 返回
        return custom.getId();
    }
    public void  checkRegion(CustomBaseVO createReqVO) {
        Integer region = createReqVO.getRegion();
        Integer regionNum = createReqVO.getRegionNum();
        if(null==region||null==regionNum ||0==region|| 0==regionNum){
           return;
        }
        JuanDO juanDO = juanMapper.selectOne(new LambdaQueryWrapperX<JuanDO>()
                .eq(JuanDO::getRegion, region).eq(JuanDO::getRegionNum, regionNum));
        if (null == juanDO){
            throw exception(JUAN_NOT_EXISTS);
        }
        if(juanDO.getStatus() ==1){
            throw exception(JUAN_EXISTS);
        }
        JuanDO old = juanMapper.selectOne(new LambdaQueryWrapper<JuanDO>().eq(JuanDO::getCustomId, createReqVO.getId()));
        old.setStatus(0);
        old.setEndTime(null);
        old.setCustomId(null);
        juanMapper.update(old,new LambdaUpdateWrapper<JuanDO>()
                .eq(JuanDO::getRegion, old.getRegion()).eq(JuanDO::getRegionNum, old.getRegionNum()));

        juanDO.setStatus(1);
        juanDO.setEndTime(createReqVO.getRegionEndTime());
        juanDO.setCustomId(createReqVO.getId());
        juanMapper.update(juanDO,new LambdaUpdateWrapper<JuanDO>()
                .eq(JuanDO::getRegion, createReqVO.getRegion()).eq(JuanDO::getRegionNum, createReqVO.getRegionNum()));
    }
    @Override
    @Transactional
    public void updateCustom(CustomUpdateReqVO updateReqVO) {
        this.checkRegion(updateReqVO);
        // 校验存在
        validateCustomExists(updateReqVO.getId());
        // 更新
        CustomDO updateObj = CustomConvert.INSTANCE.convert(updateReqVO);
        customMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustom(Long id) {
        // 校验存在
        validateCustomExists(id);
        // 删除
        customMapper.deleteById(id);
    }

    private void validateCustomExists(Long id) {
        if (customMapper.selectById(id) == null) {
            throw exception(CUSTOM_NOT_EXISTS);
        }
    }

    @Override
    public CustomDO getCustom(Long id) {
        return customMapper.selectById(id);
    }

    @Override
    public List<CustomDO> getCustomList(Collection<Long> ids) {
        return customMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CustomDO> getCustomPage(CustomPageReqVO pageReqVO) {
        return customMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CustomDO> getCustomList(CustomExportReqVO exportReqVO) {
        return customMapper.selectList(exportReqVO);
    }

}
