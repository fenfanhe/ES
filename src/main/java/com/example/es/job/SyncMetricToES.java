package com.example.es.job;

import com.example.es.dao.MetricESDao;
import com.example.es.modal.dto.MetricESDTO;
import com.example.es.modal.entity.Metric;
import com.example.es.modal.vo.MetricVO;
import com.example.es.service.MetricService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//开启component启动时执行
@Component
@Slf4j
public class SyncMetricToES implements CommandLineRunner {
    @Resource
    private MetricService metricService;

    @Resource
    private MetricESDao metricESDao;

    @Override
    public void run(String... args) throws Exception {
        List<Metric> allMetrics = metricService.getBaseMapper().selectList(null);
        List<MetricESDTO> metricESDTOList = new ArrayList<>();

        if (allMetrics.isEmpty()) {
            return;
        }

        allMetrics.forEach(it -> {
            MetricESDTO metricESDTO = convertESDTO(it);
            metricESDTOList.add(metricESDTO);
        });
        metricESDao.saveAll(metricESDTOList);
    }

    private MetricESDTO convertESDTO(Metric metric) {
        MetricESDTO metricESDTO = new MetricESDTO();
        metricESDTO.setId(metric.getId());
        metricESDTO.setUid(metric.getUid());
        metricESDTO.setDescription(metric.getDescription());
        metricESDTO.setDisplayName(metric.getDisplayName());
        metricESDTO.setOwner(metric.getOwner());
        metricESDTO.setSettings(metric.getSettings());
        metricESDTO.setStatus(metric.getStatus());
        metricESDTO.setTags(metric.getTags());
        return metricESDTO;
    }
}
