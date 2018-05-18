package rest;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import io.restassured.response.Response;
import reporting.TestLogger;

import static io.restassured.RestAssured.given;

public class TestApiStatusCode extends ApiStatusCode {
    // Testing the Status Code: 200 ok
    @Test(groups = "apiTestApiStatusCode", priority = 1)
    public void givenURIExists_whenResponseReceived() {
        //TestLogger.log(convertToString("C: " + getClass().getSimpleName()) + " - M: " + convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));

        Response response = given()                                             // content-type, cookies, params
                            .when().get(apiHome + "AllEmployeeResources")       // http method type
                            .then().statusCode(200).extract().response();       // result

        String status = response.getStatusLine(); // HTTP/1.1 200 OK
        String body = response.getBody().print();

        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.println(status);
        System.out.println("TC1 Passed");
    }

    // Testing the Status Code: 404 not found
    @Test(groups = "apiTestApiStatusCode", priority = 2)
    public void givenURIDoesNotExist_whenResponseReceived() throws IOException {
        TestLogger.log(convertToString("C: " + getClass().getSimpleName()) + " - M: " + convertToString(new Object() {
        }.getClass().getEnclosingMethod().getName()));
        String randomUri = RandomStringUtils.randomAlphabetic(12);
        Response response = given().when().get(apiHome + randomUri)
                .then().statusCode(404).extract().response();
        String status = response.getStatusLine();
        String body = response.getBody().print();
        Assert.assertEquals(response.getStatusCode(), 404);
        System.out.println(status);
        System.out.println("TC2 Passed");
    }

    // Testing the Status Code for all valid uri
    @Test(groups = "apiTestApiStatusCode", priority = 3)
    public void givenURIsExist_whenResponseReceived() throws IOException {
        TestLogger.log(convertToString("C: " + getClass().getSimpleName()) + " - M: " + convertToString(new Object() {
        }.getClass().getEnclosingMethod().getName()));
        List<String> validUriList = getValidUriList();
        for (String validUri : validUriList) {
            Response response = given().when().get(apiHome + validUri)
                    .then().extract().response();
            String status = response.getStatusLine();
            System.out.println(apiHome + validUri);
            System.out.println(status);
        }
        System.out.println("TC3 Passed");
    }

    // Testing the Status Code for five invalid uri
    @Test(groups = "apiTestApiStatusCode", priority = 4)
    public void givenURIsDoNotExist_whenResponseReceived() throws IOException {
        TestLogger.log(convertToString("C: " + getClass().getSimpleName()) + " - M: " + convertToString(new Object() {
        }.getClass().getEnclosingMethod().getName()));
        List<String> invalidUriList = getInvalidUriList();
        for (String invalidUri : invalidUriList) {
            Response response = given().when().get(apiHome + invalidUri)
                    .then().extract().response();
            String status = response.getStatusLine();
            System.out.println(apiHome + invalidUri);
            System.out.println(status);
        }
        System.out.println("TC4 Passed");
    }
}
