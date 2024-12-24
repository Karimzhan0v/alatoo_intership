package com.practice.practice_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories
@EnableScheduling
public class PracticeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeProjectApplication.class, args);
	}

}
