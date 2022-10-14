package com.example.security.serverSide;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * The WebSecurityConfig configures Spring Security to use all the components described above.
 *
 * To prevent the creation of the server-side session and the JSESSION cookie we use the SessionCreationPolicy.STATELESS.
 * To really activate this policy, we have to disable CSRF protection as well (see Spring Security issue 5299).
 *
 * We use the CookieSecurityContextRepository and our cookie should be deleted after the user logs out.
 *
 * The RequestCache is deactivated and instead the LoginWithTargetUrlAuthenticationEntryPoint is used to
 * add the originally requested URL to the login form request.
 *
 * The RedirectToOriginalUrlAuthenticationSuccessHandler is used to forward the user to the originally requested URL
 * after a successful login.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    static final String LOGIN_FORM_URL = "/login";
    static final String TARGET_AFTER_SUCCESSFUL_LOGIN_PARAM = "target";
    static final String COLOUR_PARAM = "colour";

    private final CookieSecurityContextRepository cookieSecurityContextRepository;
    private final LoginWithTargetUrlAuthenticationEntryPoint loginWithTargetUrlAuthenticationEntryPoint;
    private final RedirectToOriginalUrlAuthenticationSuccessHandler redirectToOriginalUrlAuthenticationSuccessHandler;
    private final InMemoryAuthenticationProvider inMemoryAuthenticationProvider;

    protected WebSecurityConfig(CookieSecurityContextRepository cookieSecurityContextRepository,
                                LoginWithTargetUrlAuthenticationEntryPoint loginWithTargetUrlAuthenticationEntryPoint,
                                RedirectToOriginalUrlAuthenticationSuccessHandler redirectToOriginalUrlAuthenticationSuccessHandler,
                                InMemoryAuthenticationProvider inMemoryAuthenticationProvider) {
        super();
        this.cookieSecurityContextRepository = cookieSecurityContextRepository;
        this.loginWithTargetUrlAuthenticationEntryPoint = loginWithTargetUrlAuthenticationEntryPoint;
        this.redirectToOriginalUrlAuthenticationSuccessHandler = redirectToOriginalUrlAuthenticationSuccessHandler;
        this.inMemoryAuthenticationProvider = inMemoryAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // deactivate session creation
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()

                // store SecurityContext in Cookie / delete Cookie on logout
                .securityContext().securityContextRepository(cookieSecurityContextRepository)
                .and().logout().permitAll().deleteCookies(SignedUserInfoCookie.NAME)

                // deactivate RequestCache and append originally requested URL as query parameter to login form request
                .and().requestCache().disable()
                .exceptionHandling().authenticationEntryPoint(loginWithTargetUrlAuthenticationEntryPoint)

                // configure form-based login
                .and().formLogin()
                .loginPage(LOGIN_FORM_URL)
                // after successful login forward user to originally requested URL
                .successHandler(redirectToOriginalUrlAuthenticationSuccessHandler)

                .and().authorizeRequests()
                .antMatchers(LOGIN_FORM_URL).permitAll()
                .antMatchers("/**").authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(inMemoryAuthenticationProvider);
    }

}