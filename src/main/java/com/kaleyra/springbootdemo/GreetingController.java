package com.kaleyra.springbootdemo;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

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


        if (user.name == null || user.name.isEmpty())
            throw new IllegalArgumentException("Name is required");

        if (user.email == null || user.email.isEmpty())
            throw new IllegalArgumentException("Email is required");


        Random random = new Random();
        jdbcTemplate.update(
                "INSERT INTO USERS VALUES (?, ?, ?)", random.nextInt(1_000_000), user.name, user.email);
    }

    /**
     * Retrieve a user by its id code
     *
     * @param id of the user
     * @return {@link User}
     */
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM USERS where id=?", User.class, id);
    }

    @PostConstruct
    public void afterClassCreated() {
        jdbcTemplate.execute("DROP TABLE users IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE users(id SERIAL, name VARCHAR(255), email VARCHAR(255))");

        System.out.println("Table users exists");
    }


    @GetMapping("/users")
    public List<User> getUserList() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM USERS");

        ArrayList<User> users = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            User user = new User();
            user.setId( Long.parseLong(map.get("id").toString()));
            user.setName((String) map.get("name"));
            user.setEmail((String) map.get("email"));
            users.add(user);
        }

        return users;
    }

    @GetMapping("/patients")
    public List<Patient> getpatients() {
        ArrayList<Patient> patients = new ArrayList<>();

        Patient u1 = new Patient();
        u1.setPatientId(1);
        u1.setName("Luigi");
        u1.setEmail("lt@kaleyra.com");
        u1.setHospital("Appolo Hospital");

        Patient u2 = new Patient();
        u2.setPatientId(2);
        u2.setName("Daniele");
        u2.setEmail("lt@kaleyra.com");
        u2.setHospital("Lombardia Hospital");

        Patient u3 = new Patient();
        u3.setPatientId(3);
        u3.setName("vinitha");
        u3.setEmail("lt@kaleyra.com");
        u3.setHospital("Milan HQ Hospital");

        patients.add(u1);
        patients.add(u2);
        patients.add(u3);

        return patients;
    }


    @GetMapping("/Hospitals")
    public List<Hospital> getHospitals() {
        ArrayList<Hospital> Hospitals = new ArrayList<>();

        Hospital u1 = new Hospital();
        u1.setName("john");
        u1.setSpeciality("cardiologist");
        u1.setHospitaladdress("Appolo Hospital,street-22");

        Hospital u2 = new Hospital();
        u2.setName("mike");
        u2.setSpeciality("physician");
        u2.setHospitaladdress("Lombardia Hospital,via stafano 11");

        Hospital u3 = new Hospital();
        u3.setName("vinitha");
        u3.setSpeciality("dermatologist,");
        u3.setHospitaladdress("Milan HQ Hospital,via isimbardi 24");

        Hospitals.add(u1);
        Hospitals.add(u2);
        Hospitals.add(u3);

        return Hospitals;
    }

    @GetMapping("/patients/{patientId}")
    public Patient getPatient(@PathVariable long patientId) {
        return jdbcTemplate.queryForObject("SELECT * FROM patients where patientId=?", Patient.class, patientId);
    }
}