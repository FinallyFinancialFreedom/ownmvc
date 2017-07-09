package com.home.mvc.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liyang on 7/7/2017.
 * liyang27@le.com
 * email to me maybe
 * implements async with directly start another thread
 * my assume is not work, response in another thread does not make response
 */
@Slf4j
@WebServlet("async_m")
public class AsyncServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("come in");
        new Thread(() -> {
            try {
                log.info("come in work");
                Thread.sleep(2000);
                log.info("work over in 2 seconds");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        log.info("over");
        response.sendRedirect("http://www.baidu.com");
        log.info("did this line reach?");
    }


}
