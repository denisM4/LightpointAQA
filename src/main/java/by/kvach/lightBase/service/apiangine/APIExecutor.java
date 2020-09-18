package by.kvach.lightBase.service.apiangine;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.emptyOrNullString;

public class APIExecutor {

    private final static String AUTHORISATION = "";
    private final static String BASE_URI = "";
    private final static String CONTENT_TYPE = "application/json; charset=utf-8";
    private final static String HOST = "";

    protected RequestSpecification createPostRequestSpecification() {

        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(CONTENT_TYPE)
                .addHeader("Host", HOST)
                .addHeader("Authorization", AUTHORISATION)
                .build();
    }

    protected ResponseSpecification createPostResponseSpecification() {

        return new ResponseSpecBuilder()
                .setDefaultParser(Parser.JSON)
                .expectStatusCode(200)
                .expectBody("error", emptyOrNullString())
                .build();

    }


}
