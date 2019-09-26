package com.zkhc.exception_log.modular.system.service;

import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zkhc.exception_log.core.common.constant.DatasourceEnum;
import com.zkhc.exception_log.modular.system.entity.ExceptionLog;
import com.zkhc.exception_log.modular.system.mapper.ExceptionLogMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExceptionLogService extends ServiceImpl<ExceptionLogMapper, ExceptionLog> {
    /**
     * 插入新的异常
     * @param exceptionLog
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public void insertNewException(ExceptionLog exceptionLog) {
        this.baseMapper.insertNewException(exceptionLog);
    }

    /**
     * 根据id查找异常信息
     * @param id
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public ExceptionLog selectByEId(String id) {
        return this.baseMapper.selectByEId(id);
    }

    /**
     * 更新浏览次数
     * @param id
     * @param viewNum
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public void updateViewNum(String id, int viewNum) {
        this.baseMapper.updateViewNum(id,viewNum);
    }

    /**
     * 异常列表
     * @param page
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public List<Map<String, Object>> selectExceptionList(Page page, String condition) {
        return this.baseMapper.selectExceptionList(page,condition);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public void deleteByEId(String id) {
        this.baseMapper.deleteByEId(id);
    }

    /**
     * 添加解决办法
     * @param id
     * @param remark
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public void addRemark(String id, String remark) {
        this.baseMapper.addRemark(id,remark);
    }

    /**
     * 类似异常解决办法
     * @param exception
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public List<ExceptionLog> sameList(String exception) {
        return this.baseMapper.sameList(exception);
    }

    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public List<Map<String, Object>> sameReason(String exception) {
        return this.baseMapper.sameReason(exception);
    }

    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public void addKeyWord(String id, String keyWord) {
        this.baseMapper.addKeyWord(id,keyWord);
    }

    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public Map<String, Object> selectExceptionKeyWords(String id) {
        return this.baseMapper.selectExceptionKeyWords(id);
    }

    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public List<Map<String, Object>> sameSolution(List<Long> keyWordId) {
        return this.baseMapper.sameSolution(keyWordId);
    }
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public List<String> selectSpecialWords() {
        return this.baseMapper.selectSpecialWords();
    }

    /**
     * 查找备注
     * @param id
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public String selectRemark(String id) {
        return this.baseMapper.selectRemark(id);
    }
}
