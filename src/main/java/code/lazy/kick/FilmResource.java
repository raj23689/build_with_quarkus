package code.lazy.kick;

import code.lazy.kick.model.Film;
import code.lazy.kick.model.Repository.FilmRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

//    @GET
//    @Path("/pagedFilms/{page}/{minLength}")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String paged(long page, short minLength) {
//        return filmRepository.paged(page, minLength)
//                .map(f -> String.format("%s (%d min)", f.getTitle(), f.getLength()))
//                .collect(Collectors.joining("\n"));
//    }
    @GET
    @Path(value = "/pagedFilms/{page}/{minLength}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> paged(long page, short minLength) {
        return filmRepository.paged(page, minLength)
                .map(f -> {
                    Map<String, Object> filmMap = new HashMap<>();
                    filmMap.put("title", f.getTitle());
                    filmMap.put("length", f.getLength());
                    return filmMap;
                })
                .collect(Collectors.toList());
    }

    @GET
    @Path(value = "/actors/{startsWith}/{minLength}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> actors(String startsWith, short minLength) {
        return filmRepository.actors(startsWith, minLength)
                .map(f -> {
                    Map<String, Object> filmMap = new HashMap<>();
                    filmMap.put("title", f.getTitle());
                    filmMap.put("length", f.getLength());
                    filmMap.put("actors", f.getActors().stream()
                            .map(a -> String.format("%s %s", a.getFirstName(), a.getLastName()))
                            .collect(Collectors.toList()));
                    return filmMap;
                })
                .collect(Collectors.toList());
    }

    @GET
    @Path(value = "/update/{minLength}/{rentalRate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> update(short minLength, Float rentalRate) {
        filmRepository.updateRentalRate(minLength, rentalRate);
        return filmRepository.getFilms(minLength)
                .map(f -> {
                    Map<String, Object> filmMap = new HashMap<>();
                    filmMap.put("title", f.getTitle());
                    filmMap.put("length", f.getLength());
                    filmMap.put("rentalRate", f.getRentalRate());
                    return filmMap;
                })
                .collect(Collectors.toList());
    }

}
