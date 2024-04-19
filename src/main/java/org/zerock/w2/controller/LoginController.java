package org.zerock.w2.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
@Log4j2
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Login doGet....");
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //mid , mpw 받기
        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");

        String str = mid + mpw;
        HttpSession session = req.getSession();
        //세션(서버)에 아이디+패스워드해서 문자열로 저장(loginInfo)
        session.setAttribute("loginInfo", str);

        resp.sendRedirect("/todo/list");
    }
}
