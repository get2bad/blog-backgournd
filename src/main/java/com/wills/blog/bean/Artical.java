package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "artical")
@ApiModel(value = "文章信息")
@Data
public class Artical implements Serializable {

    @Id
    @Column(name = "artical_id")
    @ApiModelProperty(name = "artical_id")
    private int articalId;

    @Column(name = "category_id")
    @ApiModelProperty(name = "category_id")
    private int categoryId;

    @Column(name = "status")
    @ApiModelProperty(name = "status")
    private int status;

    @Column(name = "post_time")
    @ApiModelProperty(name = "post_time")
    private Date postTime;

    @Column(name = "view_count")
    @ApiModelProperty(name = "view_count")
    private int viewCount;

    @Column(name = "user_id")
    @ApiModelProperty(name = "user_id")
    private int userId;

    @Column(name = "artical_title")
    @ApiModelProperty(name = "artical_title")
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String articalTitle;

    @Column(name = "artical_content")
    @ApiModelProperty(name = "artical_content")
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String articalContent;

    @Column(name = "artical_introduce")
    @ApiModelProperty(name = "artical_introduce")
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String articalIntroduce;

    @Column(name = "pic_introduce_img_url")
    @ApiModelProperty(name = "pic_introduce_img_url")
    private String picIntroduceImgUrl;

    @Column(name = "is_deny_comment")
    @ApiModelProperty(name = "is_deny_comment")
    private int isDenyComment;

    @Column(name = "is_lock")
    @ApiModelProperty(name = "is_lock")
    private int isLock;

    @Column(name = "is_submit_top")
    @ApiModelProperty(name = "is_submit_top")
    private int isSubmitTop;

    @Column(name = "tag")
    @ApiModelProperty(name = "tag")
    private String tag;

    @Transient
    private String categoryName;

    @Transient
    private int commentCount;

    @Transient
    private int like;

    @Transient
    private List<Tag> tags;

    @Transient
    private List<Comment> comments;

}
