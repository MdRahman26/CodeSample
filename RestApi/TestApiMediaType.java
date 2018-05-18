package rest;


import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import reporting.TestLogger;

import static io.restassured.RestAssured.given;


public class TestApiMediaType extends ApiMediaType {
    String jsonMimeType = "application/json";
    String htmlMimeType = "text/html";

    // test media type: default
    @Test(groups = "apiTestApiMediaType", priority = 1)
    public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson() {
        TestLogger.log(convertToString("C: " + getClass().getSimpleName()) + " - M: " + convertToString(new Object() {
        }.getClass().getEnclosingMethod().getName()));

        Response response = given()
                .when().get(apiHome + "AllEmployeeResources")
                .then().extract().response();

        String mimeType = response.contentType();
        Assert.assertEquals(mimeType, jsonMimeType);
        System.out.println("Content Type: " + mimeType);
        System.out.println("TC1 Passed");
    }

    // test media type: json
    @Test(groups = "apiTestApiMediaType", priority = 2)
    public void givenRequestWithAcceptHeader_whenRequestIsExecuted_thenResponseContentTypeIsJson() {
        TestLogger.log(convertToString("C: " + getClass().getSimpleName()) + " - M: " + convertToString(new Object() {
        }.getClass().getEnclosingMethod().getName()));
        Response response = given().contentType(ContentType.JSON)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get(apiHome + "AllEmployeeResources")
                .then().extract().response();
        String mimeType = response.getContentType();
        Assert.assertEquals(mimeType, jsonMimeType);
        System.out.println("Content Type: " + mimeType);
        System.out.println("TC2 Passed");
    }

    // test media type: html
    @Test(groups = "apiTestApiMediaType", priority = 3)
    public void givenRequestWithAcceptHeader_whenRequestIsExecuted_thenResponseContentTypeIsHtml() {
        TestLogger.log(convertToString("C: " + getClass().getSimpleName()) + " - M: " + convertToString(new Object() {
        }.getClass().getEnclosingMethod().getName()));
        Response response = given().contentType(ContentType.JSON)
                .header("accept", "text/html")
                .header("Content-Type", "text/html")
                .when()
                .get(apiHome + "AllEmployeeResources")
                .then().extract().response();
        String mimeType = response.getContentType();
        Assert.assertEquals(mimeType, htmlMimeType);
        System.out.println("Content Type: " + mimeType);
        System.out.println("TC3 Passed");
    }
}
