package com.econo.econobeepserver.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Iterator;

@Component
public class BearerAuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer";
    public static final String ACCESS_TOKEN = "accessToken";


    private String extract(HttpServletRequest request, String type) {
        Enumeration<String> headers = request.getHeaders(AUTHORIZATION);
        for (Iterator<String> it = headers.asIterator(); it.hasNext(); ) {
            String header = it.next();
            if (header.toLowerCase().startsWith(type.toLowerCase())) {
                return header.substring(type.length()).trim();
            }
        }

        return Strings.EMPTY;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = extract(request, BEARER);
        if (StringUtils.isNotEmpty(token)) {
            request.setAttribute(ACCESS_TOKEN, token);
        }

        return true;
    }
}
