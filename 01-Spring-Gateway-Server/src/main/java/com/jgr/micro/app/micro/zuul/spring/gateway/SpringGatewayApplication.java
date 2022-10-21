package com.jgr.micro.app.micro.zuul.spring.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The Class SpringGatewayApplication.
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringGatewayApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringGatewayApplication.class, args);
	}

}
