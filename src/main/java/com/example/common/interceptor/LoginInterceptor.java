package com.example.common.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("deprecation")
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        if(userId == null) {
            response.sendRedirect("/loginForm.html");
            System.out.println("You need Login");
                        return false;
        }else {
            return true;
        }
    }
}
