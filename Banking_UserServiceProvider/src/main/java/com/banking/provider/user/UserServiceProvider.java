package com.banking.provider.user;

import com.banking.common.util.EncrypUtil;
import com.banking.common.vo.R;
import com.banking.domain.user.User;
import com.banking.mapper.user.UserMapper;
import com.banking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceProvider implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public R userLogin(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user != null) {
            //用户存在
            if (Objects.equals(user.getPassword(), EncrypUtil.md5Pass(password))) {
                //登录成功
                return new R(0, "OK", user);
            }
        }
        return new R(1, "ERROR");
    }

    @Override
    public R register(User user) {
        return userMapper.insert(user) > 0 ? new R(0, "OK") : new R(1, "ERROR");
    }
}
