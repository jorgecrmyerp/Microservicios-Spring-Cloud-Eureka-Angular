package com.jgr.micro.app.micro.config.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * The Class ConfigController.
 */
@RestController
//si se modifica el environment o algo en git
@RefreshScope
//@RequestMapping("/api/controller")
public class ConfigController {

	private final Logger logger = LoggerFactory.getLogger(ConfigController.class);

	/** The entorno. */
	// para obtener el puerto
	@Autowired
	private ServletWebServerApplicationContext webServerAppCtxt;

	/** The entorno. */
	// para obtener el entorno de ejecucion
	@Autowired
	private Environment entorno;

	/** The instance id. */
	@Value("${eureka.instance.instance-id}")
	private String instanceId;

	@GetMapping("/obtener-configuracion")
	public ResponseEntity<?> obtenerConfig() {

		Map<String, String> json = new HashMap<>();

		if (entorno.getActiveProfiles().length > 0			
				) {

			json.put("**PUERTO**", String.valueOf(webServerAppCtxt.getWebServer().getPort()));
			json.put("**INSTANCIA**", instanceId);
			json.put("**ENTORNO**", entorno.getActiveProfiles()[0]);
			json.put("**DEFAULT**", entorno.getDefaultProfiles()[0]);
		}

		
		// PARA QUE LO ORDENE
		TreeMap<String, String> ordenado = new TreeMap<>(json);

		return new ResponseEntity<Map<String, String>>(ordenado, HttpStatus.OK);
	}

	@GetMapping("/obtener-properties")
	public ResponseEntity<?> obtenerProperties() {
		
		Map<String, String> json = new HashMap<>();
		Properties properties = System.getProperties();
		properties.forEach((k, v) -> json.put("Properties->" + k.toString(), v.toString()));
		TreeMap<String, String> ordenado = new TreeMap<>(json);

		return new ResponseEntity<Map<String, String>>(ordenado, HttpStatus.OK);
	}

	@GetMapping("/obtener-ambiente")
	public ResponseEntity<?> obtenerAmbiente() {

		Map<String, String> json = new HashMap<>();

		Map<String, String> getenv = System.getenv();
		getenv.forEach((k, v) -> json.put("Variables Ambiente->" + k.toString(), v.toString())

				);
		// PARA QUE LO ORDENE
		TreeMap<String, String> ordenado = new TreeMap<>(json);

		return new ResponseEntity<Map<String, String>>(ordenado, HttpStatus.OK);
	}

}
