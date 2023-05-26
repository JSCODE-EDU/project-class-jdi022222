package com.comibird.anonymousforum.authentication.handler;

import com.comibird.anonymousforum.authentication.exception.UserNotFoundException;
import com.comibird.anonymousforum.common.dto.response.ApiResponse;
import com.comibird.anonymousforum.common.dto.response.ApiResponseType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        throw new UserNotFoundException();
    }

}
