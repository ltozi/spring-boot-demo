package com.kaleyra.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class OrderController {

    private static final String template = "Hello, %s!";
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

        //2. read user from external service
        OrderUser userFromRemoteService =
                restTemplate.getForObject(new URI("http://localhost:8080/users/" + order.user.id), OrderUser.class);

        //

        order.user = userFromRemoteService;
        return order;

    }


}