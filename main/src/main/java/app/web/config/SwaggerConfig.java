package app.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Класс, конфигурирующий документацию к апи и общие данные
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("app.web.сontroller"))//путь к контроллерам для сваггера
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Общее описание приложения + ссылка на сайт автора
     *
     * @return
     */
    @Value("${mysite}")
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Carsharing Api", "Select api from the list below", "1.0", null, new springfox.documentation.service.Contact("Author", "https://www.instagram.com/p/B7octB9lgHQ/", "negrosila@yandex.ru"), null, null, Collections.emptyList());
    }
}