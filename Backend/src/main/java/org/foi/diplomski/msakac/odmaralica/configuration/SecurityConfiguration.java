package org.foi.diplomski.msakac.odmaralica.configuration;

import lombok.RequiredArgsConstructor;
import org.foi.diplomski.msakac.odmaralica.security.jwt.JwtAuthenticationEntryPoint;
import org.foi.diplomski.msakac.odmaralica.security.jwt.JwtAuthenticationFilter;
import org.foi.diplomski.msakac.odmaralica.security.oauth.HttpCookieOAuth2AuthorizationRequestRepository;
import org.foi.diplomski.msakac.odmaralica.security.oauth.OAuth2AuthenticationFailureHandler;
import org.foi.diplomski.msakac.odmaralica.security.oauth.OAuth2AuthenticationSuccessHandler;
import org.foi.diplomski.msakac.odmaralica.service.security.implementation.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/login", "/auth/login-open-auth", "/auth/register", "/auth/activate").permitAll()
                .antMatchers(HttpMethod.GET, "/role").hasAuthority("admin")
                .antMatchers(HttpMethod.POST, "/role", "/user").hasAuthority("admin")
                .antMatchers(HttpMethod.PUT, "/role", "/user/**").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.DELETE, "/role", "/user/**").hasAuthority("admin")
                .antMatchers(HttpMethod.GET, "/user").hasAnyAuthority("admin", "moderator")
                .antMatchers(HttpMethod.GET, "/user/**").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.POST, "/user").hasAuthority("admin")
                .antMatchers(HttpMethod.DELETE, "/user/**").hasAuthority("admin")
                .antMatchers(HttpMethod.PUT, "/user/**").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.GET, "/accommodation-unit", "/accommodation-unit/**", "/accommodation-unit/find").permitAll()
                .antMatchers(HttpMethod.POST, "/accommodation-unit").hasAnyAuthority("admin", "moderator", "renter")
                .antMatchers(HttpMethod.DELETE, "/accommodation-unit/**").hasAnyAuthority("admin", "moderator", "renter")
                .antMatchers(HttpMethod.PUT, "/accommodation-unit/**").hasAnyAuthority("admin", "moderator", "renter")
                .antMatchers(HttpMethod.GET, "/activity-type").hasAuthority("admin")
                .antMatchers(HttpMethod.POST, "/address", "/amount", "/city").hasAnyAuthority("admin", "moderator", "renter")
                .antMatchers(HttpMethod.PUT, "/address", "/amount").hasAnyAuthority("admin", "moderator", "renter")
                .antMatchers(HttpMethod.DELETE, "/address/**", "/amount/**", "/city/**").hasAnyAuthority("admin", "moderator", "renter")
                .antMatchers(HttpMethod.GET, "/address", "/address/**", "/address/find", "/amount", "/amount/**", "/amount/find", "/city", "/city/**", "/city/find", "/country", "/country/**", "/country/find", "/country/with-regions-and-cities").permitAll()
                .antMatchers(HttpMethod.POST, "/image").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.GET, "/image/**", "/image/**/find").permitAll()
                .antMatchers(HttpMethod.DELETE, "/image/**").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.GET, "/log", "/log/encrypted").hasAuthority("admin")
                .antMatchers(HttpMethod.POST, "/price-period").hasAnyAuthority("admin", "moderator", "renter")
                .antMatchers(HttpMethod.PUT, "/price-period").hasAnyAuthority("admin", "moderator", "renter")
                .antMatchers(HttpMethod.DELETE, "/price-period/**").hasAnyAuthority("admin", "moderator", "renter")
                .antMatchers(HttpMethod.GET, "/price-period", "/price-period/**", "/price-period/find").permitAll()
                .antMatchers(HttpMethod.POST, "/region").hasAuthority("admin")
                .antMatchers(HttpMethod.PUT, "/region").hasAuthority("admin")
                .antMatchers(HttpMethod.DELETE, "/region/**").hasAuthority("admin")
                .antMatchers(HttpMethod.GET, "/region", "/region/**", "/region/find").permitAll()
                .antMatchers(HttpMethod.POST, "/reservation").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.PUT, "/reservation").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.DELETE, "/reservation/**").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.GET, "/reservation", "/reservation/**", "/reservation/find").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.POST, "/residence").hasAnyAuthority("moderator", "renter", "admin")
                .antMatchers(HttpMethod.PUT, "/residence").hasAnyAuthority("moderator", "renter", "admin")
                .antMatchers(HttpMethod.DELETE, "/residence/**").hasAnyAuthority("moderator", "renter", "admin")
                .antMatchers(HttpMethod.GET, "/residence", "/residence/**", "/residence/find", "/residence/aggregate", "/residence/aggregate/**").permitAll()
                .antMatchers(HttpMethod.POST, "/residence-type").hasAuthority("admin")
                .antMatchers(HttpMethod.PUT, "/residence-type").hasAuthority("admin")
                .antMatchers(HttpMethod.DELETE, "/residence-type/**").hasAuthority("admin")
                .antMatchers(HttpMethod.GET, "/residence-type", "/residence-type/**", "/residence-type/find").permitAll()
                .antMatchers(HttpMethod.POST, "/review").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.PUT, "/review").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.DELETE, "/review/**").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.GET, "/review", "/review/**", "/review/find").permitAll()
                .antMatchers(HttpMethod.GET, "/review/can-review/**").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.GET, "/privacy-request", "/privacy-request/**", "/privacy-request/find").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.POST, "/privacy-request").hasAnyAuthority("user", "moderator", "renter", "admin")
                .antMatchers(HttpMethod.PUT, "/privacy-request/accept/**").hasAnyAuthority("moderator", "admin")
                .antMatchers(HttpMethod.PUT, "/privacy-request").hasAnyAuthority("user", "moderator", "renter", "admin")
                .anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .oauth2Login().authorizationEndpoint().baseUri("/oauth2/authorize")
                .authorizationRequestRepository(cookieAuthorizationRequestRepository()).and().redirectionEndpoint()
                .baseUri("/oauth2/callback/*").and().userInfoEndpoint().userService(customOAuth2UserService)
                .and().successHandler(oAuth2AuthenticationSuccessHandler).failureHandler(oAuth2AuthenticationFailureHandler);
        return http.build();
    }
}
