package com.example.springsecurity;

import com.example.springsecurity.model.Role;
import com.example.springsecurity.model.User;
import com.example.springsecurity.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //
//    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
//          userService.saveRole(new Role(null,"ROLE_USER"));
//          userService.saveRole(new Role(null,"ROLE_MANAGER"));
//          userService.saveRole(new Role(null,"ROLE_ADMIN"));
//          userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

//          userService.saveUser(new User(null, "will", "1234","Will Smith", 2, "0913444488", "male", "Kinh", 2, 30, 20, new ArrayList<>()));
//          userService.saveUser(new User(null, "jim", "1234" ,"Jim Carry" , 3,"0912312445", "female", "Tay", 2, 40, 15, new ArrayList<>()));
//            userService.saveUser(new User(null, "admin", "1234", "admin", 0, null, null, null, 0, 0, 0,null, new ArrayList<>()));

//            userService.addRoleToUser("admin", "ROLE_ADMIN");

        };
    }
}

