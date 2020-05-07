package com.wills.blog.bean;

import lombok.Data;

import java.util.List;
@Data
public class PermissonTotal {

    private Permission father;

    private List<Permission> child;

    public PermissonTotal(Permission father, List<Permission> child) {
        this.father = father;
        this.child = child;
    }
}
