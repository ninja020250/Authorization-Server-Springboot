package com.eureka.flame.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringEurekaFlameApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaFlameApplication.class, args);
	}

}
