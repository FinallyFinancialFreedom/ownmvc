package com.home.mvc.servlet;

import com.home.mvc.common.form.User;
import com.home.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by liyang on 25/1/2017.
 * li.acerphoenix@gmail.com;
 * email to me
 */
@Slf4j
@Path(value="/reg/")
public class RegisterController {

    @Autowired
    private UserService userService;

    @POST
    @Path(value = "add")
    public String add(String tel,int age) {
        return tel+"--"+(++age);
    }

    @Path(value = "reg")
    public String reg(String name) {
        userService.regUser(name);
        return "success";
    }

    @Path(value = "hehe")
    public int hehe(int age) {
        return age;
    }

    @Path(value = "modify")
    public String modify(User user) {
        return user.getName()+"--"+user.getTel()+"--"+(user.getAge()+1);
    }
}
