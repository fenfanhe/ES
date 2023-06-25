package com.example.es.modal.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MetricDTO {

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
}
