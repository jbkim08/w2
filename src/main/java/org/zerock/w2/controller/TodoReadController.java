package org.zerock.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2.dto.TodoDTO;
import org.zerock.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //할일번호를 파라미터로 받기
        try {
            Long tno = Long.parseLong(req.getParameter("tno"));
            TodoDTO todoDTO = todoService.get(tno);
            req.setAttribute("dto", todoDTO);
            //쿠키찾기
            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
            String todoListStr = viewTodoCookie.getValue(); //찾은 쿠키의 문자열(값)
            boolean exist = false; //(디폴트:false)

            if (todoListStr != null && todoListStr.indexOf(tno+"-")>=0) {
                exist = true; //쿠키안에 번호가 있을 경우는 true
            }
            log.info("exist: " + exist);

            if(!exist) {
                todoListStr += tno+"-"; //쿠키에 번호를 추가한다.
                viewTodoCookie.setValue(todoListStr);
                viewTodoCookie.setMaxAge(60*60*24); // 60초*60(시간)*24(하루)
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);
            }

            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    //쿠키배열중에서 필요한 쿠키를 이름으로 찾고 없을경우에 생성함
    private Cookie findCookie(Cookie[] cookies, String cookieName) {
        //쿠키는 배열이다. 쿠키이름으로 배열안에 쿠키를 찾아서 리턴한다.
        Cookie targetCookie = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    targetCookie = cookie;
                    break;
                }
            }
        }
        // 찾는 쿠키가 없을 경우에
        if (targetCookie == null) {
            targetCookie = new Cookie(cookieName, ""); //쿠키 생성
            targetCookie.setPath("/");         //기본주소에 생성
            targetCookie.setMaxAge(60*60*24);  //하루 생존
        }
        return targetCookie;
    }
}
