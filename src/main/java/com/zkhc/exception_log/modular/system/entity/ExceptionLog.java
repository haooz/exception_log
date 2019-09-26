package com.zkhc.exception_log.modular.system.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 后台异常信息表
 * </p>
 */
@TableName("sys_exception_log")
public class ExceptionLog extends Model<ExceptionLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 系统名称编号 1.znkf_pc_manage 2.kmc 3.rhbshop
     */
    private String system;
    /**
     * 运行环境
     */
    @TableField("run_environment")
    private String runEnvironment;
    /**
     * ip地址
     */
    private String host;
    /**
     * 端口号
     */
    private Integer port;
    /**
     * 异常发生时间
     */
    private Date createtime;
    /**
     * 异常内容
     */
    private String content;
    /**
     * 备注
     */
    private String remark;
    /**
     * 浏览次数
     */
    @TableField("view_num")
    private Integer viewNum;
    /**
     * 关键词
     */
    @TableField("key_word")
    private String keyWord;

    public ExceptionLog(){

    }

    public ExceptionLog(String id,String system,String runEnvironment,String host,Integer port,Date createtime,String content){
        this.id=id;
        this.system=system;
        this.runEnvironment=runEnvironment;
        this.host=host;
        this.port=port;
        this.createtime=createtime;
        this.content=content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getRunEnvironment() {
        return runEnvironment;
    }

    public void setRunEnvironment(String runEnvironment) {
        this.runEnvironment = runEnvironment;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ExceptionLog{" +
        "id=" + id +
        ", system=" + system +
        ", runEnvironment=" + runEnvironment +
        ", host=" + host +
        ", port=" + port +
        ", createtime=" + createtime +
        ", content=" + content +
        ", remark=" + remark +
        ", viewNum=" + viewNum +
        "}";
    }
}
