package cn.iocoder.yudao.module.cumu.service.lefter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.cumu.controller.admin.lefter.vo.*;
import cn.iocoder.yudao.module.cumu.dal.dataobject.lefter.LefterDO;
import cn.iocoder.yudao.module.cumu.dal.mysql.lefter.LefterMapper;
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
 * {@link LefterServiceImpl} 的单元测试类
 *
 * @author 管理员
 */
@Import(LefterServiceImpl.class)
public class LefterServiceImplTest extends BaseDbUnitTest {

    @Resource
    private LefterServiceImpl lefterService;

    @Resource
    private LefterMapper lefterMapper;

    @Test
    public void testCreateLefter_success() {
        // 准备参数
        LefterCreateReqVO reqVO = randomPojo(LefterCreateReqVO.class);

        // 调用
        Long lefterId = lefterService.createLefter(reqVO);
        // 断言
        assertNotNull(lefterId);
        // 校验记录的属性是否正确
        LefterDO lefter = lefterMapper.selectById(lefterId);
        assertPojoEquals(reqVO, lefter);
    }

    @Test
    public void testUpdateLefter_success() {
        // mock 数据
        LefterDO dbLefter = randomPojo(LefterDO.class);
        lefterMapper.insert(dbLefter);// @Sql: 先插入出一条存在的数据
        // 准备参数
        LefterUpdateReqVO reqVO = randomPojo(LefterUpdateReqVO.class, o -> {
            o.setId(dbLefter.getId()); // 设置更新的 ID
        });

        // 调用
        lefterService.updateLefter(reqVO);
        // 校验是否更新正确
        LefterDO lefter = lefterMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, lefter);
    }

    @Test
    public void testUpdateLefter_notExists() {
        // 准备参数
        LefterUpdateReqVO reqVO = randomPojo(LefterUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> lefterService.updateLefter(reqVO), LEFTER_NOT_EXISTS);
    }

    @Test
    public void testDeleteLefter_success() {
        // mock 数据
        LefterDO dbLefter = randomPojo(LefterDO.class);
        lefterMapper.insert(dbLefter);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbLefter.getId();

        // 调用
        lefterService.deleteLefter(id);
       // 校验数据不存在了
       assertNull(lefterMapper.selectById(id));
    }

    @Test
    public void testDeleteLefter_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> lefterService.deleteLefter(id), LEFTER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetLefterPage() {
       // mock 数据
       LefterDO dbLefter = randomPojo(LefterDO.class, o -> { // 等会查询到
           o.setCustomId(null);
           o.setCarId(null);
           o.setType(null);
           o.setNumber(null);
           o.setNumberReal(null);
           o.setRemeber(null);
           o.setJoinTime(null);
           o.setCreateTime(null);
       });
       lefterMapper.insert(dbLefter);
       // 测试 customId 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setCustomId(null)));
       // 测试 carId 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setCarId(null)));
       // 测试 type 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setType(null)));
       // 测试 number 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setNumber(null)));
       // 测试 numberReal 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setNumberReal(null)));
       // 测试 remeber 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setRemeber(null)));
       // 测试 joinTime 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setJoinTime(null)));
       // 测试 createTime 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setCreateTime(null)));
       // 准备参数
       LefterPageReqVO reqVO = new LefterPageReqVO();
       reqVO.setCustomId(null);
       reqVO.setCarId(null);
       reqVO.setType(null);
       reqVO.setNumber(null);
       reqVO.setNumberReal(null);
       reqVO.setRemeber(null);
       reqVO.setJoinTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
//       PageResult<LefterDO> pageResult = lefterService.getLefterPage(reqVO);
       // 断言
//       assertEquals(1, pageResult.getTotal());
//       assertEquals(1, pageResult.getList().size());
//       assertPojoEquals(dbLefter, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetLefterList() {
       // mock 数据
       LefterDO dbLefter = randomPojo(LefterDO.class, o -> { // 等会查询到
           o.setCustomId(null);
           o.setCarId(null);
           o.setType(null);
           o.setNumber(null);
           o.setNumberReal(null);
           o.setRemeber(null);
           o.setJoinTime(null);
           o.setCreateTime(null);
       });
       lefterMapper.insert(dbLefter);
       // 测试 customId 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setCustomId(null)));
       // 测试 carId 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setCarId(null)));
       // 测试 type 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setType(null)));
       // 测试 number 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setNumber(null)));
       // 测试 numberReal 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setNumberReal(null)));
       // 测试 remeber 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setRemeber(null)));
       // 测试 joinTime 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setJoinTime(null)));
       // 测试 createTime 不匹配
       lefterMapper.insert(cloneIgnoreId(dbLefter, o -> o.setCreateTime(null)));
       // 准备参数
       LefterExportReqVO reqVO = new LefterExportReqVO();
       reqVO.setCustomId(null);
       reqVO.setCarId(null);
       reqVO.setType(null);
       reqVO.setNumber(null);
       reqVO.setNumberReal(null);
       reqVO.setRemeber(null);
       reqVO.setJoinTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<LefterDO> list = lefterService.getLefterList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbLefter, list.get(0));
    }

}
