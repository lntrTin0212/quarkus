package org.agoncal.quarkus.microservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient(configKey = "number.proxy")
@Path("/api/numbers")
@Produces(MediaType.APPLICATION_JSON)
public interface NumberProxy {
    @GET
    IsbnThirteen generateIsbnNumber();
}

