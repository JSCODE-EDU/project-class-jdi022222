package com.comibird.anonymousforum.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI(@Value("v1.0") String appVersion) {

        Info info = new Info().title("Board API Docs").version(appVersion)
                .description("익명게시판 API Docs 입니다.")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact().name("jdi022222").url("https://https://github.com/jdi022222/").email("jdi022222@gmail.com"))
                .license(new License().name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"));

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
