package com.zkhc.exception_log.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zkhc.exception_log.modular.system.entity.ExceptionLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExceptionLogMapper extends BaseMapper<ExceptionLog> {
    /**
     * 插入新的异常
     *
     * @param exceptionLog
     */
    void insertNewException(ExceptionLog exceptionLog);

    /**
     * 查找某个异常详细信息
     *
     * @param id
     * @return
     */
    ExceptionLog selectByEId(@Param("id") String id);

    /**
     * 更新浏览次数
     *
     * @param id
     * @param viewNum
     */
    void updateViewNum(@Param("id") String id, @Param("viewNum") int viewNum);

    /**
     * 异常列表
     *
     * @param page
     * @return
     */
    List<Map<String, Object>> selectExceptionList(@Param("page") Page page, @Param("condition") String condition);

    /**
     * 删除某个异常详细信息
     *
     * @param id
     * @return
     */
    void deleteByEId(@Param("id") String id);

    /**
     * 添加解决办法
     *
     * @param id
     * @param remark
     */
    void addRemark(@Param("id") String id, @Param("remark") String remark);

    /**
     * 添加关键字id
     *
     * @param id
     * @param keyWord
     */
    void addKeyWord(@Param("id") String id, @Param("keyWord") String keyWord);

    /**
     * 类似异常解决办法
     *
     * @param exception
     * @return
     */
    List<ExceptionLog> sameList(@Param("exceptionLog") String exception);

    /**
     * 类似异常解决办法
     *
     * @param exception
     * @return
     */
    List<Map<String, Object>> sameReason(@Param("exceptionLog") String exception);

    /**
     * 某异常的keyWordId
     *
     * @param id
     * @return
     */
    Map<String, Object> selectExceptionKeyWords(@Param("id") String id);

    /**
     * 异常解决办法
     *
     * @return
     */
    List<Map<String, Object>> sameSolution(List<Long> keyWordId);

    /**
     * 查找需要特殊显示的字符
     *
     * @return
     */
    List<String> selectSpecialWords();

    /**
     * 查找备注
     * @param id
     * @return
     */
    String selectRemark(@Param("id") String id);
}
