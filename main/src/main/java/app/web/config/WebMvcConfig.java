package app.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфигурация с добавлением статических данных
 */
@Configuration
@EnableJpaRepositories("app.web.repos")
//@EnableSpringDataWebSupport
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${imagePath}")
    String imagePath;

    @Value("${imgURL}")
    String imgURL;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler(imgURL + "**").addResourceLocations("file:///" + imagePath);
    }
}

