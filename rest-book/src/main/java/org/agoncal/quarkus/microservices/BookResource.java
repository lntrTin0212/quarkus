package org.agoncal.quarkus.microservices;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    @RestClient
    NumberProxy proxy;

    @Inject
    Logger logger;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(summary = "Creates a Book", description = "Creates a Book with an ISBN number")
    @Retry(
        maxRetries = 1, delay = 2000
    )
    @Fallback(fallbackMethod = "fallingbackOnCreatingABook")
    public Response createABook(
                            @FormParam("title") String title,
                            @FormParam("author") String author,
                            @FormParam("year") int yearOfPublication,
                            @FormParam("genre") String genre) {

        Book book = new Book();
        book.isbn13 = proxy.generateIsbnNumber().isbn13;
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();
        return Response.status(201).entity(book).build();
    }

    public Response fallingbackOnCreatingABook(
                             String title,
                             String author,
                             int yearOfPublication,
                             String genre) throws FileNotFoundException {

        Book book = new Book();
        book.isbn13 ="Will be set later";
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();
        saveBookOnDisk(book);
        logger.warn("Book saved on disk: " + book);
        return Response.status(206).entity(book).build();
    }

    private void saveBookOnDisk(Book book) throws FileNotFoundException {
        String bookJson = JsonbBuilder.create().toJson(book);
        try(PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")){
            out.println(bookJson);
        }
    }
}

// rety fallback to recreate when other microservice is interrupted