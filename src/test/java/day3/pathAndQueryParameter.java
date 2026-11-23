package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class pathAndQueryParameter {
// https://reqres.in/api/users?page=2
// https://reqres.in/api/users?page=2&id=5

    @Test
    void testRequest() {

        given()
                .pathParam("myPath", "users") // path parameter
                .queryParam("page", 2) // query parameter
                .queryParam("id", 5) // query parameter

                .when()
                .get("https://reqres.in/api/{myPath}") // FIXED: Removed {myPath2}

                .then()
                .statusCode(200)
                .log().all();
    }


}