package cn.iocoder.yudao.module.cumu.service.juan;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.cumu.controller.admin.juan.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.juan.JuanDO;
import cn.iocoder.yudao.module.cumu.dal.mysql.juan.JuanMapper;
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
 * {@link JuanServiceImpl} 的单元测试类
 *
 * @author 管理员
 */
@Import(JuanServiceImpl.class)
public class JuanServiceImplTest extends BaseDbUnitTest {

    @Resource
    private JuanServiceImpl juanService;

    @Resource
    private JuanMapper juanMapper;

    @Test
    public void testCreateJuan_success() {
        // 准备参数
        JuanCreateReqVO reqVO = randomPojo(JuanCreateReqVO.class);

        // 调用
        Integer juanId = juanService.createJuan(reqVO);
        // 断言
        assertNotNull(juanId);
        // 校验记录的属性是否正确
        JuanDO juan = juanMapper.selectById(juanId);
        assertPojoEquals(reqVO, juan);
    }

    @Test
    public void testUpdateJuan_success() {
        // mock 数据
        JuanDO dbJuan = randomPojo(JuanDO.class);
        juanMapper.insert(dbJuan);// @Sql: 先插入出一条存在的数据
        // 准备参数
        JuanUpdateReqVO reqVO = randomPojo(JuanUpdateReqVO.class, o -> {
            o.setId(dbJuan.getId()); // 设置更新的 ID
        });

        // 调用
        juanService.updateJuan(reqVO);
        // 校验是否更新正确
        JuanDO juan = juanMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, juan);
    }

    @Test
    public void testUpdateJuan_notExists() {
        // 准备参数
        JuanUpdateReqVO reqVO = randomPojo(JuanUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> juanService.updateJuan(reqVO), JUAN_NOT_EXISTS);
    }

    @Test
    public void testDeleteJuan_success() {
        // mock 数据
        JuanDO dbJuan = randomPojo(JuanDO.class);
        juanMapper.insert(dbJuan);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbJuan.getId();

        // 调用
        juanService.deleteJuan(id);
       // 校验数据不存在了
       assertNull(juanMapper.selectById(id));
    }

    @Test
    public void testDeleteJuan_notExists() {
        // 准备参数
        Integer id = randomIntegerId();

        // 调用, 并断言异常
        assertServiceException(() -> juanService.deleteJuan(id), JUAN_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetJuanPage() {
       // mock 数据
       JuanDO dbJuan = randomPojo(JuanDO.class, o -> { // 等会查询到
           o.setRegion(null);
           o.setRegionNum(null);
           o.setStatus(null);
           o.setType(null);
           o.setEndTime(null);
           o.setCustomId(null);
           o.setCreateTime(null);
       });
       juanMapper.insert(dbJuan);
       // 测试 region 不匹配
       juanMapper.insert(cloneIgnoreId(dbJuan, o -> o.setRegion(null)));
       // 测试 regionNum 不匹配
       juanMapper.insert(cloneIgnoreId(dbJuan, o -> o.setRegionNum(null)));
       // 测试 status 不匹配
       juanMapper.insert(cloneIgnoreId(dbJuan, o -> o.setStatus(null)));
       // 测试 type 不匹配
       juanMapper.insert(cloneIgnoreId(dbJuan, o -> o.setType(null)));
       // 测试 endTime 不匹配
       juanMapper.insert(cloneIgnoreId(dbJuan, o -> o.setEndTime(null)));
       // 测试 customId 不匹配
       juanMapper.insert(cloneIgnoreId(dbJuan, o -> o.setCustomId(null)));
       // 测试 createTime 不匹配
       juanMapper.insert(cloneIgnoreId(dbJuan, o -> o.setCreateTime(null)));
       // 准备参数
       JuanPageReqVO reqVO = new JuanPageReqVO();
       reqVO.setRegion(null);
       reqVO.setRegionNum(null);
       reqVO.setStatus(null);
       reqVO.setType(null);
       reqVO.setEndTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCustomId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<JuanDO> pageResult = juanService.getJuanPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbJuan, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetJuanList() {
       // mock 数据
       JuanDO dbJuan = randomPojo(JuanDO.class, o -> { // 等会查询到
           o.setRegion(null);
           o.setRegionNum(null);
           o.setStatus(null);
           o.setType(null);
           o.setEndTime(null);
           o.setCustomId(null);
           o.setCreateTime(null);
       });
       juanMapper.insert(dbJuan);
       // 测试 region 不匹配
       juanMapper.insert(cloneIgnoreId(dbJuan, o -> o.setRegion(null)));
       // 测试 regionNum 不匹配
       juanMapper.insert(cloneIgnoreId(dbJuan, o -> o.setRegionNum(null)));
       // 测试 status 不匹配
       juanMapper.insert(cloneIgnoreId(dbJuan, o -> o.setStatus(null)));
       // 测试 type 不匹配
       juanMapper.insert(cloneIgnoreId(dbJuan, o -> o.setType(null)));
       // 测试 endTime 不匹配
       juanMapper.insert(cloneIgnoreId(dbJuan, o -> o.setEndTime(null)));
       // 测试 customId 不匹配
       juanMapper.insert(cloneIgnoreId(dbJuan, o -> o.setCustomId(null)));
       // 测试 createTime 不匹配
       juanMapper.insert(cloneIgnoreId(dbJuan, o -> o.setCreateTime(null)));
       // 准备参数
       JuanExportReqVO reqVO = new JuanExportReqVO();
       reqVO.setRegion(null);
       reqVO.setRegionNum(null);
       reqVO.setStatus(null);
       reqVO.setType(null);
       reqVO.setEndTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCustomId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<JuanDO> list = juanService.getJuanList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbJuan, list.get(0));
    }

}
