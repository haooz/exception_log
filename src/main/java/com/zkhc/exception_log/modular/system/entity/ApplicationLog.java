package com.zkhc.exception_log.modular.system.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 请求信息日志表
 * </p>
 */
@TableName("APPLICATION_LOG")
public class ApplicationLog extends Model<ApplicationLog> {
    private static final long serialVersionUID = 1L;
    @Override
    protected Serializable pkVal() {
        return null;
    }
    /**
     * 编号
     */
    @TableId(value = "event_id")
    private String eventId;
    /**
     * 时间
     */
    @TableField("event_date")
    private Date eventDate;
    /**
     * 级别
     */
    private String level;
    /**
     * 日志名称
     */
    @TableField("log_name")
    private String logName;
    /**
     * 信息
     */
    private String message;
    /**
     * 全部信息
     */
    @TableField("log_throwable")
    private String logThrowable;
    /**
     * 日志文件路径
     */
    @TableField("log_file")
    private String logFile;
    /**
     * 行数
     */
    @TableField("log_line")
    private String logLine;
    /**
     * 系统名
     */
    @TableField("system_name")
    private String systemName;
    /**
     * 环境
     */
    private String env;
    /**
     * 线程内容
     */
    @TableField("thread_con")
    private String threadCon;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogThrowable() {
        return logThrowable;
    }

    public void setLogThrowable(String logThrowable) {
        this.logThrowable = logThrowable;
    }

    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public String getLogLine() {
        return logLine;
    }

    public void setLogLine(String logLine) {
        this.logLine = logLine;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getThreadCon() {
        return threadCon;
    }

    public void setThreadCon(String threadCon) {
        this.threadCon = threadCon;
    }

    @Override
    public String toString() {
        return "ApplicationLog{" +
                "eventId='" + eventId + '\'' +
                ", eventDate=" + eventDate +
                ", level='" + level + '\'' +
                ", logName='" + logName + '\'' +
                ", message='" + message + '\'' +
                ", logThrowable='" + logThrowable + '\'' +
                ", logFile='" + logFile + '\'' +
                ", logLine='" + logLine + '\'' +
                ", systemName='" + systemName + '\'' +
                ", env='" + env + '\'' +
                ", threadCon='" + threadCon + '\'' +
                '}';
    }
}
