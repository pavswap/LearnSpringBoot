package com.pavswap.LearningSpringBoot;

import com.pavswap.LearningSpringBoot.colours.HelloRunner;
import com.pavswap.LearningSpringBoot.colours.implementation.HelloRunnerImpli;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class HelloApplication implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		HelloRunner h =  new HelloRunnerImpli();
		log.info(h.sayHello());
  }
}
