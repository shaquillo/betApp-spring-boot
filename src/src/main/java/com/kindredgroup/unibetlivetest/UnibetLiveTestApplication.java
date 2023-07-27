package com.kindredgroup.unibetlivetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UnibetLiveTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnibetLiveTestApplication.class, args);
	}

}
