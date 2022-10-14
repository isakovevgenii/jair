package com.example.security.serverSide;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Especially the very first, unauthorized request of a user triggers an AccessDeniedException (somewhere out of the FilterSecurityInterceptor).
 * This one is catched and handled by the ExceptionTranslationFilter.
 * If the user is not yet authenticated, the filter forwards him/her to the configured AuthenticationEntryPoint.
 *
 * In the default configuration, the original request is temporarily stored in a RequestCache to be replayed
 * after a successful login (see previous section).
 * As noted before, the default HttpSessionRequestCache also uses the server session to store the request.
 * We could have introduced a CookieRequestCache to stored the request in another cookie (like the SecurityContext).
 * In our sample project we follow another approach.
 *
 * We deactivate the RequestCache and instead extend the default LoginUrlAuthenticationEntryPoint,
 * which forwards the user to the login form.
 */
@Component
public class LoginWithTargetUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public LoginWithTargetUrlAuthenticationEntryPoint() {
        super(WebSecurityConfig.LOGIN_FORM_URL);
    }

    /**
     * The overridden determineUrlToUseForThisRequest(...) method appends the URL from the original request
     * as a query parameter to the redirect URL.
     * This way, the URL can be mapped to a hidden input field and
     * will be included again in the login request where the AuthenticationSuccessHandler can read it.
     * @param request
     * @param response
     * @param exception
     * @return
     */
    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        return UriComponentsBuilder.fromUriString(super.determineUrlToUseForThisRequest(request, response, exception))
                .queryParam(WebSecurityConfig.TARGET_AFTER_SUCCESSFUL_LOGIN_PARAM, request.getRequestURI())
                .toUriString();
    }
}