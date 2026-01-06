package com.pavswap.LearningSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class LearningSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringBootApplication.class, args);
	}

}

@RestController
class HelloWorldController {

    @GetMapping(path = "/paavan")
    public String returnHelloWorld() {
        return "Hello Paavan";
    }
}

