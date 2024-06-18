package br.com.lca.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private final String schemeName = "bearerAuth";
    private final String bearerFormat = "JWT";
    private final String scheme = "bearer";

    private final SecurityScheme securityScheme = new SecurityScheme()
            .name(schemeName)
            .type(SecurityScheme.Type.HTTP)
            .bearerFormat(bearerFormat)
            .in(SecurityScheme.In.HEADER)
            .scheme(scheme);

    private Contact createContact() {
        Contact contact = new Contact();
        contact.setName("Lucas do Amaral Virmond");
        contact.setUrl("https://github.com/lucasvir/luzcameraacao-api");

        return contact;
    }

    @Bean
    public OpenAPI catchOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(schemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(schemeName, securityScheme)
                )
                .info(
                        new Info()
                                .title("Luz Camera Ação")
                                .description("Gerenciador de locação de equipamentos de audio visual.")
                                .version("1.0")
                                .contact(createContact())
                );
    }
}
