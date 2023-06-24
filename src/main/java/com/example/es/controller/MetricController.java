package com.example.es.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.es.SortOrder;
import com.example.es.modal.dto.MetricDTO;
import com.example.es.modal.vo.MetricVO;
import com.example.es.service.MetricService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/metric")
@Slf4j
public class MetricController {

    @Resource
    private MetricService metricService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("")
    public Page<MetricVO> getMetrics(@RequestParam(name = "pageNum", required = false) Optional<Integer> pageNum,
                                     @RequestParam(name = "pageSize", required = false) Optional<Integer> pageSize,
                                     @RequestParam(name = "sort", required = false) String sort,
                                     @RequestParam(name = "order", required = false) String order,
                                     MetricDTO metricDTO) {
        if(pageNum.isPresent() && pageSize.isPresent()) {
            //转换sort order
            SortOrder sortOrder = null;
            if(order != null) {
                sortOrder = SortOrder.valueOf(order.toUpperCase());
            }
            return metricService.getMetrics(pageNum.get(), pageSize.get(), sort, sortOrder, metricDTO);
        } else {
            return null;
        }
    }
}
