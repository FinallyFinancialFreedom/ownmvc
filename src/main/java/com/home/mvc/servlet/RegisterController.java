package com.home.mvc.servlet;

import com.home.mvc.common.form.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;

/**
 * Created by liyang on 25/1/2017.
 * li.acerphoenix@gmail.com;
 * email to me
 */
@Slf4j
@WebServlet(urlPatterns = {"/reg/"})
public class RegisterController {

    public String add(String tel,int age) {
        return tel+"--"+(++age);
    }

    public int hehe(int age) {
        return age;
    }

    public String modify(User user) {
        System.out.println(user.getAge());
        return user.getName()+"--"+user.getTel()+"--"+(user.getAge()+1);
    }
}
