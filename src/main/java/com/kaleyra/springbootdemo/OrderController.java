package com.kaleyra.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class OrderController {

    private static final String template = "Hello, %s!";
    public static final String HOSTNAME_PORT = "petstore.swagger.io";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable Long id) throws URISyntaxException {

        /*
            {
                id: 2,
                date: "04/02/2020"
                user: 0
            }
         */

        //1. get order from database
        Order order = new Order();
        order.id = 10L;
        order.date = new Date();
        order.user = new OrderUser();
        order.user.id = 0L; //

        //https://petstore.swagger.io/v2/pet/findByStatus?status=available
        //2. read user from external service
        OrderUser userFromRemoteService =
                restTemplate.getForObject(new URI("http://" + HOSTNAME_PORT + "/users/" + order.user.id), OrderUser.class);

        //

        order.user = userFromRemoteService;
        return order;

    }


    @GetMapping("/available-pets")
    public @ResponseBody List<Object> getAvailablePets() throws URISyntaxException {
        //https://petstore.swagger.io/v2/pet/findByStatus?status=available
        ResponseEntity<Object[]> responseEntity =
                restTemplate.getForEntity(
                        new URI("http://" + HOSTNAME_PORT + "/v2/pet/findByStatus?status=available"), Object[].class);
        Object[] objects = responseEntity.getBody();


        return Arrays.asList(objects);
    }


    @PostMapping("/pets")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPet(@RequestBody Pet pet) throws URISyntaxException {
        //POST https://petstore.swagger.io/v2/pet
//        {
//            "id": 0,
//                "category": {
//            "id": 0,
//                    "name": "string"
//        },
//            "name": "doggie",
//                "photoUrls": [
//            "string"
//         ],
//            "tags": [
//            {
//                "id": 0,
//                    "name": "string"
//            }
//         ],
//            "status": "available"
//        }

        ResponseEntity<Pet> petResponseEntity = restTemplate.postForEntity(
                new URI("http://" + HOSTNAME_PORT + "/v2/pet"), pet, Pet.class);

        System.out.println(petResponseEntity);

    }
}