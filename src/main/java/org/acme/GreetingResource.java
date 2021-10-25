package org.acme;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.smallrye.mutiny.Uni;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @Inject
    GreetingService greetingService;

    @GET
    @Path("/cache/{key}")
    public String getCache(@PathParam("key") String key) {
        return greetingService.get(key);
    }

    @GET
    @Path("/cache/reactive")
    public Uni<List<String>> getReactive() {
        return greetingService.getReactive();
    }

    @POST
    @Path("/cache")
    public PersonCache putCache(PersonCache personCache) {
        greetingService.set(personCache.key, personCache.value);
        return personCache;
    }
}