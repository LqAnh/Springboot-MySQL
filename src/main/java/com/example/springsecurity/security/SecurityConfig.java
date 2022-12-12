package com.example.springsecurity.security;

import com.example.springsecurity.filter.CustomAuthenFilter;
import com.example.springsecurity.filter.CustomAuthorizationFilter;
import com.sun.javafx.collections.ImmutableObservableList;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable();
        CustomAuthenFilter customAuthenFilter = new CustomAuthenFilter(authenticationManagerBean());
        customAuthenFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
//        http.cors().and();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**",
                "/api/token/refresh/**",
                "/news/**",
                "/api/signup",
                "/api/getpost/**",
                "/api/getpostcmt/**",
                "/city/**",
                "/district/**",
                "/kvut/**",
                "/sex/**",
                "/dantoc/**",
                "/point/getpoint",
                "/point/getpointid",
                "/prize/getprize",
                "/prize/getprizeid",
                "/nganhhoc",
                "/point/getavgpoint"
                ).permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/users/**").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/api/crpost").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/api/crpostcmt/postId/comment").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/point/crpoint/").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/prize/crprize/").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/api/user/delete/**").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT,"/api/user/up").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(GET,"/api/user/get").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(GET,"/api/user/adget/**").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT,"/point/uppoint").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET,"/point/getpointjwt").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT,"/prize/upprize").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT,"/prize/del").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET,"/prize/getprizejwt").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT,"/upnew/id").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/crnew").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE,"/delnew/id").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT,"/user/changepass").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET,"/predict/getprejwt").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/crnh").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE,"/delnh/id").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT,"/upnh/id").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET,"/nganhhoc/id").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedMethods("*");
//            }
//        };
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
