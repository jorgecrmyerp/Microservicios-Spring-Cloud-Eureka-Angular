package com.jgr.micro.app.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroservicioCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioCursosApplication.class, args);
	}

}
