package cn.iocoder.yudao.module.cumu.controller.admin.statistics;

import cn.iocoder.yudao.module.cumu.service.statistics.StatisticsServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Tag(name = "管理后台 - 统计")
@RestController
@RequestMapping("/cumu/statistics")
@Validated
public class StatisticsController {
    @Resource
    private StatisticsServiceImpl statisticsService;
    @GetMapping("/getStatistics")
    public List<Long> getStatistics(){
        return statisticsService.getStatistics();
    }
}
