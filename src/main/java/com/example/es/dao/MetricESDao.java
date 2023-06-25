package com.example.es.dao;

import com.example.es.modal.entity.MetricES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MetricESDao extends ElasticsearchRepository<MetricES, Long> {
    List<MetricES> findAllByDescription(String description);
}
