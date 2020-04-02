package com.kaleyra.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    JdbcTemplate jdbcTemplate;

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

    @PostMapping("/users")
    public void addUser(@RequestBody User user) {


        if (user.getName() == null || user.getName().isEmpty())
            throw new IllegalArgumentException("Name is required");

        if (user.getEmail() == null || user.getEmail().isEmpty())
            throw new IllegalArgumentException("Email is required");


        Random random = new Random();
        jdbcTemplate.update(
                "INSERT INTO USERS VALUES (?, ?, ?)", counter.getAndIncrement(), user.getName(), user.getEmail());
    }

    /**
     * Retrieve a user by its id code
     *
     * @param id of the user
     * @return {@link User}
     */
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {

        return jdbcTemplate.queryForObject("SELECT * FROM USERS where id=?",
                BeanPropertyRowMapper.newInstance(User.class), id);
    }

    @PostConstruct
    public void afterClassCreated() {
        jdbcTemplate.execute("DROP TABLE users IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE users(id SERIAL, name VARCHAR(255), email VARCHAR(255))");

        System.out.println("Table users exists");
    }


    /**
     * /users?param=value  (get all users with name containing "like '%string%'")
     *
     * @return
     */
    @GetMapping("/users")
    public List<User> getUserList(@RequestParam(value = "name", defaultValue = "") String name) {

        String sqlAll = "SELECT * FROM USERS";
        List<Map<String, Object>> result  = null;
        if(name.isEmpty())
            result = jdbcTemplate.queryForList(sqlAll);
        else
            result = jdbcTemplate.queryForList(sqlAll + " WHERE name = ?", name);

        ArrayList<User> users = new ArrayList<>();
        for (Map<String, Object> map : result) {
            User user = new User();
            user.setId( Long.parseLong(map.get("id").toString()));
            user.setName((String) map.get("name"));
            user.setEmail((String) map.get("email"));
            users.add(user);
        }

        return users;
    }

}