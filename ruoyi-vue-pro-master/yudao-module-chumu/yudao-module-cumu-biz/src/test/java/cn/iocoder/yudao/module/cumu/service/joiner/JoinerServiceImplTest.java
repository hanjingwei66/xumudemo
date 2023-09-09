package cn.iocoder.yudao.module.cumu.service.joiner;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.cumu.controller.admin.joiner.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.joiner.JoinerDO;
import cn.iocoder.yudao.module.cumu.dal.mysql.joiner.JoinerMapper;
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
 * {@link JoinerServiceImpl} 的单元测试类
 *
 * @author 管理员
 */
@Import(JoinerServiceImpl.class)
public class JoinerServiceImplTest extends BaseDbUnitTest {

    @Resource
    private JoinerServiceImpl joinerService;

    @Resource
    private JoinerMapper joinerMapper;

    @Test
    public void testCreateJoiner_success() {
        // 准备参数
        JoinerCreateReqVO reqVO = randomPojo(JoinerCreateReqVO.class);

        // 调用
        Long joinerId = joinerService.createJoiner(reqVO);
        // 断言
        assertNotNull(joinerId);
        // 校验记录的属性是否正确
        JoinerDO joiner = joinerMapper.selectById(joinerId);
        assertPojoEquals(reqVO, joiner);
    }

    @Test
    public void testUpdateJoiner_success() {
        // mock 数据
        JoinerDO dbJoiner = randomPojo(JoinerDO.class);
        joinerMapper.insert(dbJoiner);// @Sql: 先插入出一条存在的数据
        // 准备参数
        JoinerUpdateReqVO reqVO = randomPojo(JoinerUpdateReqVO.class, o -> {
            o.setId(dbJoiner.getId()); // 设置更新的 ID
        });

        // 调用
        joinerService.updateJoiner(reqVO);
        // 校验是否更新正确
        JoinerDO joiner = joinerMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, joiner);
    }

    @Test
    public void testUpdateJoiner_notExists() {
        // 准备参数
        JoinerUpdateReqVO reqVO = randomPojo(JoinerUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> joinerService.updateJoiner(reqVO), JOINER_NOT_EXISTS);
    }

    @Test
    public void testDeleteJoiner_success() {
        // mock 数据
        JoinerDO dbJoiner = randomPojo(JoinerDO.class);
        joinerMapper.insert(dbJoiner);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbJoiner.getId();

        // 调用
        joinerService.deleteJoiner(id);
       // 校验数据不存在了
       assertNull(joinerMapper.selectById(id));
    }

    @Test
    public void testDeleteJoiner_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> joinerService.deleteJoiner(id), JOINER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetJoinerPage() {
       // mock 数据
       JoinerDO dbJoiner = randomPojo(JoinerDO.class, o -> { // 等会查询到
           o.setCustomId(null);
           o.setCarId(null);
           o.setType(null);
           o.setNumber(null);
           o.setRemeber(null);
           o.setJoinTime(null);
           o.setCreateTime(null);
       });
       joinerMapper.insert(dbJoiner);
       // 测试 customId 不匹配
       joinerMapper.insert(cloneIgnoreId(dbJoiner, o -> o.setCustomId(null)));
       // 测试 carId 不匹配
       joinerMapper.insert(cloneIgnoreId(dbJoiner, o -> o.setCarId(null)));
       // 测试 type 不匹配
       joinerMapper.insert(cloneIgnoreId(dbJoiner, o -> o.setType(null)));
       // 测试 number 不匹配
       joinerMapper.insert(cloneIgnoreId(dbJoiner, o -> o.setNumber(null)));
       // 测试 remeber 不匹配
       joinerMapper.insert(cloneIgnoreId(dbJoiner, o -> o.setRemeber(null)));
       // 测试 joinTime 不匹配
       joinerMapper.insert(cloneIgnoreId(dbJoiner, o -> o.setJoinTime(null)));
       // 测试 createTime 不匹配
       joinerMapper.insert(cloneIgnoreId(dbJoiner, o -> o.setCreateTime(null)));
       // 准备参数
       JoinerPageReqVO reqVO = new JoinerPageReqVO();
       reqVO.setCustomId(null);
       reqVO.setCarId(null);
       reqVO.setType(null);
       reqVO.setNumber(null);
       reqVO.setRemeber(null);
       reqVO.setJoinTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
//       PageResult<JoinerDO> pageResult = joinerService.getJoinerPage(reqVO);
       // 断言
//       assertEquals(1, pageResult.getTotal());
//       assertEquals(1, pageResult.getList().size());
//       assertPojoEquals(dbJoiner, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetJoinerList() {
       // mock 数据
       JoinerDO dbJoiner = randomPojo(JoinerDO.class, o -> { // 等会查询到
           o.setCustomId(null);
           o.setCarId(null);
           o.setType(null);
           o.setNumber(null);
           o.setRemeber(null);
           o.setJoinTime(null);
           o.setCreateTime(null);
       });
       joinerMapper.insert(dbJoiner);
       // 测试 customId 不匹配
       joinerMapper.insert(cloneIgnoreId(dbJoiner, o -> o.setCustomId(null)));
       // 测试 carId 不匹配
       joinerMapper.insert(cloneIgnoreId(dbJoiner, o -> o.setCarId(null)));
       // 测试 type 不匹配
       joinerMapper.insert(cloneIgnoreId(dbJoiner, o -> o.setType(null)));
       // 测试 number 不匹配
       joinerMapper.insert(cloneIgnoreId(dbJoiner, o -> o.setNumber(null)));
       // 测试 remeber 不匹配
       joinerMapper.insert(cloneIgnoreId(dbJoiner, o -> o.setRemeber(null)));
       // 测试 joinTime 不匹配
       joinerMapper.insert(cloneIgnoreId(dbJoiner, o -> o.setJoinTime(null)));
       // 测试 createTime 不匹配
       joinerMapper.insert(cloneIgnoreId(dbJoiner, o -> o.setCreateTime(null)));
       // 准备参数
       JoinerExportReqVO reqVO = new JoinerExportReqVO();
       reqVO.setCustomId(null);
       reqVO.setCarId(null);
       reqVO.setType(null);
       reqVO.setNumber(null);
       reqVO.setRemeber(null);
       reqVO.setJoinTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<JoinerDO> list = joinerService.getJoinerList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbJoiner, list.get(0));
    }

}
