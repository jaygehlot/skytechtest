package com.sky.tech.test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class RestAssuredPostRequestTest {

    private static final String POST_BODY = "{ \"title\": \"jay\", \"body\": \"gehlot\",  \"name\": \"JG\",\"email\": \"gehlotj@gmail.com\", \"userId\": 103 }";
    private static final String BASE_URI = "http://jsonplaceholder.typicode.com";

    @Before
    public void setup() {
        RestAssured.baseURI  = BASE_URI;
        RestAssured.basePath = "/posts";
    }

    @Test
    public void testPostRequestToEndpointReturns201Response() {
        Response postResponse = given().contentType("application/json").body(POST_BODY).when().post();
        assertEquals(HttpStatus.SC_CREATED, postResponse.getStatusCode());
    }

    @Test
    public void testPostRequestContainsCorrectEmailId() {
        Response postResponse = given().contentType("application/json").body(POST_BODY).when().post();
        assertEquals(postResponse.path("email"), "gehlotj@gmail.com");
    }
}
