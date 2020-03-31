package com.kaleyra.springbootdemo;

import java.util.ArrayList;
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

		for (int i = 1; i <= 10; i++) {
			if(i % 2 == 0)
				evenArrayList.add(i);
		}

		return evenArrayList;

	}
}