package com.jgr.micro.app.examenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// TODO: Auto-generated Javadoc
/**
 * The Class MicroservicioExamenesApplication.
 */
@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.jgr.micro.app.examenes.entity",
"com.jgr.modelo.microservicio.datos.examen.entity"}
)
public class MicroservicioExamenesApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MicroservicioExamenesApplication.class, args);
	}

}
