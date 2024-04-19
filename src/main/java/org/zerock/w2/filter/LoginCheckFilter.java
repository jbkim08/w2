package org.zerock.w2.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/todo/*")
@Log4j2
public class LoginCheckFilter implements Filter {
    //필터를 구현하고 추상메소드인 doFilter 를 만들어야 함
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("LoginCheckFilter....");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        //세션에 로그인정보 있으면 통과
        if(session.getAttribute("loginInfo") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //세션은 없지만 쿠키를 체크해봄
        Cookie cookie = findCookie(req.getCookies(), "remember-me");



        filterChain.doFilter(servletRequest, servletResponse);
    }

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
        return targetCookie;
    }

}
