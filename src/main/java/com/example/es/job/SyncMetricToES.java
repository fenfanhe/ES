package com.example.es.job;

import com.example.es.dao.MetricESDao;
import com.example.es.modal.entity.MetricES;
import com.example.es.modal.entity.Metric;
import com.example.es.service.MetricService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

//开启component启动时执行
//@Component
@Slf4j
public class SyncMetricToES implements CommandLineRunner {
    @Resource
    private MetricService metricService;

    @Resource
    private MetricESDao metricESDao;

    @Override
    public void run(String... args) throws Exception {
        List<Metric> allMetrics = metricService.getBaseMapper().selectList(null);
        List<MetricES> metricESList = new ArrayList<>();

        if (allMetrics.isEmpty()) {
            return;
        }

        allMetrics.forEach(it -> {
            MetricES metricES = convertESDTO(it);
            metricESList.add(metricES);
        });
        metricESDao.saveAll(metricESList);
    }

    private MetricES convertESDTO(Metric metric) {
        MetricES metricES = new MetricES();
        metricES.setId(metric.getId());
        metricES.setUid(metric.getUid());
        metricES.setDescription(metric.getDescription());
        metricES.setDisplayName(metric.getDisplayName());
        metricES.setOwner(metric.getOwner());
        metricES.setSettings(metric.getSettings());
        metricES.setStatus(metric.getStatus());
        metricES.setTags(metric.getTags());
        metricES.setDeleted(metric.getDeleted());
        return metricES;
    }
}
