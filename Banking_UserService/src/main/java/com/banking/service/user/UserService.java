package com.banking.service.user;

import com.banking.common.vo.R;
import com.banking.domain.user.User;

public interface UserService {
    //用户登录
    R userLogin(String username,String password);
    //用户注册
    R register(User user);
}
