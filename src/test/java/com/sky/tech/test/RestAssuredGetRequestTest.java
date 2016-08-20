package com.sky.tech.test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.when;
import static com.google.common.truth.Truth.assertWithMessage;


public class RestAssuredGetRequestTest {

    private static final int EXPECTED_STATUS_OK_STATUS_CODE = 200;
    private static final String BASE_URI = "http://api.randomuser.me";
    private final List<String> personTitle = Arrays.asList("miss", "ms", "mrs", "mr", "monsieur", "mademoiselle");

    @Before
    public void setup() {
        RestAssured.baseURI  = BASE_URI;
    }

    @Test
    public void testGetRestRequestReturns200StatusCode() {
        Response response = when().get().then().contentType(ContentType.JSON).extract().response();
        assertWithMessage("Status Code is not 200").that(response.getStatusCode()).isEqualTo(EXPECTED_STATUS_OK_STATUS_CODE);
    }

    @Test
    public void testPersonTitleIsAnExpectedTitle() {
        Response response = when().get().then().extract().response();

        assertWithMessage("Incorrect Title")
                .that(response.jsonPath().getString("results.name.title[0]"))
                .isIn(personTitle);
    }
}
