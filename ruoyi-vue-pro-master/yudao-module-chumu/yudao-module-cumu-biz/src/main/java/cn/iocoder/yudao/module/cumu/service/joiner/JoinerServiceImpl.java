package cn.iocoder.yudao.module.cumu.service.joiner;

import cn.hutool.core.util.IdUtil;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.dict.core.util.DictFrameworkUtils;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.cumu.controller.admin.custom.vo.CustomBaseVO;
import cn.iocoder.yudao.module.cumu.controller.admin.joiner.Enmu.JoinLeftEnmu;
import cn.iocoder.yudao.module.cumu.controller.admin.joininfo.vo.JoinInfoExportReqVO;
import cn.iocoder.yudao.module.cumu.controller.admin.lefter.vo.LefterResponsVO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.custom.CustomDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo.JoinInfoDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.juan.JuanDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.lefter.LefterDO;
import cn.iocoder.yudao.module.cumu.dal.dataobject.leftinfo.LeftInfoDO;
import cn.iocoder.yudao.module.cumu.dal.mysql.custom.CustomMapper;
import cn.iocoder.yudao.module.cumu.dal.mysql.joininfo.JoinInfoMapper;
import cn.iocoder.yudao.module.cumu.dal.mysql.juan.JuanMapper;
import cn.iocoder.yudao.module.cumu.dal.mysql.lefter.LefterMapper;
import cn.iocoder.yudao.module.cumu.dal.mysql.leftinfo.LeftInfoMapper;
import cn.iocoder.yudao.module.cumu.service.custom.CustomService;
import cn.iocoder.yudao.module.cumu.service.joininfo.JoinInfoService;
import cn.iocoder.yudao.module.cumu.service.lefter.LefterService;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.cumu.controller.admin.joiner.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joiner.JoinerDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.cumu.convert.joiner.JoinerConvert;
import cn.iocoder.yudao.module.cumu.dal.mysql.joiner.JoinerMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.cumu.enums.ErrorCodeConstants.*;

/**
 * 畜牧进场 Service 实现类
 *
 * @author 管理员
 */
@Service
@Validated
public class JoinerServiceImpl implements JoinerService {

