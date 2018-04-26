package com.springboot.integration;

import org.mockserver.client.server.MockServerClient;
import org.mockserver.initialize.ExpectationInitializer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/4/24
 */
public class HttpMockServerInitializer implements ExpectationInitializer {

    @Override
    public void initializeExpectations(MockServerClient mockServerClient) {
        System.out.println("@@@@@@@@@");
        mockServerClient.when(HttpRequest.request().withPath("/hello").withMethod("GET"))
                .respond(HttpResponse.response().withStatusCode(200).withBody("world"));
        mockServerClient.when(
                HttpRequest.request()
                        .withPath("/hello1")
                        .withMethod("GET")
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody("world")
        );
    }
}
