import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class FilmResourceTest {

    @Test
    public void filmByIdTest() {
        given()
                .when().get("/film/1")
                .then()
                .statusCode(200)
                .body(containsString("ACADEMY DINOSAUR"));
    }

    @Test
    public void filmById2Test() {
        given()
                .when().get("/film/180")
                .then()
                .statusCode(200)
                .body(containsString("CONSPIRACY SPIRIT"));
    }


    @Test
    public void filmByLengthTest() {
        given()
                .when().get("/pagedFilms/6/60")
                .then()
                .statusCode(200)
                .body("$", hasSize(20));
    }

    @Test
    public void actorsTest() {
        given()
                .when().get("/actors/A/170")
                .then()
                .statusCode(200)
                .body("$", hasSize(3))
                .body("[0].title", equalTo("ANALYZE HOOSIERS"))
                .body("[0].length", equalTo(181))
                .body("[0].actors", hasItems("TOM MCKELLEN", "TOM MIRANDA", "JESSICA BAILEY", "GRETA MALDEN", "ED GUINESS"))
                .body("[1].title", equalTo("ALLEY EVOLUTION"))
                .body("[1].length", equalTo(180))
                .body("[1].actors", hasItems("KARL BERRY", "JUDE CRUISE", "ALBERT JOHANSSON", "GREGORY GOODING", "JOHN SUVARI"))
                .body("[2].title", equalTo("ANONYMOUS HUMAN"))
                .body("[2].length", equalTo(179))
                .body("[2].actors", hasItems("GRACE MOSTEL", "FAY KILMER", "JIM MOSTEL", "SUSAN DAVIS", "WHOOPI HURT", "EMILY DEE", "MERYL GIBSON", "MENA HOPPER", "ED GUINESS"));
    }

}
