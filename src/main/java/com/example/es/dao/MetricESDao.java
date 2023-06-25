package com.example.es.dao;

import com.example.es.modal.dto.MetricESDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MetricESDao extends ElasticsearchRepository<MetricESDTO, Long> {
    List<MetricESDTO> findAllByDescription(String description);
}
