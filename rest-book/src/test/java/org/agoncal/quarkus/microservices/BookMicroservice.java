package org.agoncal.quarkus.microservices;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
    info = @Info(title = "Book API",
    description = "Creates books",
    version = "1.0",
    contact = @Contact(name = "@agoncal")
    ),
    tags = {
        @Tag(name = "api", description = "Public Api"),
        @Tag(name = "books", description = "Interested in books")
    }
)
public class BookMicroservice extends Application {
    
}
