package com.jgr.micro.app.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

// TODO: Auto-generated Javadoc
/**
 * The Class MicroservicioUsuariosApplication.
 */
@SpringBootApplication
@EnableConfigServer
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
