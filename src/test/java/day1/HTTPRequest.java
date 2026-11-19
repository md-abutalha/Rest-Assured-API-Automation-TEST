package day1;

import java.util.HashMap;

import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HTTPRequest {

    private static final Logger log = LoggerFactory.getLogger(HTTPRequest.class);
    int id;

    @Test(priority = 1)
    void getUser() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2)) // Fixed this line
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .log().all();
    }
    @Test(priority = 2)
    void createUser(){
        HashMap<String, String> data = new HashMap<>();
        // Key হবে "name", Value হবে "Talha"
        data.put("name", "Talha");
        data.put("job", "Test Eng");

        id = given()
                .contentType(ContentType.JSON)
                .body(data)

                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

//                .then()
//                .statusCode(201) // 200 এর পরিবর্তে 201 হবে
//                .body("name", equalTo("Talha")) // রেসপন্স ভেরিফাই করা (Optional কিন্তু ভালো)
//                .body("job", equalTo("Test Eng"))
//                .log().all();
    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    void updateUser(){
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "Talha1");
        data.put("job", "Test Eng1");

        given()
                .contentType(ContentType.JSON)
                .body(data)

                .when()
                .put("https://reqres.in/api/users/"+id) // Fixed missing slash here

                .then()
                .statusCode(200) // Fixed status code from 201 to 200 for PUT
                .log().all();
    }

    @Test(priority = 4)
    void deleteUser(){
        given()
                .when()
                .delete("https://reqres.in/api/users/"+id                           )

                .then()
                .statusCode(204)
                .log().all();
    }



}