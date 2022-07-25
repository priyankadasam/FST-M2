package liveProject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GitHubProject {
    RequestSpecification reqspec;
    String ssh="ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCNbHo41QtD54x7xtJkjN6E0fFGaPSfUi+ky2WovWb5LaVTqFVFjmzIQBKsC+xcLZVgmQri772qKYgHG7Logo6Uaz1b33bP+Ec/nT05yiVirKvB7O8d7wsHNrPae5GtJ6uGJ/VQpPdqjEO2+Exg7vzRzNNuL9bh1L9T3WZdVTmkB8yODYQbHJbT63tFZ0Nkuo5zhXxyxUrpHk/Ri/wP0jjwQFgkJheQCTCnummq+eWhXXb1El7OpLpqo3TaZu6V8KVYTfINqecooAsHOlowt2JR17srPZ7puxur6sWvDqLZgFNUk02r2OO4z4Za+G4HQPUZGY7aiQY4p1/G33TSG+WV";
    int id;
    String authorization="token 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";

    // Set Base URL
    String base_URI = "https://api.github.com";

    @BeforeClass
    public void setUp(){
        reqspec= new RequestSpecBuilder().setContentType(ContentType.JSON)
                .addHeader("Authorization", authorization)
                .setBaseUri(base_URI)
                .build();
    }

    @Test(priority = 1)
    public void req_post(){
        // Write the request body
       String reqBody = "{\"title\": \"TestAPIKey\",\"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCNbHo41QtD54x7xtJkjN6E0fFGaPSfUi+ky2WovWb5LaVTqFVFjmzIQBKsC+xcLZVgmQri772qKYgHG7Logo6Uaz1b33bP+Ec/nT05yiVirKvB7O8d7wsHNrPae5GtJ6uGJ/VQpPdqjEO2+Exg7vzRzNNuL9bh1L9T3WZdVTmkB8yODYQbHJbT63tFZ0Nkuo5zhXxyxUrpHk/Ri/wP0jjwQFgkJheQCTCnummq+eWhXXb1El7OpLpqo3TaZu6V8KVYTfINqecooAsHOlowt2JR17srPZ7puxur6sWvDqLZgFNUk02r2OO4z4Za+G4HQPUZGY7aiQY4p1/G33TSG+WV\"}";
       // String reqBody = "{\"title\":\"TestAPIKey\"}" ;

        Response response =
            given().spec(reqspec)
                    .body(reqBody).when().post("/user/keys"); // Send POST request
        String body = response.getBody().asPrettyString();
        System.out.println("test\n");
        System.out.println(body);
        id=response.then().extract().body().path("id");
    // Print response of POST request

    response.then().statusCode(201);

    }

    @Test(priority = 2)
    public void reqGet(){
        Response response =
                given().spec(reqspec)
                        .when().get("/user/keys/" + id);
        // Print response of Delete request
        String body = response.getBody().asPrettyString();
        System.out.println(body);
        response.then().statusCode(200);
    }


    @Test(priority = 3)
    public void reqDelete(){
        Response response =
                given().spec(reqspec)
                        .when().delete("/user/keys/" + id);
        // Print response of Delete request
        String body = response.getBody().asPrettyString();
        System.out.println(body);
        response.then().statusCode(204);


    }

}
