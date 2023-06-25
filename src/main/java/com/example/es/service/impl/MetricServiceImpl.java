package com.example.es.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.es.constant.SortOrder;
import com.example.es.modal.dto.MetricDTO;
import com.example.es.modal.entity.Metric;
import com.example.es.modal.vo.MetricVO;
import com.example.es.service.MetricService;
import com.example.es.mapper.MetricMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
* @author C5311821
* @description 针对表【ik_metric】的数据库操作Service实现
* @createDate 2023-06-24 14:04:10
*/
@Service
public class MetricServiceImpl extends ServiceImpl<MetricMapper, Metric> implements MetricService {

    @Override
    public Page<MetricVO> getMetrics(int pageNum, int pageSize, String sort, SortOrder order, MetricDTO metric) {
        //create pagination
        Page<Metric> page = new Page<>(pageNum, pageSize);

        //create query wrapper
        QueryWrapper<Metric> queryWrapper = this.buildQueryWrapper(metric);

        // sort
        if(StringUtils.hasText(sort)) {
            if(order == null) {
                order = SortOrder.ASC;
            }
            queryWrapper.orderBy(true, order == SortOrder.ASC, sort);
        }

        Page<Metric> metrics = this.page(page, queryWrapper);
        Page<MetricVO> metricVOPage = new Page<>();
        metricVOPage.setTotal(metrics.getTotal());
        metricVOPage.setSize(metrics.getSize());
        metricVOPage.setPages(metrics.getPages());
        metricVOPage.setCurrent(metrics.getCurrent());
        metricVOPage.setRecords(metrics.getRecords().stream().map(this::convertToMetricVO).toList());
        return metricVOPage;
    }

    @Override
    public List<MetricVO> getAllMetrics(MetricDTO metricDTO) {
        //create query wrapper
        QueryWrapper<Metric> queryWrapper = this.buildQueryWrapper(metricDTO);
        return this.getBaseMapper().selectList(queryWrapper).stream().map(this::convertToMetricVO).toList();
    }

    @Override
    public void updateMetric(Long id, MetricDTO metricDTO) {
        Metric metric = this.getById(id);
        metric.setDisplayName(metricDTO.getDisplayName());
        metric.setDescription(metricDTO.getDescription());
        metric.setOwner(metricDTO.getOwner());
        metric.setSettings(metricDTO.getSettings());
        metric.setStatus(metricDTO.getStatus());
        this.updateById(metric);
    }

    private QueryWrapper<Metric> buildQueryWrapper(MetricDTO metric) {
        //create query wrapper
        QueryWrapper<Metric> queryWrapper = new QueryWrapper<>();

        if(StringUtils.hasText(metric.getDescription())) {
            queryWrapper.like("description", metric.getDescription());
        }

        if(StringUtils.hasText(metric.getDisplayName())) {
            queryWrapper.like("display_name", metric.getDisplayName());
        }

        if(metric.getOwner() != null) {
            queryWrapper.eq("owner", metric.getOwner());
        }

        return queryWrapper;
    }

    private MetricVO convertToMetricVO(Metric metric) {
        MetricVO metricVO = new MetricVO();
        metricVO.setId(metric.getId());
        metricVO.setUid(metric.getUid());
        metricVO.setDisplayName(metric.getDisplayName());
        metricVO.setDescription(metric.getDescription());
        metricVO.setOwner(metric.getOwner());
        metricVO.setSettings(metric.getSettings());
        metricVO.setStatus(metric.getStatus());
        metricVO.setTags(metric.getTags());
        metricVO.setColumnId(metric.getColumnId());
        metricVO.setDatasetId(metric.getDatasetId());
        metricVO.setPrimaryTimeDim(metric.getPrimaryTimeDim());
        return metricVO;
    }
}
