package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "note")
@ApiModel(value = "留言",description = "留言类")
@Data
public class Note implements Serializable {

    @Id
    @ApiModelProperty(name = "note_id")
    @Column(name = "note_id")
    private int noteId;

    @ApiModelProperty(name = "user_id")
    @Column(name = "user_id")
    private int userId;

    @ApiModelProperty(name = "note_content")
    @Column(name = "note_content")
    private String noteContent;

    @ApiModelProperty(name = "post_ip")
    @Column(name = "post_ip")
    private String postIp;

    @ApiModelProperty(name = "note_post_time")
    @Column(name = "note_post_time")
    private Date notePostTime;

    public Note(int noteId, int userId, String noteContent, String postIp, Date notePostTime) {
        this.noteId = noteId;
        this.userId = userId;
        this.noteContent = noteContent;
        this.postIp = postIp;
        this.notePostTime = notePostTime;
    }
}
