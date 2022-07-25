package examples;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static  org.hamcrest.Matchers.equalTo;

import java.net.ResponseCache;

import static io.restassured.RestAssured.given;

public class FirstTest {

    //Base URI
    final String baseURI = "https://petstore.swagger.io/v2/pet";

    @Test
    public void simpleTest(){
        //Get a response
        Response response = given().contentType(ContentType.JSON).
                when().get(baseURI + "/findByStatus?status=sold");
       // System.out.println(response.getBody()); //prints json
        System.out.println(response.getBody().asPrettyString());

        System.out.println(response.getBody().asString());

        //System.out.println(response.getHeaders().asList());
        String petstatus=response.then().extract().body().path("[0].status");
        String dateHeader = response.then().extract().header("Date");
        System.out.println("pet status: " +petstatus );
        System.out.println("Date header: " +dateHeader );

        //Assertions
        response.then().statusCode(200);
        response.then().body("[0].status",equalTo("sold"));

    }
}
