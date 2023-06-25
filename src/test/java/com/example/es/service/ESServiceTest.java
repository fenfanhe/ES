package com.example.es.service;

import com.example.es.dao.MetricESDao;
import com.example.es.modal.dto.MetricESDTO;
import com.example.es.modal.entity.Metric;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@SpringBootTest
public class ESServiceTest {

    @Resource
    private MetricESDao metricESDao;

    @Resource
    private MetricService metricService;

    @Test
    void testAddMetric() {
        Metric metric = metricService.getById(2L);
        MetricESDTO metricDTO = new MetricESDTO();
        metricDTO.setId(metric.getId());
        metricDTO.setUid(metric.getUid());
        metricDTO.setDescription(metric.getDescription());
        metricDTO.setDisplayName(metric.getDisplayName());
        metricDTO.setOwner(metric.getOwner());
        metricDTO.setSettings(metric.getSettings());
        metricDTO.setStatus(metric.getStatus());
        metricDTO.setTags(metric.getTags());
        metricESDao.save(metricDTO);
    }

    @Test
    void testSelectMetric() {
        Optional<MetricESDTO> id = metricESDao.findById(100L);
        id.ifPresent(System.out::println);
    }

    @Test
    void testSelectAllMetric() {
        Iterable<MetricESDTO> all = metricESDao.findAll();
        //转换all为list
        List<MetricESDTO> list = StreamSupport.stream(all.spliterator(), false).toList();
        System.out.println(list);
    }

    @Test
    void searchMetric() {
        List<MetricESDTO> metricESDTOList = metricESDao.findAllByDescription("Ratio");
        System.out.println(metricESDTOList);
    }

    @Test
    void searchMetric1() {
    }
}
