package com.example1.interceptor;

import org.hamcrest.Matchers;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class EndPointValidationInterceptor implements HandlerInterceptor {
    private static final String REGEX = "/rates/" + "[A-Z]{3}";
    private final String MESSAGE_BAD_REQUEST = "Bad request! Please type valid request. Example: rates/USD";
    private final int STATUS_BAD_REQUEST = 400;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!Matchers.matchesRegex(REGEX).matches(request.getRequestURI())) {
            response.getWriter().write(MESSAGE_BAD_REQUEST);
            response.setStatus(STATUS_BAD_REQUEST);
            return false;
        }
        return true;
    }
}


