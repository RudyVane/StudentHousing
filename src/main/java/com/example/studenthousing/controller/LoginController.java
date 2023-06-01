//package com.example.studenthousing.controller;
//
//import com.example.studenthousing.AppException;
//import com.example.studenthousing.form.LoginForm;
//import com.example.studenthousing.model.CustomUserDetails;
//import com.example.studenthousing.model.User;
//import com.example.studenthousing.repository.UserRepository;
//import com.example.studenthousing.services.UserService;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.DependsOn;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.web.authentication.RememberMeServices;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@DependsOn("securityFilterChain")
//public class LoginController {
//
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public class CurrentUser{
//        Integer id;
//        String username;
//    }
//
//    @Data
//    public class LogoutResponse{}
//    @Data
//    @AllArgsConstructor
//    public class CsrfResponse{String token;}
//    private final RememberMeServices rememberMeServices;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private CustomUserDetails customUserDetails;
//
//    // Using POST to /login will log in an existing user
//    @PostMapping("/login")
//    public CurrentUser login(@Valid @RequestBody LoginForm form, BindingResult bindingResult,
//                             HttpServletRequest request, HttpServletResponse response) {
//
//        try {
//            if (request.getUserPrincipal() != null) {
//                throw new AppException("Please logout first.");
//            }
//
//            System.out.println(customUserDetails.isAccountNonExpired());
//            System.out.println(customUserDetails.isAccountNonLocked());
//            System.out.println(customUserDetails.isCredentialsNonExpired());
//            System.out.println(customUserDetails.isEnabled());
//
//            if (bindingResult.hasErrors()) {
//                throw new AppException("Invalid username or password");
//            }
//
//            try {
//                request.getSession(true);
//                request.login(form.getUsername(), form.getPassword());
//            } catch (ServletException e) {
//                System.out.println(e.getMessage() + " " + e.getCause());
//                throw new AppException("Invalid username or password");
//            }
//
//            var auth = (Authentication) request.getUserPrincipal();
//            var user = (User) auth.getPrincipal();
//            log.info("User {} logged in", user.getUsername());
//            rememberMeServices.loginSuccess(request, response, auth);
//
//            return new CurrentUser(user.getId(), user.getUsername());
//        } catch (AppException a) {
//            System.out.println(a.getMessage());
//        }
//        return new CurrentUser();
//    }
//
//    @PostMapping("/logout")
//    public LogoutResponse logout() {
//        return new LogoutResponse();
//    }
//
////    @GetMapping("/account")
////    public CurrentUser getCurrentUser(@AuthenticationPrincipal User user) {
////        return new CurrentUser(user.getId(), user.getUsername());
////    }
//
//    @GetMapping("/csrf")
//    public CsrfResponse csrf(HttpServletRequest request) {
//        var csrf = (CsrfToken) request.getAttribute("_csrf");
//        return new CsrfResponse(csrf.getToken());
//    }
//}
