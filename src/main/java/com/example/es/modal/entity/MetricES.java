package com.example.es.modal.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "metric")
@Data
public class MetricES implements Serializable {
    @Id
    private Long id;

    private String uid;

    private String description;

    private String displayName;

    private String owner;

    private Object settings;

    private String status;

    private Object tags;

    private Long columnId;

    private Long datasetId;

    private Long primaryTimeDim;

    private Boolean deleted;
}