    @Resource
    private JoinerMapper joinerMapper;
    @Resource
    private CustomService customService;
    @Resource
    private CustomMapper customMapper;
    @Resource
    private JoinInfoService joinInfoService;
    @Resource
    private JoinInfoMapper joinInfoMapper;
    @Resource
    private LefterMapper lefterMapper;
    @Resource
    private LeftInfoMapper leftInfoMapper;
    @Resource
    private AdminUserService userService;
    @Resource
    private JuanMapper juanMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createJoiner(JoinerCreateReqVO createReqVO) {
        //校验临时圈号
        this.checkRegion(createReqVO);
        // 插入
        JoinerDO joiner = JoinerConvert.INSTANCE.convert(createReqVO);
        Long joinId =IdUtil.getSnowflakeNextId();
        joiner.setId(joinId);
        joiner.setJoinleftFlag(JoinLeftEnmu.JOIN_FLAG.getStatus());
        String typeString="";
        List<JoinInfoDO> joinInfoDOS = createReqVO.getJoinInfoDOS();
        for (JoinInfoDO joinInfoDO : joinInfoDOS) {
            String label = DictFrameworkUtils.getDictDataLabel("cumu_type", joinInfoDO.getType());
            if(joinInfoDO.getNum()!=0) {
                typeString += label + ":" + joinInfoDO.getNum() + ";";
            }
        }
        joiner.setTypeString(typeString);
        joinerMapper.insert(joiner);

        List<JoinInfoDO> collect = joinInfoDOS.stream().
                map(item -> {
                    item.setJoinId(joiner.getId());
                    item.setId(IdUtil.getSnowflakeNextId());
                            return item;
                }).collect(Collectors.toList());

        joinInfoMapper.insertBatch(collect);

        //同步出场登记
        Long lefterId =IdUtil.getSnowflakeNextId();
        LefterDO lefterDO = new LefterDO();
        BeanUtils.copyProperties(createReqVO,lefterDO);
        lefterDO.setId(lefterId);
        lefterDO.setJoinId(joinId);
        lefterDO.setJoinleftFlag(JoinLeftEnmu.JOIN_FLAG.getStatus());
        lefterDO.setTypeString(typeString);
        lefterMapper.insert(lefterDO);
        List<LeftInfoDO> collect1 = joinInfoDOS.stream().
                map(item -> {
                    LeftInfoDO leftInfoDO = new LeftInfoDO();
                    leftInfoDO.setLeftId(lefterId);
                    leftInfoDO.setId(IdUtil.getSnowflakeNextId());
                    leftInfoDO.setType(item.getType());
                    leftInfoDO.setNum(item.getNum());
                    return leftInfoDO;
                }).collect(Collectors.toList());
        leftInfoMapper.insertBatch(collect1);
        // 返回
        return joiner.getId();
    }
    public void  checkRegion(JoinerBaseVO createReqVO) {
        Integer region =null;
        Integer regionNum =null;
        if(null == createReqVO.getRegion()||0==createReqVO.getRegion()){
            region = createReqVO.getTempRegion();
            regionNum = createReqVO.getTempRegionNum();
        }

        if(null==region||null==regionNum ||0==region|| 0==regionNum){
            return;
        }
        JuanDO juanDO = juanMapper.selectOne(new LambdaQueryWrapperX<JuanDO>()
                .eq(JuanDO::getRegion, region).eq(JuanDO::getRegionNum, regionNum));
        if (null == juanDO){
            throw exception(TEMP_JUAN_NOT_EXISTS);
        }
        if(juanDO.getStatus() ==1){
            throw exception(TEMP_JUAN_EXISTS);
        }
        juanDO.setStatus(0);
        juanDO.setEndTime(null);
        juanDO.setCustomId(null);
        juanMapper.update(juanDO,new LambdaUpdateWrapper<JuanDO>()
                .eq(JuanDO::getRegion, juanDO.getRegion()).eq(JuanDO::getRegionNum, juanDO.getRegionNum()));
//        JuanDO juanDO = new JuanDO();
        juanDO.setStatus(1);
        juanDO.setEndTime(LocalDateTime.now());
        juanDO.setCustomId(createReqVO.getCustomId());
        juanMapper.update(juanDO,new LambdaUpdateWrapper<JuanDO>()
                .eq(JuanDO::getRegion, region)
                .eq(JuanDO::getRegionNum, regionNum)
        );
    }
    @Override
    public void updateJoiner(JoinerUpdateReqVO updateReqVO) {
        // 校验存在
        validateJoinerExists(updateReqVO.getId());
        this.checkRegion(updateReqVO);
        // 更新
        JoinerDO updateObj = JoinerConvert.INSTANCE.convert(updateReqVO);
        List<JoinInfoDO> joinInfoDOList = updateReqVO.getJoinInfoDOList();
        String typeString ="";
        Integer sum =0;
        for (JoinInfoDO joinInfoDO : joinInfoDOList) {
            String label = DictFrameworkUtils.getDictDataLabel("cumu_type", joinInfoDO.getType());
            if(joinInfoDO.getNum()>0) {
                typeString += label + ":" + joinInfoDO.getNum() + ";";
                sum+=joinInfoDO.getNum();
            }
        }
        updateObj.setTypeString(typeString);
        updateObj.setNumber(sum);
        joinInfoMapper.updateBatch(joinInfoDOList);
        LefterDO lefterDO = lefterMapper.selectOne(new LambdaQueryWrapperX<LefterDO>()
                .eqIfPresent(LefterDO::getJoinId, updateReqVO.getId())
                .eq(LefterDO::getJoinleftFlag,JoinLeftEnmu.JOIN_FLAG.getStatus())
        );
        //"当前用户已离场不能修改"
        if(null==lefterDO){
            throw exception(NOT_LETERED);
        }
        Long lefterId = lefterDO.getId();
        lefterDO.setId(lefterId);
        lefterDO.setTypeString(typeString);
        lefterDO.setNumber(sum);
        lefterMapper.updateById(lefterDO);
        Long lefterDOId = lefterDO.getId();
        List<LeftInfoDO> leftInfoDOs = leftInfoMapper.selectList("left_id", lefterDOId);
        for (LeftInfoDO leftInfoDO : leftInfoDOs) {
            for (JoinInfoDO joinInfoDO : joinInfoDOList) {
                if(leftInfoDO.getType().equals(joinInfoDO.getType())){
                    leftInfoDO.setNum(joinInfoDO.getNum());
                    break;
                }
            }
        }

        leftInfoMapper.updateBatch(leftInfoDOs);
        joinerMapper.updateById(updateObj);
    }

    @Override
    public void deleteJoiner(Long id) {
        // 校验存在
        validateJoinerExists(id);
        // 删除
        joinerMapper.deleteById(id);
    }

    private void validateJoinerExists(Long id) {
        if (joinerMapper.selectById(id) == null) {
            throw exception(JOINER_NOT_EXISTS);
        }
    }

