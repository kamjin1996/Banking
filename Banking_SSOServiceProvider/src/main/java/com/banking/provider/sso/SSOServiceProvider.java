package com.banking.provider.sso;

import com.alibaba.fastjson.JSON;
import com.banking.common.redis.RedisUtil;
import com.banking.common.util.EncrypUtil;
import com.banking.common.vo.R;
import com.banking.domain.user.User;
import com.banking.mapper.user.UserMapper;
import com.banking.service.sso.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service(value = "sSOServiceProvider")
public class SSOServiceProvider implements SSOService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public R SSOLogin(String username, String password) {
        //1、用户登录
        User user = userMapper.selectByUsername(username);
        if(user!=null){
            //用户存在
            if(Objects.equals(user.getPassword(),EncrypUtil.md5Pass(password))){
                //登录成功
                //生成令牌，标识用户登录状态,这里以用户名作盐,id为密文,要求唯一，复杂，密文
                String token = EncrypUtil.md5Pass(username,user.getId().toString());
                //2、将用户信息json字符串以string存入redis,30分钟，即1800秒
                redisUtil.set(token,JSON.toJSONString(user),1800);
                //3、将令牌存入cookie，所以这里选择将令牌返回到controller。
                return new R(1000,"登录成功",token);
                }
                return new R(1002,"用户密码错误");
        }
        return new R(1001,"用户不存在");
    }

    //检查用户是否登录
    @Override
    public R SSOCheckLogin(String token) {
       String json = (String) redisUtil.get(token);
        if(json==null){
            //登录失效了,重新登录
            return R.ERROR();
        } else {
            //用户已登录过
            // 将token时间刷新
            redisUtil.expire(token,1800);
            //将用户信息返回
            return new R(1000,"登录成功",JSON.parseObject(json,User.class));
        }

    }

    @Override
    public R SSOLoginOut(String token) {
        //删除redis中的用户信息
        redisUtil.del(token);
        return R.OK();
    }
}
