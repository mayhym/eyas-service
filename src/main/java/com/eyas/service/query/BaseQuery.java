package com.eyas.service.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Created by yixuan on 2019/1/23.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseQuery extends BasePage implements Serializable {
    private String id;
    private Date createTime;
    private Date updateTime;
    private String creator;
    private String operator;
    private Integer rowStatus;
    private String remark;
    private String orderBy;
}
