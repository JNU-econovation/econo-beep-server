package com.econo.econobeepserver.config;

import com.econo.econobeepserver.domain.User.Role;
import com.econo.econobeepserver.domain.User.User;
import com.econo.econobeepserver.exception.IDPServerErrorException;
import com.econo.econobeepserver.exception.WrongAccessTokenException;
import com.econo.econobeepserver.service.User.UserFakerIdp;
import com.econo.econobeepserver.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class BearerAuthInterceptor implements HandlerInterceptor {

    // @deprecated : Debug token for UserFakerIdp
    @Value("${ADMIN_DEBUG_TOKEN}")
    private String ADMIN_DEBUG_TOKEN;


    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer";
    public static final String IDP_TOKEN = "idpToken";
    public static final String IDP_ID = "idpId";
    public static final String USER_ID = "userId";
    public static final String USER_ROLE = "userRole";


    private final UserService userService;


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

    // TODO : Remove a deprecated debug code
    // TODO : Add a exception catching code for a wrong token
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = extract(request, BEARER);
        if (StringUtils.isEmpty(token)) {
            return true;
        }

        // @deprecated : Debug token for UserFakerIdp
        if (ADMIN_DEBUG_TOKEN.equals(token)) {
            request.setAttribute(IDP_TOKEN, ADMIN_DEBUG_TOKEN);
            request.setAttribute(IDP_ID, UserFakerIdp.FAKE_USER_ID);
            request.setAttribute(USER_ID, UserFakerIdp.FAKE_USER_ID);
            request.setAttribute(USER_ROLE, Role.ADMIN);
            return true;
        }


        try {
            User user = userService.getUserByIdpTokenAfterSyncingDBWithIdp(token);

            request.setAttribute(IDP_TOKEN, token);
            request.setAttribute(IDP_ID, user.getIdpId());
            request.setAttribute(USER_ID, user.getId());
            request.setAttribute(USER_ROLE, user.getRole());
        } catch (IDPServerErrorException | WrongAccessTokenException ignored) { // @deprecated Ignoring WrongAccessTokenException is temporary
        }

        return true;
    }
}
