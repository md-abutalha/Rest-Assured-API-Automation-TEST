package day3;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.proxy;

public class CookieDemo {

    private static final Logger log = LoggerFactory.getLogger(CookieDemo.class);

    @Test(priority = 1)
    void testCookies() {

        given()

                .when()
                .get("https://www.google.com/")

                .then()
                .cookie("AEC", "AakniGOLxRQC9fgi6mjPYfT76_mMHEZC-z_5xRB2ApPi8Ag2HZdJvBWmxZU")
                .log().all();
    }


    @Test(priority = 2)
    void testCookiesInfo() {
        Response response = given()
                .when()
                .get("https://www.google.com/");

        //single cookies get
//        String cookie_value = response.getCookie("AEC");
//        System.out.println("Cookie value is : " + cookie_value);

        //get cookies all values
        Map<String, String> cookies_values = response.getCookies();
//      System.out.println(cookies_values.keySet());

        for (String k : cookies_values.keySet()) {
            String cookies_value = response.getCookie(k); // FIXED: Changed to getCookie(k)
            System.out.println(k + " = " + cookies_value); // Added 'k' to print the name too
        }


    }


}
