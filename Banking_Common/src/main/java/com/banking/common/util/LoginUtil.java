package com.banking.common.util;


import com.banking.common.redis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;


public class LoginUtil {
    @Autowired
    private JedisUtil jedisUtil;

    public String getLogin(HttpServletRequest request){
        String tk=CookieUtil.getCk(request,"userauth");
        return jedisUtil.getStr(tk);
    }
}
