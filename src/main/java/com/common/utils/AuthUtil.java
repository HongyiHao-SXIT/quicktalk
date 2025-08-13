package com.common.utils;

import com.common.exception.ServiceException;
import com.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class AuthUtil {
    private static final String TOKEN_KEY = "token";

    public static Integer getUserId() {
        return getUser().getId();
    }

    public static String getUserAccount() {
        return getUser().getAccount();
    }

    public static void setToken(User auth) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(TOKEN_KEY, auth);
    }

    public static void clear() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(TOKEN_KEY, null);
    }

    public static User getUser() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(TOKEN_KEY);

        if(attribute == null) {
            throw new ServiceException("用户未登录");
        }
        return (User) attribute;
    }

    public static String codePassword(String password) {
        return password;
    }
}
