package demo.web;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import demo.web.dto.GameDTO;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static demo.web.dto.GameDTO.Type;
import static demo.web.dto.GameDTO.Type.RPG;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.OK;

public class GameControllerTest {

    @Before
    public void setUp(){
        //conf en local
        RestAssured.port = 8080;

        // conf pour serveur distant :
            // ip benoit : 192.168.43.182
            // ip ahri : 192.168.43.155
//        RestAssured.port = 8080;
//        RestAssured.baseURI = "http://192.168.43.182";
    }

    @Test
    public void should_GetAllGames(){
        given()
            .log().all()
        .when()
            .get("/game")
        .then()
            .log().all()
            .statusCode(OK.value())
            .body("[0].id", is(1))
            .body("[0].name", is("Game1"))
            .body("[0].type", is("RPG"))
            .body("[1].id", is(2))
            .body("[1].name", is("Game2"))
            .body("[1].type", is("FPS"))
        ;
    }

    @Test
    public void should_GetOneGame(){
        given()
            .log().all()
        .when()
            .get("/game/1")
        .then()
            .log().all()
            .statusCode(OK.value())
            .body("id", is(1))
            .body("name", is("Game1"))
            .body("type", is("RPG"));
    }

    @Test
    public void should_CreateAGame_AndGetIt(){
        String name = "lastGame";
        Type type = RPG;

        GameDTO gameDTO = new GameDTO();
        gameDTO.setName(name);
        gameDTO.setType(type);

        System.out.println(gameDTO);

        given()
            .log().all()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(gameDTO)
        .when()
            .post("/game")
        .then()
            .log().all()
            .statusCode(OK.value())
            .body("id", is(21))
            .body("name", is(name))
            .body("type", is(type.toString()))
        ;

        // delete the previously inserted item
        when().post("/game/21");
    }

    @Test
    public void should_DeleteOneGame(){
        // create the game to delete
        String name = "gameToDelete";
        Type type = RPG;

        GameDTO gameDTO = new GameDTO();
        gameDTO.setName(name);
        gameDTO.setType(type);

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(gameDTO)
        .when()
            .post("/game")
        ;

        // delete this game
        given()
            .log().all()
        .when()
            .delete("/game/21")
        .then()
            .log().all()
            .statusCode(OK.value())
            .body("id", is(21))
            .body("name", is("gameToDelete"))
            .body("type", is("RPG"))
        ;
    }

    @Test
    public void shouldNot_GetOneGame(){
        given()
            .log().all()
        .when()
            .get("/game/1000")
        .then()
            .statusCode(400);
    }
}
