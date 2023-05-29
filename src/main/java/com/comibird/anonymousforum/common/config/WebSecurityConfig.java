package com.comibird.anonymousforum.common.config;

import com.comibird.anonymousforum.authentication.util.JwtProvider;
import com.comibird.anonymousforum.authentication.filter.CustomAuthenticationFilter;
import com.comibird.anonymousforum.authentication.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;
    private final AuthenticationEntryPoint authenticationEntryPoint;// 인증 실패 또는 인증헤더가 전달받지 못했을때 핸들러
    private final AuthenticationSuccessHandler authenticationSuccessHandler;// 인증 성공 핸들러
    private final AuthenticationFailureHandler authenticationFailureHandler;// 인증 실패 핸들러
    private final AccessDeniedHandler accessDeniedHandler;// 인가 실패 핸들러

    private static final String[] ALL_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    private static final String[] GET_WHITELIST = {
            "/users/login",
            "/posts/**"
    };

    private static final String[] POST_WHITELIST = {
            "/users/signup",
            "/users/login",
            "/posts/**"
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
                                .antMatchers("/**").authenticated()
                ).formLogin(
                        formLogin -> formLogin
                                .disable()
                ).sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        ;

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

    @Bean
    public CustomAuthenticationFilter authenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/login");

        // 로그인 인증 성공
        customAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        // 로그인 인증 실패
        customAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtProvider);
    }
}
