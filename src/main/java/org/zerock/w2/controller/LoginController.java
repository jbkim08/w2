package org.zerock.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2.dto.MemberDTO;
import org.zerock.w2.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

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
        //mid , mpw 받기 , auto 받기 체크박스는 "on"이 체크가 됬을때 값
        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");
        String auto = req.getParameter("auto"); //"on"일때 체크

        boolean rememberMe = auto != null && auto.equals("on"); //자동로그인

        //로그인 확인하기
        try {
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid, mpw);

            if(rememberMe){
                String uuid = UUID.randomUUID().toString(); //랜덤 문자열 생성
                MemberService.INSTANCE.updateUuid(mid, uuid); //DB에 uuid 저장
                memberDTO.setUuid(uuid);  //세션에 저장할 멤버객체에 uuid 저장
                //쿠키로 uuid 브라우저에 저장
                Cookie rememberCookie = new Cookie("remember-me", uuid);
                rememberCookie.setMaxAge(60*60*24*7); //1주일 보관
                rememberCookie.setPath("/");
                resp.addCookie(rememberCookie);
            }
            HttpSession session = req.getSession();
            //세션(서버)에 멤버 객체를 저장하고 리스트 페이지로
            session.setAttribute("loginInfo", memberDTO);
            resp.sendRedirect("/todo/list");
        } catch (Exception e) {
            log.error(e.getMessage());
            resp.sendRedirect("/login?result=error"); //DB 에서 유저 없음
        }

    }
}
