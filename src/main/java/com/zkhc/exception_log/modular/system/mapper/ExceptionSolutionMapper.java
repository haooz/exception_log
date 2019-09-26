package com.zkhc.exception_log.modular.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zkhc.exception_log.modular.system.entity.ExceptionSolution;

/**
 * <p>
 * 异常解决办法 Mapper 接口
 * </p>
 *
 * @author zkhc
 * @since 2018-11-16
 */
public interface ExceptionSolutionMapper extends BaseMapper<ExceptionSolution> {

    /**
     * 添加解决办法
     * @param exceptionSolution
     */
    void addSolution(ExceptionSolution exceptionSolution);
}
