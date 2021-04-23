package com.zyx.entity;


import cn.fsp.core.format.JsonFormatType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体抽象类
 */
@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AbstractPojo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "表自增长主键")
    protected Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = JsonFormatType.DATE_TIME)
    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    protected Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = JsonFormatType.DATE_TIME)
    @LastModifiedDate
    @ApiModelProperty(value = "更新时间")
    protected Date updatedTime;
}
