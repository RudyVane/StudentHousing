package com.example.studenthousing;

import com.example.studenthousing.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.example.studenthousing");
        appContext.refresh();

        UserService userService = (UserService) appContext.getBean("userService");
        userService.test();

        appContext.close();
    }
}
