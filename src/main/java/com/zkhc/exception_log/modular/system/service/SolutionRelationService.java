package com.zkhc.exception_log.modular.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zkhc.exception_log.modular.system.entity.SolutionRelation;
import com.zkhc.exception_log.modular.system.mapper.SolutionRelationMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 关键字与解决办法对照表 服务实现类
 * </p>
 *
 * @author zkhc
 * @since 2018-11-16
 */
@Service
public class SolutionRelationService extends ServiceImpl<SolutionRelationMapper, SolutionRelation> {

    public int deleteRelationBySolutionId(Integer solutionId) {
        return this.baseMapper.deleteRelationBySolutionId(solutionId);
    }
}
