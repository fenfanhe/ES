package com.example.es.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.es.constant.SortOrder;
import com.example.es.modal.dto.MetricDTO;
import com.example.es.modal.entity.Metric;
import com.example.es.modal.vo.MetricVO;

import java.util.List;
import java.util.Optional;

/**
* @author C5311821
* @description 针对表【ik_metric】的数据库操作Service
* @createDate 2023-06-24 14:04:10
*/
public interface MetricService extends IService<Metric> {
    Page<MetricVO> getMetrics(int pageNum, int pageSize, String sort, SortOrder order, MetricDTO metric);

    List<MetricVO> getAllMetrics(MetricDTO metricDTO);

    void updateMetric(Long id, MetricDTO metricDTO);
}
