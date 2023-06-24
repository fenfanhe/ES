package com.example.es.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.es.modal.entity.Metric;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;

import java.util.List;

/**
* @author C5311821
* @description 针对表【ik_metric】的数据库操作Mapper
* @createDate 2023-06-24 14:04:10
* @Entity generator.domain.Metric
*/
public interface MetricMapper extends BaseMapper<Metric> {

    @Select("SELECT * FROM ik_metric LIMIT #{pageSize} OFFSET ${(pageNum -1) * pageSize}")
    List<Metric> selectAll(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
}




