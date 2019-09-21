package io.cjf.jinterviewback.controller;

import io.cjf.jinterviewback.dao.UserMapper;
import io.cjf.jinterviewback.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/login")
    public void login(@RequestParam String mobile,
                      @RequestParam String password) throws Exception {
        User user = userMapper.selectByMobile(mobile);
        if (user == null){
            throw new Exception("mobile doesn't exist");
        }
        String md5Pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        String encPwdDB = user.getEncryptedPassword();
        if (!md5Pwd.equalsIgnoreCase(encPwdDB)){
            throw new Exception("password is incorrect");
        }
        String sessionId = httpSession.getId();
        httpSession.setAttribute(sessionId, user);
    }
}
