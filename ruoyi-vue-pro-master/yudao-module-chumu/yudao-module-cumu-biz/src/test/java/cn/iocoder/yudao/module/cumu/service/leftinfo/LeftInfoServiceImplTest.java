package cn.iocoder.yudao.module.cumu.service.leftinfo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.cumu.controller.admin.leftinfo.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.leftinfo.LeftInfoDO;
import cn.iocoder.yudao.module.cumu.dal.mysql.leftinfo.LeftInfoMapper;
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
 * {@link LeftInfoServiceImpl} 的单元测试类
 *
 * @author 管理员
 */
@Import(LeftInfoServiceImpl.class)
public class LeftInfoServiceImplTest extends BaseDbUnitTest {

    @Resource
    private LeftInfoServiceImpl leftInfoService;

    @Resource
    private LeftInfoMapper leftInfoMapper;

    @Test
    public void testCreateLeftInfo_success() {
        // 准备参数
        LeftInfoCreateReqVO reqVO = randomPojo(LeftInfoCreateReqVO.class);

        // 调用
        Long leftInfoId = leftInfoService.createLeftInfo(reqVO);
        // 断言
        assertNotNull(leftInfoId);
        // 校验记录的属性是否正确
        LeftInfoDO leftInfo = leftInfoMapper.selectById(leftInfoId);
        assertPojoEquals(reqVO, leftInfo);
    }

    @Test
    public void testUpdateLeftInfo_success() {
        // mock 数据
        LeftInfoDO dbLeftInfo = randomPojo(LeftInfoDO.class);
        leftInfoMapper.insert(dbLeftInfo);// @Sql: 先插入出一条存在的数据
        // 准备参数
        LeftInfoUpdateReqVO reqVO = randomPojo(LeftInfoUpdateReqVO.class, o -> {
            o.setId(dbLeftInfo.getId()); // 设置更新的 ID
        });

        // 调用
        leftInfoService.updateLeftInfo(reqVO);
        // 校验是否更新正确
        LeftInfoDO leftInfo = leftInfoMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, leftInfo);
    }

    @Test
    public void testUpdateLeftInfo_notExists() {
        // 准备参数
        LeftInfoUpdateReqVO reqVO = randomPojo(LeftInfoUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> leftInfoService.updateLeftInfo(reqVO), LEFT_INFO_NOT_EXISTS);
    }

    @Test
    public void testDeleteLeftInfo_success() {
        // mock 数据
        LeftInfoDO dbLeftInfo = randomPojo(LeftInfoDO.class);
        leftInfoMapper.insert(dbLeftInfo);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbLeftInfo.getId();

        // 调用
        leftInfoService.deleteLeftInfo(id);
       // 校验数据不存在了
       assertNull(leftInfoMapper.selectById(id));
    }

    @Test
    public void testDeleteLeftInfo_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> leftInfoService.deleteLeftInfo(id), LEFT_INFO_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetLeftInfoPage() {
       // mock 数据
       LeftInfoDO dbLeftInfo = randomPojo(LeftInfoDO.class, o -> { // 等会查询到
           o.setLeftId(null);
           o.setType(null);
           o.setNum(null);
       });
       leftInfoMapper.insert(dbLeftInfo);
       // 测试 leftId 不匹配
       leftInfoMapper.insert(cloneIgnoreId(dbLeftInfo, o -> o.setLeftId(null)));
       // 测试 type 不匹配
       leftInfoMapper.insert(cloneIgnoreId(dbLeftInfo, o -> o.setType(null)));
       // 测试 num 不匹配
       leftInfoMapper.insert(cloneIgnoreId(dbLeftInfo, o -> o.setNum(null)));
       // 准备参数
       LeftInfoPageReqVO reqVO = new LeftInfoPageReqVO();
       reqVO.setLeftId(null);
       reqVO.setType(null);
       reqVO.setNum(null);

       // 调用
       PageResult<LeftInfoDO> pageResult = leftInfoService.getLeftInfoPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbLeftInfo, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetLeftInfoList() {
       // mock 数据
       LeftInfoDO dbLeftInfo = randomPojo(LeftInfoDO.class, o -> { // 等会查询到
           o.setLeftId(null);
           o.setType(null);
           o.setNum(null);
       });
       leftInfoMapper.insert(dbLeftInfo);
       // 测试 leftId 不匹配
       leftInfoMapper.insert(cloneIgnoreId(dbLeftInfo, o -> o.setLeftId(null)));
       // 测试 type 不匹配
       leftInfoMapper.insert(cloneIgnoreId(dbLeftInfo, o -> o.setType(null)));
       // 测试 num 不匹配
       leftInfoMapper.insert(cloneIgnoreId(dbLeftInfo, o -> o.setNum(null)));
       // 准备参数
       LeftInfoExportReqVO reqVO = new LeftInfoExportReqVO();
       reqVO.setLeftId(null);
       reqVO.setType(null);
       reqVO.setNum(null);

       // 调用
       List<LeftInfoDO> list = leftInfoService.getLeftInfoList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbLeftInfo, list.get(0));
    }

}
