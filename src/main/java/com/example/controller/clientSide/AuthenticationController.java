//package com.example.controller.clientSide;
//
//import com.example.entity.clientSide.dto.SignUpDto;
//import com.example.entity.clientSide.dto.UserDto;
//import com.example.security.clientSide.CookieAuthenticationFilter;
//import com.example.service.clientSide.AuthenticationService;
//import com.example.service.clientSide.UserService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//import java.net.URI;
//import java.time.Duration;
//import java.time.temporal.ChronoUnit;
//import java.util.Optional;
//import java.util.stream.Stream;
//
//@RestController
//@RequestMapping("/v1")
//public class AuthenticationController {
//
//    private final UserService userService;
//    private final AuthenticationService authenticationService;
//
//    public AuthenticationController(UserService userService,
//                                    AuthenticationService authenticationService) {
//        this.userService = userService;
//        this.authenticationService = authenticationService;
//    }
//
//    @PostMapping("/signIn")
//    public ResponseEntity<UserDto> signIn(@AuthenticationPrincipal UserDto user,
//                                          HttpServletResponse servletResponse) {
//
//        Cookie authCookie = new Cookie(CookieAuthenticationFilter.COOKIE_NAME, authenticationService.createToken(user));
//        authCookie.setHttpOnly(true);
//        authCookie.setSecure(true);
//        authCookie.setMaxAge((int) Duration.of(100, ChronoUnit.SECONDS).toSeconds());
//        authCookie.setPath("/");
//
//        servletResponse.addCookie(authCookie);
//
//        return ResponseEntity.ok(user);
//    }
//
//    @PostMapping("/signUp")
//    public ResponseEntity<UserDto> signUp(@RequestBody @Valid SignUpDto user) {
//        UserDto createdUser = userService.signUp(user);
//        return ResponseEntity.created(URI.create("/users/" + createdUser.getId() + "/profile")).body(createdUser);
//    }
//
//    @PostMapping("/signOut")
//    public ResponseEntity<Void> signOut(HttpServletRequest request) {
//        SecurityContextHolder.clearContext();
//
//        Optional<Cookie> authCookie = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
//                .filter(cookie -> CookieAuthenticationFilter.COOKIE_NAME
//                        .equals(cookie.getName()))
//                .findFirst();
//
////        authCookie.isPresent(cookie -> cookie.setMaxAge(0));
//
//        authCookie.get().setMaxAge(0);
//        return ResponseEntity.noContent().build();
//    }
//}
