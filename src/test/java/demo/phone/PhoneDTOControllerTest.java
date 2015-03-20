package demo.phone;


import demo.phone.dto.PhoneDTO;
import demo.phone.repository.PhoneRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static demo.phone.dto.PhoneDTO.Builder;
import static demo.phone.dto.PhoneDTO.newBuilder;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebIntegrationTest
public class PhoneDTOControllerTest {
    @Autowired
    private PhoneRepository phoneRepository;

    // test phone 1
    private String serial1 = "123456789";
    private String num1 = "0798653214";
    private boolean stolen1 = true;
    // test phone 2
    private String serial2 = "987654321";
    private String num2 = "0698653214";
    private boolean stolen2 = true;

    private String anonymous = "**********";

    @Before
    public void setUp() {
//        RestAssured.port = 8080;
//        RestAssured.baseURI = "http://192.168.43.182";
    }

    @Test
    public void should_GetAllPhone() {
        given()
            .log().all()
        .when()
            .get("/phone")
        .then()
            .log().all()
            .statusCode(200)
            // first phone
            .body("[0].id", is(1))
            .body("[0].serialNumber", is(serial1))
            .body("[0].number", is(num1))
            .body("[0].firstName", is(anonymous))
            .body("[0].lastName", is(anonymous))
            .body("[0].stolen", is(stolen1))
            // second phone
            .body("[1].id", is(2))
            .body("[1].serialNumber", is(serial2))
            .body("[1].number", is(num2))
            .body("[1].firstName", is(anonymous))
            .body("[1].lastName", is(anonymous))
            .body("[1].stolen", is(stolen2));
    }

    @Test
    public void should_GetOnePhone() {
        given()
            .log().all()
        .when()
            .get("/phone/1")
        .then()
            .log().all()
            .statusCode(200)
            .body("id", is(1))
            .body("serialNumber", is(serial1))
            .body("number", is(num1))
            .body("firstName", is(anonymous))
            .body("lastName", is(anonymous))
            .body("stolen", is(stolen1));
    }

    @Test
    public void should_UpdateStolen() {
        given()
            .log().all()
            .contentType(JSON)
            .accept(JSON)
            .body("{\"stolen\": " + !stolen1 + "}")
        .when()
            .put("/phone/1")
        .then()
            .log().all()
            .statusCode(200)
            .body("id", is(1))
            .body("serialNumber", is(serial1))
            .body("number", is(num1))
            .body("stolen", is(!stolen1));

        // revert update
        given()
            .log().all()
            .contentType(JSON)
            .accept(JSON)
            .body("{\"stolen\": " + stolen1 + "}")
        .when()
            .put("/phone/1");
    }

    @Test
    public void should_CreatePhone_AndGetIt() {
        String serialNumber = "12568923";
        String number = "0732657846";
        String firstName = "Jane";
        String lastName = "Banana";
        boolean stolen = false;

        Builder phoneDTOBuilder = newBuilder(serialNumber, number, stolen);

        phoneDTOBuilder.withFirstName(firstName);
        phoneDTOBuilder.withLastName(lastName);

        PhoneDTO phoneDTO = phoneDTOBuilder.build();

        given()
            .log().all()
            .contentType(JSON)
            .accept(JSON)
            .body(phoneDTO)
        .when()
            .post("/phone")
        .then()
            .log().all()
            .statusCode(200)
            .body("id", is(3))
            .body("serialNumber", is(serialNumber))
            .body("number", is(number))
            .body("firstName", is(anonymous))
            .body("lastName", is(anonymous))
            .body("stolen", is(stolen));

        // a healthy DB is a nice DB
        phoneRepository.delete(3L);
    }

    @Test
    public void shouldNot_GetOnePhone() {
        given()
            .log().all()
        .when()
            .get("/phone/0")
        .then()
            .log().all()
            .statusCode(404)
            .body("message", is("Phone not found"));
    }

}