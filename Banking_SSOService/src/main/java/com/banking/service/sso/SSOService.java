package com.banking.service.sso;

import com.banking.common.vo.R;

public interface SSOService {
    //单点登录
    R SSOLogin(String username,String password);

    //登录检查
    R SSOCheckLogin(String token);

    //单点登出
    R SSOLoginOut(String token);
}
