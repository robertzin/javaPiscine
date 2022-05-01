package edu.school21.spring.service.application;

import edu.school21.spring.service.config.ApplicationConfig;
import edu.school21.spring.service.services.UsersService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UsersService userService = context.getBean("usersService", UsersService.class);
        String password = userService.signUp(19L, "newEmail@mail.ru");
        System.out.println(password);
        context.close();
    }
}
