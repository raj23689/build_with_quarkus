package code.lazy.kick;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path(value = "/")
public class FilmResource {

    @GET
    @Path(value = "/helloWorld")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello, World!!";
    }
}
