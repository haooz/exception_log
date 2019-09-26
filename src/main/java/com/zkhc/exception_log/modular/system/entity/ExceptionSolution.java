package com.zkhc.exception_log.modular.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 异常解决办法
 * </p>
 */
@TableName("sys_exception_solution")
public class ExceptionSolution extends Model<ExceptionSolution> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 解决办法
     */
    private String solution;
    /**
     * 审核 0:通过  1:不通过
     */
    private Integer review;
    /**
     * 预留字段
     */
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ExceptionSolution{" +
        "id=" + id +
        ", solution=" + solution +
        ", review=" + review +
        ", remark=" + remark +
        "}";
    }
}
