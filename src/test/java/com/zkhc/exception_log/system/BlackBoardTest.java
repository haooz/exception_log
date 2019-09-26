package com.zkhc.exception_log.system;

import com.zkhc.exception_log.base.BaseJunit;
import com.zkhc.exception_log.modular.system.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

/**
 * 首页通知展示测试
 *
 * @author fengshuonan
 * @date 2017-05-21 15:02
 */
public class BlackBoardTest extends BaseJunit {

    @Autowired
    NoticeMapper noticeMapper;

}
