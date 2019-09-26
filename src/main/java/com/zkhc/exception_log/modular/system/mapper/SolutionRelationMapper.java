package com.zkhc.exception_log.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zkhc.exception_log.modular.system.entity.SolutionRelation;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 关键字与解决办法对照表 Mapper 接口
 * </p>
 *
 * @author zkhc
 * @since 2018-11-16
 */
public interface SolutionRelationMapper extends BaseMapper<SolutionRelation> {
    /**
     * 根据解决办法删除对应关系
     * @param solutionId
     * @return
     */
    int deleteRelationBySolutionId(@Param("solutionId") Integer solutionId);
}
