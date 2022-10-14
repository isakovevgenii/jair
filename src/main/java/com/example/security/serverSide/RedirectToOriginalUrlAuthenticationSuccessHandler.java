package com.example.security.serverSide;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * After a successful login the configured AuthenticationSuccessHandler is called.
 * Usually, this handler decides about where to forward the user to after the successful login.
 * In the default configuration a SavedRequestAwareAuthenticationSuccessHandler is used.
 * It loads and replays the original request (which was cached before by the ExceptionTranslationFilter, see next section)
 * to show the page to the user which he/she originally requested.
 * As this RequestCache is also stored in the server-side session,
 * we have to find another strategy for this feature as well.
 *
 * The RedirectToOriginalUrlAuthenticationSuccessHandler extends the SimpleUrlAuthenticationSuccessHandler
 * and sets the targetUrlParameter in its constructor.
 * The parameter is defined and used by the extended AbstractAuthenticationTargetUrlRequestHandler to find
 * the target URL in the request parameters. Using this feature,
 * we can simply put the originally requested URL into a hidden input field of the login form.
 * The determineTargetUrl(...) method of the AbstractAuthenticationTargetUrlRequestHandler is overridden to prevent
 * tampering of the target URL parameter (see OWASP Unvalidated Redirects and Forwards Cheat Sheet).
 * We only expect relative URLs within our own application.
 *
 * The RedirectToOriginalUrlAuthenticationSuccessHandler also overrides the onAuthenticationSuccess(...) method.
 * In this method we can get additional parameters (in our example a favorite colour) from the login form and
 * add it to the UserInfo object.
 */

@Component
public class RedirectToOriginalUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final Logger LOG = LoggerFactory.getLogger(RedirectToOriginalUrlAuthenticationSuccessHandler.class);
    private static final String DEFAULT_TARGET_URL = "/";


    public RedirectToOriginalUrlAuthenticationSuccessHandler() {
        super(DEFAULT_TARGET_URL);
        this.setTargetUrlParameter(WebSecurityConfig.TARGET_AFTER_SUCCESSFUL_LOGIN_PARAM);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        userInfo.setColour(request.getParameter(WebSecurityConfig.COLOUR_PARAM));
        super.onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        var targetUrl = super.determineTargetUrl(request, response, authentication);
        if (UrlUtils.isAbsoluteUrl(targetUrl)) {
            LOG.warn("Absolute target URL {} identified and suppressed", targetUrl);
            return DEFAULT_TARGET_URL;
        }
        return targetUrl;
    }
}