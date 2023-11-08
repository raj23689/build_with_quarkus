package code.lazy.kick;

import code.lazy.kick.model.Film;
import code.lazy.kick.model.Repository.FilmRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Optional;

@Path(value = "/")
public class FilmResource {

    @Inject
    FilmRepository filmRepository;

    @GET
    @Path(value = "/helloWorld")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello, World!!";
    }

    @GET
    @Path(value = "/film/{filmId}")
    @Produces(value = MediaType.TEXT_PLAIN)
    public String getFilm(short filmId) {
        Optional<Film> film = filmRepository.getFilm(filmId);
        return film.isPresent() ? film.get().getTitle() : "No film was found!";
    }
}
