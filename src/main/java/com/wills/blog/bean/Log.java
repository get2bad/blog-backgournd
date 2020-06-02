package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "日志")
@Table(name = "log")
public class Log {
    @Id
    @ApiModelProperty(name = "log_id")
    @Column(name = "log_id")
    private int logId;

    @ApiModelProperty(name = "log_content")
    @Column(name = "log_content")
    private String logContent;

    @ApiModelProperty(name = "log_operationDate")
    @Column(name = "log_operationDate")
    private Date logOperationDate;

    @ApiModelProperty(name = "log_requireType")
    @Column(name = "log_requireType")
    private String logRequireType;

    @ApiModelProperty(name = "log_requireIp")
    @Column(name = "log_requireIp")
    private String logRequireIp;
}
