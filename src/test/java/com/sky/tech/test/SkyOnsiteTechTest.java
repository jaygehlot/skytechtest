package com.sky.tech.test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class SkyOnsiteTechTest {

    private static final String POST_BODY = "{\\r\\n \\\"query\\\" : {\\r\\n     \\\"operator\\\" : \\\"AND\\\",\\r\\n" +
            "\\\"operands\\\" : [\\r\\n       {\\r\\n         \\\"type\\\" : \\\"ITEM\\\",\\r\\n \\\"uuidType\\\" : \\\"Person\\\",\\r\\n" +
            "\\\"uuid\\\" : \\\"bbf29f57-0728-4a99-ac90-54c4b6cf1329\\\"\\r\\n       },\\r\\n       {\\r\\n         \\\"type\\\" " +
            ": \\\"ENTITY\\\",\\r\\n         \\\"uuidType\\\" : \\\"Person\\\",\\r\\n" +
            "\\\"uuid\\\" : \\\"061c6d50-4544-4891-aeec-31e6278e784e\\\"\\r\\n       }]\\r\\n   }\\r\\n}";

    private static final String BASE_URI = "http://jsonplaceholder.typicode.com";

    @Before
    public void setup() {
        RestAssured.baseURI  = BASE_URI;
        RestAssured.basePath = "/posts";
    }

    @Test
    public void testPostRequestToEndpointReturns201Response() {
        Response postResponse = given().body(POST_BODY).when().post();
        assertEquals(HttpStatus.SC_CREATED, postResponse.getStatusCode());
    }
}
