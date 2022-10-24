package com.jgr.micro.app.micro.config.controller;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

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


	//circuitbreaker,control de errores
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

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
	@GetMapping("/obtener-web")
	public ResponseEntity<?> obtenerWeb() {

		Map<String, String> json = new HashMap<>();

		json.put("Nombre Aplicacion", webServerAppCtxt.getApplicationName());
		json.put("Nombre Servidor", webServerAppCtxt.getServerNamespace());
		//Map<String, String> getenv = webServerAppCtxt.getApplicationListeners().t;


		// PARA QUE LO ORDENE
		TreeMap<String, String> ordenado = new TreeMap<>(json);

		return new ResponseEntity<Map<String, String>>(ordenado, HttpStatus.OK);
	}

	@GetMapping("/obtener-error")
	public ResponseEntity<?> obtenerError(){
		
		return this.circuitBreakerFactory.create("defecto")//inventado el nombre¿?
				.run(()->provocarErrorThrow(),e->metodoAlternativoObtenerError());

	}
	
	@GetMapping("/obtener-error-alternativo-anotado")
	@CircuitBreaker(name="defecto", fallbackMethod = "metodoAlternativoObtenerError")
	public ResponseEntity<?> obtenerErrorAlternativo(){
		
		String error = this.provocarErrorThrow().toString();
		return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body(error);
		
	}


	public ResponseEntity<?> provocarErrorThrow(){

		String salida=null;

		if (salida==null) {
			throw new IllegalStateException("Error provocado");
		}

		return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body(salida);

	}
	
	
	@CircuitBreaker(name="defecto", fallbackMethod = "metodoAlternativo2")
	@TimeLimiter(name="defecto")
	@GetMapping("/obtener-timeout")
	public CompletableFuture<String> obtenerErrorTimeout() {
		obtenerTimeout();
		
		String retorno=null;
		
		return CompletableFuture.supplyAsync(()-> retorno);
	}
	
	
	
	
	
	
	public CompletableFuture<String> obtenerTimeout(){

		try {
			TimeUnit.SECONDS.sleep(10L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String retorno = null;

		return CompletableFuture.supplyAsync(()->retorno);
	}



	public ResponseEntity<?> metodoAlternativoObtenerError(){

		Map<String, String> json = new HashMap<>();

		json.put("MetodoAlternativo", " Este es el mensaje de MetodoAlternativoObtenerError");
		return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body(json);


	}
	
	public CompletableFuture<?> metodoAlternativoObtenerTimeout(){

		Map<String, String> json = new HashMap<>();

		json.put("MetodoAlternativo", " Este es el mensaje de MetodoAlternativoObtenerError");
		return CompletableFuture.supplyAsync(()->json);


	}


}
