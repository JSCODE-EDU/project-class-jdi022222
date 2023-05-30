package com.comibird.anonymousforum.auth;

import com.comibird.anonymousforum.auth.jwt.JwtAccessDeniedHandler;
import com.comibird.anonymousforum.auth.jwt.JwtAuthenticationEntryPoint;
import com.comibird.anonymousforum.auth.jwt.JwtSecurityConfig;
import com.comibird.anonymousforum.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;// 인증 실패 또는 인증헤더가 전달받지 못했을때 핸들러
    private final JwtAccessDeniedHandler accessDeniedHandler;// 인가 실패 핸들러

    private static final String[] ALL_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/h2-console/**",
            "/favicon.ico"
    };

    private static final String[] GET_WHITELIST = {
            "/posts/**"
    };

    private static final String[] POST_WHITELIST = {
            "/users/signup",
            "/users/login",
            "/users/reissue",
            "/posts/**"
    };

    private static final String[] DELETE_WHITELIST = {
            "/users/logout/*",
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors(
                        cors -> cors
                                .configurationSource(corsConfigurationSource()))
                .csrf(
                        csrf -> csrf
                                .disable()
                )
                .exceptionHandling(
                        exceptionHandling -> exceptionHandling
                                .authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler)
                )
                .authorizeRequests(
                        authorizeRequests -> authorizeRequests
                                .antMatchers(ALL_WHITELIST).permitAll()
                                .antMatchers(GET, GET_WHITELIST).permitAll()
                                .antMatchers(POST, POST_WHITELIST).permitAll()
                                .antMatchers(DELETE, DELETE_WHITELIST).permitAll()
                                .antMatchers("/**").authenticated()
                ).formLogin(
                        formLogin -> formLogin
                                .disable()
                ).sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
                .apply(new JwtSecurityConfig(jwtProvider));

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("http://localhost:5000");
        corsConfiguration.addAllowedMethod(GET);
        corsConfiguration.addAllowedMethod(POST);
        corsConfiguration.addAllowedMethod(DELETE);
        corsConfiguration.addAllowedMethod(PUT);
        corsConfiguration.addAllowedMethod(PATCH);
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }
}
