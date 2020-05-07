package com.wills.blog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "file")
@ApiModel(value = "文件类")
@Data
public class File  implements Serializable {

    @Id
    @Column(name = "file_id")
    @ApiModelProperty(name = "file_id")
    private int fileId;

    @Column(name = "file_path")
    @ApiModelProperty(name = "file_path")
    private String filePath;

    @Column(name = "file_type")
    @ApiModelProperty(name = "file_type")
    private String fileType;

    @Column(name = "file_description")
    @ApiModelProperty(name = "file_description")
    private String fileDescription;

    private String fileKey;

    public File(int fileId, String filePath, String fileType, String fileDescription, String fileKey) {
        this.fileId = fileId;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fileDescription = fileDescription;
        this.fileKey = fileKey;
    }
}
