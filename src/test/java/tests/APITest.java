package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest {
    @Test
    public void getPet(){
        RequestSpecification request = RestAssured.given();
             request.contentType(ContentType.JSON);

        Response response = request.get("https://petstore.swagger.io/v2/pet/1");
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.contentType(ContentType.JSON);
        validatableResponse.statusCode(200);
        response.getBody().prettyPeek();


    }
}
