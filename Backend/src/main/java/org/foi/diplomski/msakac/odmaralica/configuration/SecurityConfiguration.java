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
                        .cors()
                        .and()
                        .csrf()
                        .disable()
				        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                        .authorizeRequests()
                        // Pristup svima (GET, POST, PUT, DELETE)
                        .antMatchers(
                            "/auth/register",
                            "/auth/login",
                            "/auth/login-open-auth",
                            "/auth/activate",
                            "/swagger-ui/**",
                            "/swagger-ui.html")
                            .permitAll()
                        // Pristup svima (GET)
                        .antMatchers(HttpMethod.GET,
                            "/city/**",
                            "/user/{id}",
                            "/region/**",
                            "/country/**",
                            "/image/**",
                            "/residence-type/**",
                            "/residence/**",
                            "/address/**",
                            "/accommodation-unit/**",
                            "/price-period/**",
                            "/reservation/**",
                            "/amount/**",
                            "/review/**")
                            .permitAll()
                            // Pristup korisniku (PUT)
                            .antMatchers(HttpMethod.PUT, "/user", "/reservation").hasAnyAuthority("user", "renter", "moderator", "admin")
                            // Pristup korisniku (POST)
                            .antMatchers(HttpMethod.POST, "/reservation", "/image", "/review").hasAnyAuthority("user","renter", "moderator", "admin")
                            .antMatchers(HttpMethod.DELETE, "/reservation", "/image").hasAnyAuthority("user","renter", "moderator", "admin")
                            //FIXME Renter ve more apsolutno sve, treba rijesiti na razini servisa
                            .antMatchers(HttpMethod.POST, "/residence", "/address", "/accommodation-unit", "/price-period", "/amount").hasAnyAuthority("renter", "moderator", "admin") 
                            .antMatchers(HttpMethod.PUT, "/residence", "/address", "/accommodation-unit", "/price-period", "/amount").hasAnyAuthority("renter", "moderator", "admin") 
                            .antMatchers(HttpMethod.DELETE, "/residence", "/address", "/accommodation-unit", "/price-period", "/amount").hasAnyAuthority("renter", "moderator", "admin") 
                            // Pristup samo moderatoru (GET, POST, PUT, DELETE)
                            .antMatchers("/review", "/user/**", "/user").hasAuthority("moderator")
                        // Pristup samo adminu (GET, POST, PUT, DELETE)
                        .antMatchers(
                            "/role",
                            "/log",
                            "/activity-type",
                            "/user",
                            "/country",
                            "/city",
                            "/region",
                            "/residence-type",
                            "/reservation",
                            "/review")
                            .hasAuthority("admin")
                        .anyRequest()
                        .authenticated()
                        .and()
                        .exceptionHandling()
                        .authenticationEntryPoint(unauthorizedHandler).and()
                        .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                        .oauth2Login()
                            .authorizationEndpoint()
                                .baseUri("/oauth2/authorize")
                                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                                .and()
                            .redirectionEndpoint()
                                .baseUri("/oauth2/callback/*")
                                .and()
                            .userInfoEndpoint()
                                .userService(customOAuth2UserService)
                                .and()
                            .successHandler(oAuth2AuthenticationSuccessHandler)
                            .failureHandler(oAuth2AuthenticationFailureHandler);
        return http.build();
    }


}
