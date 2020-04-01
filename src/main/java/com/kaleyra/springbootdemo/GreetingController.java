package com.kaleyra.springbootdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /**
     * GET /greeting
     *
     * @param name e.g. GET /greeting?name="Luigi"
     * @return JSON representation of {@link Greeting}
     */
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        //TODO your imagination

        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }


    @GetMapping("/even")
    public ArrayList<Integer> even(@RequestParam(value = "min", defaultValue = "0") Integer min,
                                   @RequestParam(value = "max", defaultValue = "10") Integer max) {
        //TODO your imagination

        ArrayList<Integer> evenArrayList = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            if (i % 2 == 0)
                evenArrayList.add(i);
        }

        return evenArrayList;
    }

    //TODO add user POST /users
    @PostMapping("/users")
    public void addUser(@RequestBody User user) {

        if (user.name == null || user.name.isEmpty())
            throw new IllegalArgumentException("Name is required");

        if (user.email == null || user.email.isEmpty())
            throw new IllegalArgumentException("Email is required");

        System.out.println("Save user!" + user);
    }


    @GetMapping("/users")
    public List<User> getUserList() {
        ArrayList<User> users = new ArrayList<>();

        User u1 = new User();
        u1.setName("Luigi");
        u1.setEmail("lt@kaleyra.com");

        User u2 = new User();
        u2.setName("Daniele");
        u2.setEmail("lt@kaleyra.com");

        users.add(u1);
        users.add(u2);

        return users;
    }

}