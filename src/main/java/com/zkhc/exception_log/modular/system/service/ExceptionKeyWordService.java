package com.zkhc.exception_log.modular.system.service;

import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zkhc.exception_log.core.common.constant.DatasourceEnum;
import com.zkhc.exception_log.modular.system.entity.ExceptionKeyWord;
import com.zkhc.exception_log.modular.system.mapper.ExceptionKeyWordMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 异常信息关键字表 服务实现类
 * </p>
 *
 * @author zkhc
 * @since 2018-11-15
 */
@Service
public class ExceptionKeyWordService extends ServiceImpl<ExceptionKeyWordMapper, ExceptionKeyWord> {
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public void newKeyWord(ExceptionKeyWord exceptionKeyWord) {
        this.baseMapper.newKeyWord(exceptionKeyWord);
    }

    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public ExceptionKeyWord keyWordIsExists(String keyWord) {
        return this.baseMapper.keyWordIsExists(keyWord);
    }

    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public List<String> selectKeyWords(List<Integer> ids) {
        return this.baseMapper.selectKeyWords(ids);
    }

    public int selectKeyWordId(String keyWord) {
        return this.baseMapper.selectKeyWordId(keyWord);
    }
}
