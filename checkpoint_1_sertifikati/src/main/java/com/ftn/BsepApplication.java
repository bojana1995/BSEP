package com.ftn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
/*
 * Potrebno je ukljuciti podrsku za izvrsavanje asinhronih zadataka.
 */
@EnableAsync
public class BsepApplication {

	public static void main(String[] args) {
		SpringApplication.run(BsepApplication.class, args);
	}
}
