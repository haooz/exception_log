package com.zkhc.exception_log.modular.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 关键字与解决办法对照表
 * </p>
 */
@TableName("sys_solution_relation")
public class SolutionRelation extends Model<SolutionRelation> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 关键词id
     */
    @TableField("key_word_id")
    private Integer keyWordId;
    /**
     * 解决办法id
     */
    @TableField("solution_id")
    private Integer solutionId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKeyWordId() {
        return keyWordId;
    }

    public void setKeyWordId(Integer keyWordId) {
        this.keyWordId = keyWordId;
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SolutionRelation{" +
        "id=" + id +
        ", keyWordId=" + keyWordId +
        ", solutionId=" + solutionId +
        "}";
    }
}
