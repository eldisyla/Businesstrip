package ch.clip.trips;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // ggf. "http://localhost:5173" für Vite
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // <== OPTIONS ergänzt
                .allowedHeaders("*")
                .allowCredentials(true); // <== optional, wenn Cookies oder Tokens genutzt werden
    }
}
