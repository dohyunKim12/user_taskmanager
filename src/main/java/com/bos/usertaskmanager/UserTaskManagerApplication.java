package com.bos.usertaskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * UserTaskManagerApplication is the main entry point for the Spring Boot application.
 * It initializes the application context and starts the embedded server.
 *
 * @author dohyun.kim
 */

@EnableCaching
@SpringBootApplication
public class UserTaskManagerApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserTaskManagerApplication.class, args);
    }

}
