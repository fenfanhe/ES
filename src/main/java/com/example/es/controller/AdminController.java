package com.example.es.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletRequest response) {
        request.getSession().setAttribute("user", "admin");
        return "login";
    }

    @GetMapping("/checkLogin")
    public String checkLogin(HttpServletRequest request, HttpServletResponse response) {
        String user = (String) request.getSession().getAttribute("user");
        if(user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "not login";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return "login success";
    }
}
