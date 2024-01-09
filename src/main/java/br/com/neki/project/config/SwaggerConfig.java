package br.com.neki.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
public class SwaggerConfig {
	
    @Bean
    public OpenAPI OpenAPI() {
        return new OpenAPI()
            .components(new Components()
            .addSecuritySchemes("bearerAuth", new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            )
        )
            .info(new Info()
                .title("Skill+")
                .version("0.10v")
                .description("Backend de um projeto desenvolvido para um processo seletivo. Ele foi construído usando Java 11 e possui um sistema de login que utiliza tokens JWT para autenticação. O projeto inclui um CRUD completo para perfil, habilidades, habilidades do usuário e logs para auditoria. Além disso, possui tratamento de erros com ErrorResponse e exceções personalizadas.")
                .contact(new Contact()
                .name("Lucas Caldas")
                .email("lucastere10@gmail.com")
                )
            )
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
};