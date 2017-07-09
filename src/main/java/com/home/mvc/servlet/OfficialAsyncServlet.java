package com.home.mvc.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liyang on 7/7/2017.
 * liyang27@le.com
 * email to me maybe
 * the result of this request is "main thread return work thread return!!"
 * means that main thread print will not close the connection,
 * work thread will use this connection for further response.
 * when main thread sendRedirect, the connection is closed and worker thread will throws a execption.
 */
@Slf4j
@WebServlet(asyncSupported = true, value = "async_o")
public class OfficialAsyncServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new Thread(new Worker(request.startAsync())).start();
        PrintWriter out = response.getWriter();
        out.write("main thread return ");
//        response.sendRedirect("http://www.sina.com");
    }

    class Worker implements Runnable {

        private AsyncContext asyncContext;

        public Worker(AsyncContext asyncContext) {
            this.asyncContext = asyncContext;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                ((HttpServletResponse) asyncContext.getResponse()).sendRedirect("http://www.sina.com");
//                PrintWriter out = asyncContext.getResponse().getWriter();
//                out.write("work thread return!!");
                asyncContext.complete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
