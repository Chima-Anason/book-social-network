package com.anagracetech.book.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Anagracetech",
                        email = "anagracetech@gmail.com"
                ),
                description = "OpenApi documentation for Spring security",
                title = "OpenApi specification - Anagracetech",
                version = "1.0.0",
                license = @License(
                        name = "Licence name",
                        url = "http://some-url.com"

                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8088/api/v1"
                ),
                @Server(
                        description = "Production ENV",
                        url = "https://prod-env.com/api/v1"
        },
         security = {
                @SecurityRequirement(
                        name = "bearerAuth")
         }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Authentication with JWT token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
        )
public class OpenApiConfig {
}