    @Override
    public JoinerResponsVO getJoiner(Long id) {
        CustomDO custom = customService.getCustom(id);
        if(null == custom){
            throw exception(CUSTOM_NOT_EXISTS);
        }
        JoinerDO joinerDO = joinerMapper.selectByCustomId(id);

        JoinInfoExportReqVO joinInfoExportReqVO = new JoinInfoExportReqVO();
        JoinerResponsVO joinerResponsVO = new JoinerResponsVO();
        joinerResponsVO.setCustomDO(custom);
        if(null != joinerDO){
            joinInfoExportReqVO.setJoinId(joinerDO.getId());
            List<JoinInfoDO> joinInfoList = joinInfoService.getJoinInfoList(joinInfoExportReqVO);
            joinerResponsVO.setJoinInfoDOList(joinInfoList);
            joinerResponsVO.setNumber(joinerDO.getNumber());
            joinerResponsVO.setRemeber(joinerDO.getRemeber());
            joinerResponsVO.setJoinTime(joinerDO.getJoinTime());
            joinerResponsVO.setJoinId(joinerDO.getId());
        }
        return joinerResponsVO;
    }
    public JoinerResponsVO getJoinerById(Long id) {
        JoinerDO joinerDO = joinerMapper.selectById(id);
        Long customId = joinerDO.getCustomId();
        CustomDO custom = customService.getCustom(customId);
        JoinInfoExportReqVO joinInfoExportReqVO = new JoinInfoExportReqVO();
        joinInfoExportReqVO.setJoinId(joinerDO.getId());
        List<JoinInfoDO> joinInfoList = joinInfoService.getJoinInfoList(joinInfoExportReqVO);
        JoinerResponsVO joinerResponsVO = new JoinerResponsVO();
//        JoinerRespVO convert = JoinerConvert.INSTANCE.convert(joinerDO);
        BeanUtils.copyProperties(joinerDO,joinerResponsVO);
        joinerResponsVO.setCustomDO(custom);
        joinerResponsVO.setJoinInfoDOList(joinInfoList);
        return joinerResponsVO;
    }

    @Override
    public JoinerResponsVO getJoinerByVo(CustomDO vo) {
        return null;
    }

    @Override
    public List<JoinerDO> getJoinerList(Collection<Long> ids) {
        return joinerMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<JoinerResponsVO> getJoinerPage(JoinerPageReqVO pageReqVO) {
        PageResult<JoinerDO> pageResult = joinerMapper.selectPage(pageReqVO);
        if(pageResult.getList().size()==0){
            PageResult<JoinerResponsVO> jpageResult =new PageResult<JoinerResponsVO>();
            return  jpageResult;
        }
        List<JoinerDO> joinerDOs = pageResult.getList();
        List<Long> customIds = pageResult.getList().stream().map(item -> item.getCustomId()).collect(Collectors.toList());
        List<CustomDO> customList = customService.getCustomList(customIds);
//        List<JoinerResponsVO> list = new ArrayList<>();
//
//        for (JoinerDO joinerDO : joinerDOs) {
//            JoinerResponsVO joinerResponsVO = new JoinerResponsVO();
//            BeanUtils.copyProperties(joinerDO,joinerResponsVO);
//            for (CustomDO customDO : customList) {
//                if(customDO.getId().equals(joinerDO.getCustomId())){
//                    joinerResponsVO.setCustomDO(customDO);
//                    list.add(joinerResponsVO);
//                }
//            }
//        }
        Collection<Long> userIds =pageResult.getList().stream().map(item -> Long.valueOf(item.getCreator())).collect(Collectors.toList());
        List<AdminUserDO> userList = userService.getUserList(userIds);
        List<JoinerResponsVO> list = joinerDOs.stream()
                .map(joinerDO -> {
                    JoinerResponsVO joinerResponsVO = new JoinerResponsVO();
                    BeanUtils.copyProperties(joinerDO, joinerResponsVO);
                    customList.stream()
                            .filter(customDO -> customDO.getId().equals(joinerDO.getCustomId()))
                            .findFirst()
                            .ifPresent(customDO -> joinerResponsVO.setCustomDO(customDO));
                    userList.stream().filter(user->user.getId().toString().equals(joinerDO.getCreator()))
                            .findFirst()
                            .ifPresent(user -> joinerResponsVO.setNikname(user.getNickname()));
                    return joinerResponsVO;
                })
                .collect(Collectors.toList());
        PageResult<JoinerResponsVO> jpageResult =new PageResult<>(list,pageResult.getTotal());
        return jpageResult;
    }

    @Override
    public List<JoinerDO> getJoinerList(JoinerExportReqVO exportReqVO) {
        return joinerMapper.selectList(exportReqVO);
    }

}
