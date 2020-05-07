package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "menu")
@ApiModel(value = "前台菜单表")
@Data
public class Menu implements Serializable {

    @Id
    @Column(name = "menu_id")
    @ApiModelProperty(name = "menu_id")
    public int menuId;

    @Column(name = "menu_name")
    @ApiModelProperty(name = "menu_name")
    public String menuName;

    @Column(name = "menu_url")
    @ApiModelProperty(name = "menu_url")
    public String menuUrl;

    @Column(name = "menu_parent")
    @ApiModelProperty(name = "menu_parent")
    public int menuParent;

    public Menu(int menuIid, String menuName, String menuUrl, int menuParent) {
        this.menuId = menuIid;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuParent = menuParent;
    }
}
