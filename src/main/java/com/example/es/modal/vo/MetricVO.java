package com.example.es.modal.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MetricVO implements Serializable {
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
}
