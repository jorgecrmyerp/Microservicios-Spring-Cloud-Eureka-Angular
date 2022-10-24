package com.jgr.micro.app.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// 
/**
 * The Class MicroservicioCursosApplication.
 * Prueba con Rest Repositories & Rest Repositories HAL Browser
 * como importa la clase alumno incluimos el package donde esta,
 * tambien hay que añadir donde esta el modelo de cursos para que no falle
 */
@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.jgr.modelo.microservicio.datos.entity",
"com.jgr.micro.app.cursos.models.data"}
)
public class MicroservicioCursosApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MicroservicioCursosApplication.class, args);
	}

}
