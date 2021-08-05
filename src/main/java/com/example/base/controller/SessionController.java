package com.example.base.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Log4j2
public class SessionController implements HttpSessionListener {

    private int userCount;
    private ServletContext application;

    @Override
    public void sessionCreated(HttpSessionEvent SE) {

       HttpSession session = SE.getSession();
       application = session.getServletContext();

             if(application.getAttribute("userCount") == null) {
                application.setAttribute("userCount", 1);
             }else {
                userCount = (Integer)application.getAttribute("userCount");
                application.setAttribute("userCount", ++userCount);
             }
       
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
       
       if(se.getSession().getServletContext().getAttribute("userCount") != null) {
          userCount = (Integer)application.getAttribute("userCount");
          application.setAttribute("userCount", --userCount);
       }
       
    }
 }
