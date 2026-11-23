package day2;

import java.beans.JavaBean;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DiffJsonToPostRequest {

    String id; // Variable to store the ID

//    //post request using hashmap
//    @Test(priority = 1)
//    void tesPostUsingHashmap(){
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("name", "Smith");
//        data.put("location", "Canada");
//        data.put("phone", "165498765");
//
//        List<String> courses = new ArrayList<>();
//        courses.add("C#");
//        courses.add("RestAPI");
//        data.put("courses", courses);
//
//        // Use Response object to extract data
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .body(data)
//                .when()
//                .post("http://localhost:3000/students");
//
//        // 1. Validate
//        response.then()
//                .statusCode(201)
//                .body("name", equalTo("Smith"))
//                .body("courses[0]", equalTo("C#"))
//                .body("courses[1]", equalTo("RestAPI"))
//                .header("Content-Type", "application/json")
//                .log().all();
//
//        // 2. Extract the generated ID for the DELETE request
//        id = response.jsonPath().getString("id");
//        System.out.println("Extracted ID: " + id);
//    }
//
//    @Test(priority = 2, dependsOnMethods = {"tesPostUsingHashmap"})
//    void testDelete(){
//        given()
//                .when()
//                // Add the extracted ID to the URL
//                .delete("http://localhost:3000/students/" + id)
//                .then()
//                .statusCode(204) // 204 means No Content (Successful delete)
//                .log().all();
//    }




    //2. post request using org.json
//    @Test(priority = 1)
    void tesPostUsingORGJson(){

        JSONObject data = new JSONObject();
        data.put("name", "Talha");
        data.put("location", "Canada");
        data.put("phone", "165498765");

        String coursesArr[] = {"Java", "Python"};
        data.put("courses", coursesArr);

        // Use Response object to extract data
        Response response = given()
                .contentType(ContentType.JSON)
                .body(data.toString()) // <-- FIXED: Added .toString() so it sends raw JSON

                .when()
                .post("http://localhost:3000/students");

        // 1. Validate
        response.then()
                .statusCode(201)
                .body("name", equalTo("Talha"))
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("Python"))
                .header("Content-Type", "application/json")
                .log().all();

        // 2. Extract the generated ID for the DELETE request
        id = response.jsonPath().getString("id");
        System.out.println("Extracted ID: " + id);
    }

//    @Test(priority = 2, dependsOnMethods = {"tesPostUsingORGJson"})
    void testDeleteORGJson(){
        given()
                .when()
                .delete("http://localhost:3000/students/" + id)
                .then()
                .statusCode(200)
                .log().all();
    }

    // POST Request body using POJO class.
   // @Test(priority = 1)
    void tesPostRequestBodyUsingPOJO(){

        Pojo_PostClass data = new Pojo_PostClass();
        data.setName("Talha");
        data.setLocation("Dhaka");
        data.setPhone("01733993993");

        String coursesArr[] = {"Java", "Python"};
        data.setCourses(coursesArr); // This will now work perfectly! // Or use Arrays.asList("Java", "Python") depending on your POJO

        // Use Response object to extract data
        Response response = given()
                .contentType(ContentType.JSON)
                .body(data) // Pass the POJO directly

                .when()
                .post("http://localhost:3000/students");

        // 1. Validate
        response.then()
                .statusCode(201)
                .body("name", equalTo("Talha"))
                .body("courses[0]", equalTo("Java")) // This will now pass
                .body("courses[1]", equalTo("Python"))
                .header("Content-Type", "application/json")
                .log().all();

        // 2. Extract the generated ID for the DELETE request
        id = response.jsonPath().getString("id");
        System.out.println("Extracted ID: " + id);
    }

    // @Test(priority = 2, dependsOnMethods = {"tesPostRequestBodyUsingPOJO"})
    void testDeletePojoClass(){
        given()
                .when()
                .delete("http://localhost:3000/students/" + id)
                .then()
                .statusCode(200)
                .log().all();
    }



    // 4. Post request with external json file
    @Test(priority = 1)
    void tesPostRequestWithExternalJsonFile() throws FileNotFoundException {

        File f = new File(".\\body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        // Use Response object to extract data
        Response response = given()
                .contentType(ContentType.JSON)
                .body(data.toString()) // Pass the POJO directly

                .when()
                .post("http://localhost:3000/students");

        // 1. Validate
        response.then()
                .statusCode(201)
                .body("name", equalTo("Talha"))
                .body("courses[0]", equalTo("Java")) // This will now pass
                .body("courses[1]", equalTo("Python"))
                .header("Content-Type", "application/json")
                .log().all();

        // 2. Extract the generated ID for the DELETE request
        id = response.jsonPath().getString("id");
        System.out.println("Extracted ID: " + id);
    }

    @Test(priority = 2, dependsOnMethods = {"tesPostRequestBodyUsingPOJO"})
    void testDeleteExternalJson(){
        given()
                .when()
                .delete("http://localhost:3000/students/" + id)
                .then()
                .statusCode(200)
                .log().all();
    }

}