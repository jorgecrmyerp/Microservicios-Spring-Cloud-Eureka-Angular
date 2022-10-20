package com.jgr.micro.app.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * The Class MicroservicioUsuariosApplication.
 * no es necesario el enableeurekaclient si lo incluimos como starter, es para hacerlo de manera explicita
 */


@SpringBootApplication
@EnableEurekaClient
public class MicroservicioUsuariosApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MicroservicioUsuariosApplication.class, args);
	}
	

}
