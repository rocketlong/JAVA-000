package org.geek.dynamic01.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String planName;

    private Integer planStatus;

    private Date createTime;

    private Date updateTime;

}
