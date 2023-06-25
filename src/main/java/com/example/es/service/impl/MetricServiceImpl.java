package com.example.es.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.es.constant.SortOrder;
import com.example.es.modal.dto.MetricDTO;
import com.example.es.modal.entity.Metric;
import com.example.es.modal.entity.MetricES;
import com.example.es.modal.vo.MetricVO;
import com.example.es.service.MetricService;
import com.example.es.mapper.MetricMapper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
* @author C5311821
* @description 针对表【ik_metric】的数据库操作Service实现
* @createDate 2023-06-24 14:04:10
*/
@Service
public class MetricServiceImpl extends ServiceImpl<MetricMapper, Metric> implements MetricService {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

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

    @Override
    public List<MetricVO> searchMetrics(String keyword) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //过滤
        boolQueryBuilder.filter(QueryBuilders.termQuery("deleted", "false"));

        //搜索条件
        boolQueryBuilder.should(QueryBuilders.matchQuery("displayName", keyword));
        boolQueryBuilder.should(QueryBuilders.matchQuery("description", keyword));
        boolQueryBuilder.minimumShouldMatch(1);

        //排序
        SortBuilder<?> sortBuilder = SortBuilders.scoreSort();
        sortBuilder = SortBuilders.fieldSort("displayName");
        sortBuilder.order(org.elasticsearch.search.sort.SortOrder.ASC);

        //分页
        PageRequest pageRequest = PageRequest.of(0, 10);

        //查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder)
//                .withPageable(pageRequest)
//                .withSorts(sortBuilder)
                .build();
        SearchHits<MetricES> searchHits = elasticsearchRestTemplate.search(searchQuery, MetricES.class);
        List<Long> ids = searchHits.stream().map(hit -> hit.getContent().getId()).toList();
        if(ids.isEmpty()){
            return null;
        }
        return this.getBaseMapper().selectBatchIds(ids).stream().map(this::convertToMetricVO).toList();
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
