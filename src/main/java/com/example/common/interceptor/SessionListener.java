package com.example.common.interceptor;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    private int userCount = 0;

    public void sessionCreate(HttpSessionEvent e) {
        if (userCount < 0) {
            userCount = 0;
        } else {
            ++userCount;
        }
        HttpSession session = e.getSession();
        session.setAttribute("userCount", userCount);

        System.out.println("생성된 SESSION ID"+e.getSession().getId());
        System.out.println("로그인된 사용자수"+userCount);
    }


    public void sessionDestroyed(HttpSessionEvent e) {
        if (userCount < 0) {
            userCount = 0;
        } else {
            --userCount;
        }
        HttpSession session = e.getSession();
        session.setAttribute("userCount", userCount);
        System.out.println("제거된 SESSION ID"+e.getSession().getId());
        System.out.println("로그된 사용자수"+userCount);
    }
}
