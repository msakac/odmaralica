package org.foi.diplomski.msakac.odmaralica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories
@EnableAspectJAutoProxy
public class Odmaralica {

    public static void main(String[] args) {
        SpringApplication.run(Odmaralica.class, args);
    }

    // @Bean
	// public WebMvcConfigurer corsConfigurer() {
	// 	return new WebMvcConfigurer() {
	// 		@Override
	// 		public void addCorsMappings(CorsRegistry registry) {
	// 			registry.addMapping("/**")
	// 			.allowedOrigins("http://localhost:3000", "http://192.168.1.11:3000")
	// 			.allowedMethods("GET", "POST", "PUT", "DELETE") // Add allowed methods
	// 			.allowCredentials(true) // If you need to send cookies or authentication headers
	// 			.maxAge(3600);
	// 		}
	// 	};
	// }
}
