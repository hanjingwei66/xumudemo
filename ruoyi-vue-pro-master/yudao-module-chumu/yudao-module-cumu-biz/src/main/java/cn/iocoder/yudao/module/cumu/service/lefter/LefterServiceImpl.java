package cn.iocoder.yudao.module.cumu.service.lefter;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.module.cumu.controller.admin.joiner.Enmu.JoinLeftEnmu;
import cn.iocoder.yudao.module.cumu.controller.admin.joiner.vo.JoinerResponsVO;
import cn.iocoder.yudao.module.cumu.controller.admin.joininfo.vo.JoinInfoExportReqVO;
import cn.iocoder.yudao.module.cumu.controller.admin.leftinfo.vo.LeftInfoExportReqVO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.custom.CustomDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joiner.JoinerDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo.JoinInfoDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.leftinfo.LeftInfoDO;
import cn.iocoder.yudao.module.cumu.dal.mysql.custom.CustomMapper;
import cn.iocoder.yudao.module.cumu.dal.mysql.joiner.JoinerMapper;
import cn.iocoder.yudao.module.cumu.service.custom.CustomService;
import cn.iocoder.yudao.module.cumu.service.leftinfo.LeftInfoService;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.cumu.controller.admin.lefter.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.lefter.LefterDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.cumu.convert.lefter.LefterConvert;
import cn.iocoder.yudao.module.cumu.dal.mysql.lefter.LefterMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cumu.enums.ErrorCodeConstants.*;

/**
 * 畜牧离场 Service 实现类
 *
 * @author 管理员
 */
@Service
@Validated
public class LefterServiceImpl implements LefterService {

    @Resource
    private LefterMapper lefterMapper;
    @Resource
    private CustomService customService;
    @Resource
    private CustomMapper customMapper;
    @Resource
    private LeftInfoService leftInfoService;
    @Resource
    private JoinerMapper joinerMapper;
    @Resource
    private AdminUserService userService;


    @Override
    public Long createLefter(LefterCreateReqVO createReqVO) {
        // 插入
        LefterDO lefter = LefterConvert.INSTANCE.convert(createReqVO);
        lefterMapper.insert(lefter);
        // 返回
        return lefter.getId();
    }

    @Override
    public void updateLefter(LefterUpdateReqVO updateReqVO) {
        // 校验存在
        validateLefterExists(updateReqVO.getId());
        // 更新
        LefterDO updateObj = LefterConvert.INSTANCE.convert(updateReqVO);
        //更新出场状态
        updateObj.setJoinleftFlag(JoinLeftEnmu.LEFT_FLAG.getStatus());
        lefterMapper.updateById(updateObj);
        JoinerDO joiner = new JoinerDO();
        joiner.setId(updateReqVO.getJoinId());
        joiner.setJoinleftFlag(JoinLeftEnmu.LEFT_FLAG.getStatus());
        joinerMapper.updateById(joiner);
    }

    @Override
    public void deleteLefter(Long id) {
        // 校验存在
        validateLefterExists(id);
        // 删除
        lefterMapper.deleteById(id);
    }

    private void validateLefterExists(Long id) {
        if (lefterMapper.selectById(id) == null) {
            throw exception(LEFTER_NOT_EXISTS);
        }
    }

    @Override
    public LefterResponsVO getLefter(Long id) {
        CustomDO custom = customService.getCustom(id);
        if(null == custom){
            throw exception(CUSTOM_NOT_EXISTS);
        }
        LefterDO lefterDO = lefterMapper.selectByCustomId(custom.getId());
        if(null ==lefterDO){
            throw exception(JOINER_NOT_EXISTS);
        }
        LefterResponsVO responsVO = getLefterByJoinId(lefterDO.getJoinId());
        responsVO.setCustomDO(custom);
        responsVO.setNumber(lefterDO.getNumber());
        responsVO.setCarId(lefterDO.getCarId());
        responsVO.setType(lefterDO.getType());
        responsVO.setJoinId(lefterDO.getJoinId());
        return responsVO;
    }
    public LefterResponsVO getLefterById(Long id){
        LefterDO lefterDO = lefterMapper.selectById(id);
        Long customId = lefterDO.getCustomId();
        CustomDO custom = customService.getCustom(customId);
        LeftInfoExportReqVO leftInfoExportReqVO = new LeftInfoExportReqVO();
        leftInfoExportReqVO.setLeftId(lefterDO.getId());
        List<LeftInfoDO> leftInfoList = leftInfoService.getLeftInfoList(leftInfoExportReqVO);
        LefterResponsVO responsVO = new LefterResponsVO();
        BeanUtils.copyProperties(lefterDO,responsVO);
        responsVO.setCustomDO(custom);
        responsVO.setLeftInfoDOList(leftInfoList);
        return responsVO;
    }

