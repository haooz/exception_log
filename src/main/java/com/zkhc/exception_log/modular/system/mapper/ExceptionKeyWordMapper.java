package com.zkhc.exception_log.modular.system.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zkhc.exception_log.modular.system.entity.ExceptionKeyWord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 异常信息关键字表 Mapper 接口
 * </p>
 *
 * @author zkhc
 * @since 2018-11-15
 */
public interface ExceptionKeyWordMapper extends BaseMapper<ExceptionKeyWord> {

    /**
     * 新增关键字
     */
    void newKeyWord(ExceptionKeyWord exceptionKeyWord);

    /**
     * 关键字是否存在
     * @param keyWord
     * @return
     */
    ExceptionKeyWord keyWordIsExists(@Param("keyWord") String keyWord);

    /**
     * 获取关键字
     * @param ids
     * @return
     */
    List<String> selectKeyWords(List<Integer> ids);

    /**
     * 获取关键字id
     * @param keyWord
     * @return
     */
    int selectKeyWordId(@Param("keyWord") String keyWord);
}
