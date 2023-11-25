package com.square.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(
                        mvcMatcherBuilder.pattern("/"),                    mvcMatcherBuilder.pattern("/login"),                mvcMatcherBuilder.pattern("/sign-up")
                        ,mvcMatcherBuilder.pattern("/check-email"),         mvcMatcherBuilder.pattern("/check-email-token"),    mvcMatcherBuilder.pattern("/email-login")
                        ,mvcMatcherBuilder.pattern("/check-email-login"),   mvcMatcherBuilder.pattern("/login-link"),           mvcMatcherBuilder.pattern("/profile/*")
                ).permitAll()
                .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/profile/*")).permitAll()
                .anyRequest().authenticated()
        );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/images/**"))
                                    .requestMatchers(new AntPathRequestMatcher("/favicon.ico"))
                                    .requestMatchers(new AntPathRequestMatcher("node_modules/**"));
    }

}
