package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "comment")
@ApiModel(value = "评论")
@Data
public class Comment  implements Serializable {

    @Id
    @Column(name = "comment_id")
    @ApiModelProperty(name = "comment_id")
    private int commentId;

    @Column(name = "user_id")
    @ApiModelProperty(name = "user_id")
    private int userId;

    @Column(name = "comment_content")
    @ApiModelProperty(name = "comment_content")
    private String commentContent;

    @Column(name = "post_time")
    @ApiModelProperty(name = "post_time")
    private Date postTime;

    @Column(name = "article_id")
    @ApiModelProperty(name = "article_id")
    private int articleId;

    @Column(name = "post_ip")
    @ApiModelProperty(name = "post_ip")
    private String postIp;

    @Column(name = "status")
    @ApiModelProperty(name = "status")
    private int status;

    @Column(name = "user_name")
    @ApiModelProperty(name = "user_name")
    private String userName;

    public Comment(int commentId, int userId, String commentContent, Date postTime, int articleId, String postIp, int status, String userName) {
        this.commentId = commentId;
        this.userId = userId;
        this.commentContent = commentContent;
        this.postTime = postTime;
        this.articleId = articleId;
        this.postIp = postIp;
        this.status = status;
        this.userName = userName;
    }
}
