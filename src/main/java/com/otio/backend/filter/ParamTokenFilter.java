package com.otio.backend.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.otio.backend.model.User;
import com.otio.backend.service.UserService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ParamTokenFilter implements Filter {
    UserService srv;
   
    public ParamTokenFilter(UserService srv) {
        this.srv = srv;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        String urlPattern = req.getRequestURI();
        List<String> urlContent = Arrays.asList(urlPattern.split("/"));
        String paramName = null;
        if (urlContent.size() > 0) {
            paramName = urlContent.getLast();
        }

        String paramValue = req.getParameter(paramName);
        String token = req.getHeader("token");

        if (Objects.isNull(paramValue)) {
            resp.sendRedirect("/error/invalidrequest");
        }

        else {
            if (Objects.isNull(token)) {
                resp.sendRedirect("/error/tokennotfound");
            }
            else {
                
    
                User userFound = srv.getUserByToken(token);
    
                if (Objects.isNull(userFound)) {
                    resp.sendRedirect("/error/incorrecttoken");
                }
                else {
                    if (LocalDateTime.now().isAfter(userFound.getToken().getTimeout())) {
                        resp.sendRedirect("/error/timeout");
                    }
                }
            }
        }

        chain.doFilter(request, response);
    }
}