    @Override
    public LefterResponsVO getLefterByCustomId(Long customId) {
        return null;
    }

    @Override
    public LefterResponsVO getLefterByJoinId(Long id) {
//        LefterDO lefterDO = lefterMapper.selectOneByJoinId(id);
//        if(null==lefterDO){
//            throw exception(LEFTER_NOT_EXISTS);
//        }
        LefterDO lefterDO = lefterMapper.selectOne(new LambdaQueryWrapperX<LefterDO>()
                .eqIfPresent(LefterDO::getJoinId, id)
                .eq(LefterDO::getJoinleftFlag
                ,JoinLeftEnmu.JOIN_FLAG.getStatus())
        );
        if(null == lefterDO){
            throw exception(JOIN_ERROR);
        }
        LeftInfoExportReqVO leftInfoExportReqVO = new LeftInfoExportReqVO();
        leftInfoExportReqVO.setLeftId(lefterDO.getId());
        List<LeftInfoDO> leftInfoList = leftInfoService.getLeftInfoList(leftInfoExportReqVO);
        Long customId = lefterDO.getCustomId();
        CustomDO custom = customService.getCustom(customId);
        LefterResponsVO lefterResponsVO = new LefterResponsVO();
        BeanUtils.copyProperties(lefterDO,lefterResponsVO);
        lefterResponsVO.setLeftInfoDOList(leftInfoList);
        lefterResponsVO.setCustomDO(custom);
        return lefterResponsVO;
    }
    public LefterResponsVO getJoinerByVo(CustomDO vo) {
        CustomDO customDO = customMapper.selectOne(new LambdaQueryWrapperX<CustomDO>()
                .eqIfPresent(CustomDO::getId, vo.getId())
                .eqIfPresent(CustomDO::getCustomPhone, vo.getCustomPhone())
                .orderByDesc(CustomDO::getCreateTime));
        if(null ==customDO){
            throw exception(CUSTOM_NOT_EXISTS);
        }
        LefterDO lefterDO = lefterMapper.selectByCustomId(customDO.getId());
        if(null == lefterDO){
            throw exception(JOINER_NOT_EXISTS);
        }
        LeftInfoExportReqVO leftInfoExportReqVO = new LeftInfoExportReqVO();
        leftInfoExportReqVO.setLeftId(lefterDO.getId());
        List<LeftInfoDO> leftInfoList = leftInfoService.getLeftInfoList(leftInfoExportReqVO);

        LefterResponsVO lefterResponsVO = new LefterResponsVO();
        BeanUtils.copyProperties(lefterDO,lefterResponsVO);
        lefterResponsVO.setLeftInfoDOList(leftInfoList);
        lefterResponsVO.setCustomDO(customDO);
        return lefterResponsVO;
    }
    @Override
    public List<LefterDO> getLefterList(Collection<Long> ids) {
        return lefterMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<LefterResponsVO> getLefterPage(LefterPageReqVO pageReqVO) {
        PageResult<LefterDO> pageResult = lefterMapper.selectPage(pageReqVO);
        if(null==pageResult){
            return null;
        }
        List<LefterDO> lefterDOS = pageResult.getList();
        List<Long> customIds = lefterDOS.stream().map(item -> item.getCustomId()).collect(Collectors.toList());
        List<CustomDO> customList = customService.getCustomList(customIds);
        Collection<Long> userIds =pageResult.getList().stream().map(item -> Long.valueOf(item.getCreator())).collect(Collectors.toList());
        List<AdminUserDO> userList = userService.getUserList(userIds);
        List<LefterResponsVO> list = lefterDOS.stream().map(
                lefterDO -> {
                    LefterResponsVO responsVO = new LefterResponsVO();
                    BeanUtils.copyProperties(lefterDO, responsVO);
                    customList.stream().filter(customDO -> customDO.getId().equals(lefterDO.getCustomId()))
                            .findFirst()
                            .ifPresent(customDO -> responsVO.setCustomDO(customDO));
                    userList.stream().filter(user->user.getId().toString().equals(lefterDO.getCreator()))
                            .findFirst()
                            .ifPresent(user -> responsVO.setNikename(user.getNickname()));
                    return responsVO;
                }
        ).collect(Collectors.toList());

        PageResult<LefterResponsVO> jpageResult =new PageResult<>(list,pageResult.getTotal());
        return jpageResult;
    }

    @Override
    public List<LefterDO> getLefterList(LefterExportReqVO exportReqVO) {
        return lefterMapper.selectList(exportReqVO);
    }

}
