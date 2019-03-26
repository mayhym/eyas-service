package com.eyas.service.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Created by yixuan on 2019/1/23.
 */
@Data
public class BaseDTO implements Serializable {
    private String id;
    private Date createTime;
    private Date updateTime;
    private String creator;
    private String operator;
    private Integer rowStatus;
    private String remark;

    /*业务字段*/
    private List<String> ids;
}
