package com.zkhc.exception_log.system;

import com.zkhc.exception_log.base.BaseJunit;
import com.zkhc.exception_log.modular.system.mapper.UserMapper;
import com.zkhc.exception_log.modular.system.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * 用户测试
 *
 * @author fengshuonan
 * @date 2017-04-27 17:05
 */
public class UserTest extends BaseJunit {

    @Autowired
    private UserService userService;

    @Test
    public void userTest() throws Exception {
        userService.test();
    }

}
