package cn.iocoder.yudao.module.cumu.service.statistics;

import cn.iocoder.yudao.module.cumu.controller.admin.joiner.Enmu.JoinLeftEnmu;
import cn.iocoder.yudao.module.cumu.dal.dataobject.lefter.LefterDO;
import cn.iocoder.yudao.module.cumu.dal.mysql.custom.CustomMapper;
import cn.iocoder.yudao.module.cumu.dal.mysql.joiner.JoinerMapper;
import cn.iocoder.yudao.module.cumu.dal.mysql.lefter.LefterMapper;
import cn.iocoder.yudao.module.cumu.dal.mysql.order.OrderMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class StatisticsServiceImpl {
    @Resource
    private CustomMapper customMapper;
    @Resource
    private JoinerMapper joinerMapper;
    @Resource
    private LefterMapper lefterMapper;
    @Resource
    private OrderMapper orderMapper;
    public List<Long> getStatistics(){
        ArrayList<Long> list = new ArrayList<>();
        Long aLong = customMapper.selectCount();
        Long aLong1 = joinerMapper.selectCount();
        Long aLong2 = lefterMapper.selectCount(new LambdaQueryWrapper<LefterDO>().eq(LefterDO::getJoinleftFlag, JoinLeftEnmu.LEFT_FLAG.getStatus()));
        Long aLong3 = orderMapper.selectCount();
        list.add(aLong);
        list.add(aLong1);
        list.add(aLong2);
        list.add(aLong3);
        return list;
    }
}
