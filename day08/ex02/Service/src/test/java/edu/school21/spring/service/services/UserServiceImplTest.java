package edu.school21.spring.service.services;

import edu.school21.spring.service.config.TestApplicationConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserServiceImplTest {


    static AnnotationConfigApplicationContext context;

    @BeforeAll
    static void initContext() {
        context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
    }

    @Test
    void signUp() {
        UsersService bean = context.getBean("usersService", UsersService.class);
        String password = bean.signUp(20L, "newEmail@email.com");
        System.out.println("Password: " + password);
        Assertions.assertNotEquals(password, "");
    }

    @AfterAll
    static void closeContext() {
        context.close();
    }
}