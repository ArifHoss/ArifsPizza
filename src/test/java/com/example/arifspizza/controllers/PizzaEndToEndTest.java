package com.example.arifspizza.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PizzaEndToEndTest {

@LocalServerPort
private int port;
    
    @Test
    void getPizzaReturnListOfPizzas(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:"+port+"/pizzas"))
                .build();

        var response = client.sendAsync(request,HttpResponse.BodyHandlers.ofString()).join();
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body()).isEqualTo("[{\"id\":1,\"name\":\"Veggitarisk\",\"price\":121,\"ingredients\":\"Ost,Tomatsås,Grönsaker,Mozzarella,KarinSpecial\"}]");

    }
}