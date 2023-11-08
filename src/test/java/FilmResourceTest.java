import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

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
}
