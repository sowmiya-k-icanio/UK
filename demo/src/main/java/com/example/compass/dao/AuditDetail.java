/*
 * Copyright (c) Icanio
 */

package com.example.compass.dao;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import java.io.Serializable;
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {"created_at","created_by"},allowGetters = true)
@Generated
public class AuditDetail implements Serializable {

    @CreatedDate
    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Data createdAt;

    @LastModifiedDate
    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Data updatedAt;

    @CreatedBy
    @JsonProperty("created_by")
    private String createdBy;

    @LastModifiedBy
    @JsonProperty("updated_by")
    private String updatedBy;



}
