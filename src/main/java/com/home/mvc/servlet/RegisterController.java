package com.home.mvc.servlet;

import com.home.mvc.common.form.User;
import com.home.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;

/**
 * Created by liyang on 25/1/2017.
 * li.acerphoenix@gmail.com;
 * email to me
 */
@Slf4j
@WebServlet(urlPatterns = {"/reg/"})
public class RegisterController {

    @Autowired
    private UserService userService;

    public String add(String tel,int age) {
        return tel+"--"+(++age);
    }

    public String reg(String name) {
        userService.regUser(name);
        return "success";
    }

    public int hehe(int age) {
        return age;
    }

    public String modify(User user) {
        return user.getName()+"--"+user.getTel()+"--"+(user.getAge()+1);
    }
}
