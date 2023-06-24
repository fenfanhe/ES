package com.example.es.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.es.modal.entity.Metric;
import com.example.es.modal.vo.MetricVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class MetricServiceTest {

    @Autowired
    private MetricService metricService;

    @Test
    void getMetrics() {
        Page<MetricVO> metrics = metricService.getMetrics(4, 10, null,null, null);
        Assert.isTrue(metrics.getRecords().size() == 10, "metrics size should be 10");
    }
}
