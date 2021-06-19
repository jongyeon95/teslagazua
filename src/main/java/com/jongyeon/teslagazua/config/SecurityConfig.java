package com.jongyeon.teslagazua.config;

import com.jongyeon.teslagazua.role.Role;
import com.jongyeon.teslagazua.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http

                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                .antMatchers("/**","/css/**","/font/**","/image/**").permitAll()
                .antMatchers("/stock/**").permitAll()
                .antMatchers(HttpMethod.POST,"/comment/**").hasAnyRole(Role.USER.name(),Role.GUEST.name(),Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/images/**").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE,"/images/**").hasRole(Role.ADMIN.name())
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
