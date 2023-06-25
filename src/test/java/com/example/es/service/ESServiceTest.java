package com.example.es.service;

import com.example.es.dao.MetricESDao;
import com.example.es.modal.entity.MetricES;
import com.example.es.modal.entity.Metric;
import com.example.es.modal.vo.MetricVO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@SpringBootTest
public class ESServiceTest {

    @Resource
    private MetricESDao metricESDao;

    @Resource
    private MetricService metricService;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    void testAddMetric() {
        Metric metric = metricService.getById(2L);
        MetricES metricDTO = new MetricES();
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
        Optional<MetricES> id = metricESDao.findById(100L);
        id.ifPresent(System.out::println);
    }

    @Test
    void testSelectAllMetric() {
        Iterable<MetricES> all = metricESDao.findAll();
        //转换all为list
        List<MetricES> list = StreamSupport.stream(all.spliterator(), false).toList();
        System.out.println(list);
    }

    @Test
    void searchMetric() {
        List<MetricES> metricESList = metricESDao.findAllByDescription("Ratio");
        System.out.println(metricESList);
    }

    @Test
    void searchMetric1() {
        List<MetricVO> ratio = metricService.searchMetrics("Ratio");
        System.out.println(ratio);
    }
}
