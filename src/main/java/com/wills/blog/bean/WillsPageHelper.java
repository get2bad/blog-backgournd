package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "Wills的分页类")
@Data
public class WillsPageHelper implements Serializable {

    private int start;
    private int perCount;

    public WillsPageHelper(int start, int perCount) {
        this.start = start;
        this.perCount = perCount;
    }
}

