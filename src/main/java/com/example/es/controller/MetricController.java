package com.example.es.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.es.constant.SortOrder;
import com.example.es.modal.dto.MetricDTO;
import com.example.es.modal.vo.MetricVO;
import com.example.es.service.MetricService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/metric")
@Slf4j
public class MetricController {

    @Resource
    private MetricService metricService;

    @GetMapping("")
    public Page<MetricVO> getMetrics(@RequestParam("pageNum") @NonNull Integer pageNum,
                        @RequestParam("pageSize") @NonNull Integer pageSize,
                        @RequestParam(name = "sort", required = false) String sort,
                        @RequestParam(name = "order", required = false) String order,
                        MetricDTO metricDTO) {
        //转换sort order
        SortOrder sortOrder = null;
        if(order != null) {
            sortOrder = SortOrder.valueOf(order.toUpperCase());
        }
        return metricService.getMetrics(pageNum, pageSize, sort, sortOrder, metricDTO);
    }

    @GetMapping("/all")
    public List<MetricVO> getAllMetrics(MetricDTO metricDTO) {
        return metricService.getAllMetrics(metricDTO);
    }

    @PutMapping("/{id}")
    public void updateMetric(@PathVariable("id") Long id, @RequestBody @NonNull MetricDTO metricDTO) {
        metricService.updateMetric(id, metricDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMetric(@PathVariable("id") Long id) {
        metricService.removeById(id);
    }

    @GetMapping("/search")
    public List<MetricVO> searchMetrics(@RequestParam("keyword") String keyword) {
        return metricService.searchMetrics(keyword);
    }

}
