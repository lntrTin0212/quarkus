package org.agoncal.quarkus.microservices;

import javax.json.bind.annotation.JsonbProperty;

public class IsbnThirteen {

    @JsonbProperty("isbn_13")
    public String isbn13;

    @JsonbProperty("isbn_10")
    public String isbn10;
}
