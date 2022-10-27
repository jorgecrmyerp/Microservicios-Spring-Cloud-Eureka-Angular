package com.jgr.micro.app.alumnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * The Class MicroservicioUsuariosApplication.
 * no es necesario el enableeurekaclient si lo incluimos como starter, es para hacerlo de manera explicita
 * añadimos el nombre del package para que pueda usarlo
 */


@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.jgr.modelo.microservicio.datos.alumno.entity",
	"com.jgr.modelo.microservicio.datos.examen.entity"})
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
