package com.zkhc.exception_log.modular.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

/**
 * <p>
 * 后台异常信息操作记录
 * </p>
 */
@TableName("sys_exception_operate")
public class ExceptionOperate extends Model<ExceptionOperate> {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;
    /**
     * 异常id
     */
    @TableField("exception_id")
    private String exceptionId;
    /**
     * 操作
     */
    private String msg;
    /**
     * 操作人
     */
    @TableField("user_name")
    private String userName;
    /**
     * 操作信息
     */
    private String content;
    /**
     * 操作时间
     */
    @TableField("create_time")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(String exceptionId) {
        this.exceptionId = exceptionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ExceptionOperate() {
    }

    public ExceptionOperate(String id, String exceptionId, String msg, String userName, String content, Date createTime) {
        this.id = id;
        this.exceptionId = exceptionId;
        this.msg = msg;
        this.userName = userName;
        this.content = content;
        this.createTime = createTime;
    }

    public ExceptionOperate(String id, String exceptionId, String msg, String content, Date createTime) {
        this.id = id;
        this.exceptionId = exceptionId;
        this.msg = msg;
        this.content = content;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ExceptionOperate{" +
                "id='" + id + '\'' +
                ", exceptionId='" + exceptionId + '\'' +
                ", msg='" + msg + '\'' +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
