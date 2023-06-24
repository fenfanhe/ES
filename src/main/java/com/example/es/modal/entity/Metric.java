package com.example.es.modal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName ik_metric
 */
@TableName(value ="ik_metric")
@Data
public class Metric implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private String createdBy;

    /**
     * 
     */
    private Boolean deleted;

    /**
     * 
     */
    private Integer lockVer;

    /**
     * 
     */
    private Date updatedAt;

    /**
     * 
     */
    private String updatedBy;

    /**
     * 
     */
    private String snapshotStat;

    /**
     * 
     */
    private Integer snapshotVer;

    /**
     * 
     */
    private String uid;

    /**
     * 
     */
    private String aggregationType;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String displayName;

    /**
     * 
     */
    private String owner;

    /**
     * 
     */
    private Object settings;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private Object tags;

    /**
     * 
     */
    private Long columnId;

    /**
     * 
     */
    private Long datasetId;

    /**
     * 
     */
    private Long primaryTimeDim;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Metric other = (Metric) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getLockVer() == null ? other.getLockVer() == null : this.getLockVer().equals(other.getLockVer()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getUpdatedBy() == null ? other.getUpdatedBy() == null : this.getUpdatedBy().equals(other.getUpdatedBy()))
            && (this.getSnapshotStat() == null ? other.getSnapshotStat() == null : this.getSnapshotStat().equals(other.getSnapshotStat()))
            && (this.getSnapshotVer() == null ? other.getSnapshotVer() == null : this.getSnapshotVer().equals(other.getSnapshotVer()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getAggregationType() == null ? other.getAggregationType() == null : this.getAggregationType().equals(other.getAggregationType()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getDisplayName() == null ? other.getDisplayName() == null : this.getDisplayName().equals(other.getDisplayName()))
            && (this.getOwner() == null ? other.getOwner() == null : this.getOwner().equals(other.getOwner()))
            && (this.getSettings() == null ? other.getSettings() == null : this.getSettings().equals(other.getSettings()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getColumnId() == null ? other.getColumnId() == null : this.getColumnId().equals(other.getColumnId()))
            && (this.getDatasetId() == null ? other.getDatasetId() == null : this.getDatasetId().equals(other.getDatasetId()))
            && (this.getPrimaryTimeDim() == null ? other.getPrimaryTimeDim() == null : this.getPrimaryTimeDim().equals(other.getPrimaryTimeDim()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getLockVer() == null) ? 0 : getLockVer().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getUpdatedBy() == null) ? 0 : getUpdatedBy().hashCode());
        result = prime * result + ((getSnapshotStat() == null) ? 0 : getSnapshotStat().hashCode());
        result = prime * result + ((getSnapshotVer() == null) ? 0 : getSnapshotVer().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getAggregationType() == null) ? 0 : getAggregationType().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getDisplayName() == null) ? 0 : getDisplayName().hashCode());
        result = prime * result + ((getOwner() == null) ? 0 : getOwner().hashCode());
        result = prime * result + ((getSettings() == null) ? 0 : getSettings().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getColumnId() == null) ? 0 : getColumnId().hashCode());
        result = prime * result + ((getDatasetId() == null) ? 0 : getDatasetId().hashCode());
        result = prime * result + ((getPrimaryTimeDim() == null) ? 0 : getPrimaryTimeDim().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", deleted=").append(deleted);
        sb.append(", lockVer=").append(lockVer);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", snapshotStat=").append(snapshotStat);
        sb.append(", snapshotVer=").append(snapshotVer);
        sb.append(", uid=").append(uid);
        sb.append(", aggregationType=").append(aggregationType);
        sb.append(", description=").append(description);
        sb.append(", displayName=").append(displayName);
        sb.append(", owner=").append(owner);
        sb.append(", settings=").append(settings);
        sb.append(", status=").append(status);
        sb.append(", tags=").append(tags);
        sb.append(", columnId=").append(columnId);
        sb.append(", datasetId=").append(datasetId);
        sb.append(", primaryTimeDim=").append(primaryTimeDim);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}