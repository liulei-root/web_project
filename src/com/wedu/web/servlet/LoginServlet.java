package com.wedu.web.servlet;

import com.wedu.dao.UserDao;
import com.wedu.domain.User;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //接收用户名、密码
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        //封装到User对象
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        //调用UserDao方法查找用户
        UserDao userDao = new UserDao();
        User user1 = userDao.loginUser(user);
        if (user1==null){
            System.out.println("后台数据：登录失败！");
            request.getRequestDispatcher("/failServlet").forward(request,response);
        }else {
            System.out.println("后台数据：登录成功！");
            request.setAttribute("user",user);
            request.getRequestDispatcher("/successServlet").forward(request,response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
