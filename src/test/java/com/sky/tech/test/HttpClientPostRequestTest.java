package com.sky.tech.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class HttpClientPostRequestTest {

    private static final String BASE_URI = "http://jsonplaceholder.typicode.com/posts";
    private URI endPointUri = null;
    private static final String POST_REQUEST_BODY = "{ \"title\": \"jay\", \"body\": \"gehlot\",  \"name\": \"JG\",\"email\": \"gehlotj@gmail.com\", \"userId\": 103 }";
    private static final Gson gsonConverter = new GsonBuilder().create();
    private HttpClient client = null;
    private HttpPost postRequest = null;

    @Before
    public void setup() throws URISyntaxException {
        endPointUri = new URIBuilder(BASE_URI).build();
        client = HttpClientBuilder.create().build();
    }

    @Test
    public void testHttpPostRequestToEndpointReturns201Response() throws Exception {
        postRequest = new HttpPost(endPointUri);
        postRequest.addHeader("Content-Type", "application/json");

        StringEntity params =new StringEntity(POST_REQUEST_BODY);
        postRequest.setEntity(params);

        assertEquals(HttpStatus.SC_CREATED, executeRequest(postRequest, client).getStatusLine().getStatusCode());
    }

    @Test
    public void testHttpPostResponseContainsName() throws Exception {
        postRequest = new HttpPost(endPointUri);
        postRequest.addHeader("Content-Type", "application/json");

        StringEntity params =new StringEntity(POST_REQUEST_BODY);
        postRequest.setEntity(params);

        String json = EntityUtils.toString(executeRequest(postRequest, client).getEntity(), "UTF-8");
        UserDetails userDetails = gsonConverter.fromJson(json, UserDetails.class);

        assertThat(userDetails.getName(), is("JG"));
    }

    /**
     * Executes a HTTP method such as Get, Post, Delete etc and returns the response
     * @param httpMethod Get or Delete request
     * @return HTTP response
     */
    private static HttpResponse executeRequest(HttpRequestBase httpMethod, HttpClient client) {
        HttpResponse response = null;

        try {
            response = client.execute(httpMethod);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            httpMethod.releaseConnection();
        }
        return response;
    }
}
