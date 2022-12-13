package com.sachinlakshitha.springboot3jspauthinterceptor.interceptor;

import com.sachinlakshitha.springboot3jspauthinterceptor.dto.UserDto;
import com.sachinlakshitha.springboot3jspauthinterceptor.util.AppConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class SignInInterceptor implements HandlerInterceptor {
    @Autowired
    private HttpSession httpSession;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (AppConstant.LOGIN_URL.equals(httpServletRequest.getRequestURI()) || AppConstant.ROOT_URL.equals(httpServletRequest.getRequestURI())) {
            UserDto user = (UserDto) httpSession.getAttribute("user");
            if (null != user) {
                if (httpServletResponse.isCommitted()){
                    httpServletResponse.sendRedirect("/starter");
                }
            }
        }

        if (!AppConstant.LOGIN_URL.equals(httpServletRequest.getRequestURI()) && !AppConstant.LOGOUT_URL.equals(httpServletRequest.getRequestURI())) {
            UserDto user = (UserDto) httpSession.getAttribute("user");
            if (null == user) {
                httpServletRequest.logout();
                httpServletResponse.sendRedirect("/logout");
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
