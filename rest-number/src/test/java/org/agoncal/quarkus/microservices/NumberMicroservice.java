package org.agoncal.quarkus.microservices;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(title="Number Microservice",
        description = "This microservice generates book numbers",
        version = "1.0",
        contact = @Contact(name = "@agoncal", url = "")
    ),
    tags = {
        @Tag(name = "api", description = "Public API"),
        @Tag(name = "numbers", description = "he")
    }
)
public class NumberMicroservice extends Application {
    
}
