package cn.iocoder.yudao.module.cumu.service.joininfo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.cumu.controller.admin.joininfo.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joininfo.JoinInfoDO;
import cn.iocoder.yudao.module.cumu.dal.mysql.joininfo.JoinInfoMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.cumu.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link JoinInfoServiceImpl} 的单元测试类
 *
 * @author 管理员
 */
@Import(JoinInfoServiceImpl.class)
public class JoinInfoServiceImplTest extends BaseDbUnitTest {

    @Resource
    private JoinInfoServiceImpl joinInfoService;

    @Resource
    private JoinInfoMapper joinInfoMapper;

    @Test
    public void testCreateJoinInfo_success() {
        // 准备参数
        JoinInfoCreateReqVO reqVO = randomPojo(JoinInfoCreateReqVO.class);

        // 调用
        Long joinInfoId = joinInfoService.createJoinInfo(reqVO);
        // 断言
        assertNotNull(joinInfoId);
        // 校验记录的属性是否正确
        JoinInfoDO joinInfo = joinInfoMapper.selectById(joinInfoId);
        assertPojoEquals(reqVO, joinInfo);
    }

    @Test
    public void testUpdateJoinInfo_success() {
        // mock 数据
        JoinInfoDO dbJoinInfo = randomPojo(JoinInfoDO.class);
        joinInfoMapper.insert(dbJoinInfo);// @Sql: 先插入出一条存在的数据
        // 准备参数
        JoinInfoUpdateReqVO reqVO = randomPojo(JoinInfoUpdateReqVO.class, o -> {
            o.setId(dbJoinInfo.getId()); // 设置更新的 ID
        });

        // 调用
        joinInfoService.updateJoinInfo(reqVO);
        // 校验是否更新正确
        JoinInfoDO joinInfo = joinInfoMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, joinInfo);
    }

    @Test
    public void testUpdateJoinInfo_notExists() {
        // 准备参数
        JoinInfoUpdateReqVO reqVO = randomPojo(JoinInfoUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> joinInfoService.updateJoinInfo(reqVO), JOIN_INFO_NOT_EXISTS);
    }

    @Test
    public void testDeleteJoinInfo_success() {
        // mock 数据
        JoinInfoDO dbJoinInfo = randomPojo(JoinInfoDO.class);
        joinInfoMapper.insert(dbJoinInfo);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbJoinInfo.getId();

        // 调用
        joinInfoService.deleteJoinInfo(id);
       // 校验数据不存在了
       assertNull(joinInfoMapper.selectById(id));
    }

    @Test
    public void testDeleteJoinInfo_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> joinInfoService.deleteJoinInfo(id), JOIN_INFO_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetJoinInfoPage() {
       // mock 数据
       JoinInfoDO dbJoinInfo = randomPojo(JoinInfoDO.class, o -> { // 等会查询到
           o.setJoinId(null);
           o.setType(null);
           o.setNum(null);
       });
       joinInfoMapper.insert(dbJoinInfo);
       // 测试 joinId 不匹配
       joinInfoMapper.insert(cloneIgnoreId(dbJoinInfo, o -> o.setJoinId(null)));
       // 测试 type 不匹配
       joinInfoMapper.insert(cloneIgnoreId(dbJoinInfo, o -> o.setType(null)));
       // 测试 num 不匹配
       joinInfoMapper.insert(cloneIgnoreId(dbJoinInfo, o -> o.setNum(null)));
       // 准备参数
       JoinInfoPageReqVO reqVO = new JoinInfoPageReqVO();
       reqVO.setJoinId(null);
       reqVO.setType(null);
       reqVO.setNum(null);

       // 调用
       PageResult<JoinInfoDO> pageResult = joinInfoService.getJoinInfoPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbJoinInfo, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetJoinInfoList() {
       // mock 数据
       JoinInfoDO dbJoinInfo = randomPojo(JoinInfoDO.class, o -> { // 等会查询到
           o.setJoinId(null);
           o.setType(null);
           o.setNum(null);
       });
       joinInfoMapper.insert(dbJoinInfo);
       // 测试 joinId 不匹配
       joinInfoMapper.insert(cloneIgnoreId(dbJoinInfo, o -> o.setJoinId(null)));
       // 测试 type 不匹配
       joinInfoMapper.insert(cloneIgnoreId(dbJoinInfo, o -> o.setType(null)));
       // 测试 num 不匹配
       joinInfoMapper.insert(cloneIgnoreId(dbJoinInfo, o -> o.setNum(null)));
       // 准备参数
       JoinInfoExportReqVO reqVO = new JoinInfoExportReqVO();
       reqVO.setJoinId(null);
       reqVO.setType(null);
       reqVO.setNum(null);

       // 调用
       List<JoinInfoDO> list = joinInfoService.getJoinInfoList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbJoinInfo, list.get(0));
    }

}
