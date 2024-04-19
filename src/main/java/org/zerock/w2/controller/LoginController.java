package org.zerock.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2.dto.MemberDTO;
import org.zerock.w2.service.MemberService;

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

        //로그인 확인하기
        try {
            MemberDTO MemberDTO = MemberService.INSTANCE.login(mid, mpw);
            HttpSession session = req.getSession();
            //세션(서버)에 멤버 객체를 저장하고 리스트 페이지로
            session.setAttribute("loginInfo", MemberDTO);
            resp.sendRedirect("/todo/list");
        } catch (Exception e) {
            log.error(e.getMessage());
            resp.sendRedirect("/login?result=error"); //DB 에서 유저 없음
        }
        
    }
}
