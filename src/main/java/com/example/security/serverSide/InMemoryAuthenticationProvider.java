package com.example.security.serverSide;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

/**
 * The UsernamePasswordAuthenticationFilter triggers the authentication, if necessary and possible.
 * It reads username and password from a login form request,
 * wraps them into a UsernamePasswordAuthenticationToken
 * and calls the configured AuthenticationManager to perform the authentication.
 *
 * In the default configuration, the AuthenticationManager is a ProviderManager
 * which holds a list of AuthenticationProviders to which it delegates the authentication request.
 * In our sample project we use a very basic InMemoryAuthenticationProvider which knows only one static user.
 * In a real world project we would instead use a database or LDAP provider (from the Spring Security LDAP module).
 */

@Component
class InMemoryAuthenticationProvider implements AuthenticationProvider {

    private static final Collection<UserInfo> userInfos = Set.of(
            new UserInfo("lufthansa", "1234",
                    Set.of(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("TESTER"))));

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserInfo userInfo = InMemoryAuthenticationProvider.userInfos.stream()
                .filter(b -> b.getUsername().equals(authentication.getName()))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(""));
        return new UsernamePasswordAuthenticationToken(userInfo, userInfo.getPassword(), userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}