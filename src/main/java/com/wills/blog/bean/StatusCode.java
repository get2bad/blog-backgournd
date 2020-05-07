package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(value = "状态码")
public class StatusCode implements Serializable {
    public static final int STATUS_OK = 200;
    public static final int STATUS_PERRMISSION_LIMITED = 401;
    public static final int STATUS_REPEAT_OPERATION = 402;
    public static final int STATUS_SOURCE_UNKNOWN = 404;
    public static final int STATUS_SEVER_ERROR = 500;
    public static final int STATUS_REQUIRE_FALL = 600;
}
