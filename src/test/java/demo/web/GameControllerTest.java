package demo.web;

import com.jayway.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by Benoit on 19/03/2015.
 */
public class GameControllerTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://192.168.43.155";
        RestAssured.port = 9000;
    }
    @Test
    public void should_GetOneGame(){
        given()
                .log().all()
        .when()
                .get("/game")
        .then()
                .log().all()
                .statusCode(OK.value())
                .body("id", is(1))
                .body("name",is("Dying Light"))
                .body("type", is("FPS"));
    }
}
