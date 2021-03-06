package com.banking.controller.sso;

import com.banking.common.util.CookieUtil;
import com.banking.common.vo.R;
import com.banking.service.sso.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class SSOController {

    @Autowired
    private SSOService ssoService;

    @RequestMapping("/ssologin.do")
    public R ssoLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
            //登录
            R r = ssoService.SSOLogin(username, password);
            if (r.getCode() == 1000) {

                //登陆成功，需将token存入cookie
                CookieUtil.setCK(response, "userauth", r.getData().toString());
            }
            return r;
    }


    @RequestMapping("/ssologincheck.do")
    public R ssoCheck(HttpServletRequest request,HttpServletResponse response) {
        String token = CookieUtil.getCk(request, "userauth");
        if (token != null) {
            R r = ssoService.SSOCheckLogin(token);
            if(r.getCode()==1000){
                return r;
            } else {
                CookieUtil.delCK(response,"userauth");
            }
            return r;
        }
        return R.ERROR();
    }

    @RequestMapping("/ssologinout.do")
    public R ssoLoginOut(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtil.getCk(request, "userauth");
        CookieUtil.delCK(response, "userauth");
        return ssoService.SSOLoginOut(token);
    }
}
