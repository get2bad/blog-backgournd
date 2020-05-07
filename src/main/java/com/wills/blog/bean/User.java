package com.wills.blog.bean;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@ApiModel(description = "用户实体类",value = "用户")
@Table(name = "user")
@Data
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    @ApiModelProperty(name = "userId",required = false)
    private int userId;
    @Column(name = "user_name")
    @ApiModelProperty(name = "userName",required = true)
    private String userName;
    @Column(name = "password")
    @ApiModelProperty(name = "password",required = true)
    private String password;
    @Column(name = "sign_in_ip")
    @ApiModelProperty(name = "signInIp",required = false)
    private String signInIp;
    @Column(name = "last_sign_time")
    @ApiModelProperty(name = "lastSignTime",required = false)
    private Date lastSignTime;
    @Column(name = "active_id")
    @ApiModelProperty(name = "activeId",required = false)
    private String activeId;
    @Column(name = "status")
    @ApiModelProperty(name = "status",required = false)
    private int status;
}
