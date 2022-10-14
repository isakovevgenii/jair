//package com.example.security.clientSide;
//
//import java.util.Collections;
//
//import com.example.entity.clientSide.dto.CredentialsDto;
//import com.example.entity.clientSide.dto.UserDto;
//import com.example.service.clientSide.AuthenticationService;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserAuthenticationProvider implements AuthenticationProvider {
//
//    private final AuthenticationService authenticationService;
//
//    public UserAuthenticationProvider(AuthenticationService authenticationService) {
//        this.authenticationService = authenticationService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        UserDto userDto = null;
//        if (authentication instanceof UsernamePasswordAuthenticationToken) {
//            // authentication by username and password
//            userDto = authenticationService.authenticate(
//                    new CredentialsDto((String) authentication.getPrincipal(), (char[]) authentication.getCredentials()));
//        } else if (authentication instanceof PreAuthenticatedAuthenticationToken) {
//            // authentication by cookie
//            userDto = authenticationService.findByToken((String) authentication.getPrincipal());
//        }
//
//        if (userDto == null) {
//            return null;
//        }
//
//        return new UsernamePasswordAuthenticationToken(userDto, null, Collections.emptyList());
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//}
