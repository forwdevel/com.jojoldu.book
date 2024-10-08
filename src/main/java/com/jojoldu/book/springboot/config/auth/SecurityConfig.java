package com.jojoldu.book.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.jojoldu.book.springboot.domain.user.Role;
import com.jojoldu.book.springboot.config.auth.CustomOAuthUserService;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final CustomOAuthUserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http
                 .csrf().disable()
                 .headers().frameOptions().disable()
                 .and()
                     .authorizeRequests()
                     .antMatchers("/", "/css/**", "/images/**, ","/js/**", "/h2-console/**").permitAll()
                     .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                     .anyRequest().authenticated()
                 .and()
                     .logout()
                        .logoutSuccessUrl("/")
                 .and()
                     .oauth2Login()
                         .userInfoEndpoint()
                             .userService(customOAuth2UserService);

    }
}
