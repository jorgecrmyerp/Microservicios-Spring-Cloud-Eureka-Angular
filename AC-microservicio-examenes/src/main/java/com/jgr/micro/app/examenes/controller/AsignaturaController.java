package com.jgr.micro.app.examenes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.micro.app.examenes.entity.service.IAsignaturaService;
import com.jgr.micro.generic.controller.GenericController;
import com.jgr.modelo.microservicio.datos.examen.entity.Asignatura;

import brave.Tracer;


/**
 * The Class ExamenesController.
 */
@RestController
@RequestMapping("/asignaturas")
public class AsignaturaController extends GenericController<Asignatura, IAsignaturaService> {

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(AsignaturaController.class);

	/** The tracer. */
	@Autowired
	private Tracer tracer;

	/** The circuit breaker factory. */
	// circuitbreaker,control de errores
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;
	
	
	/*
	@GetMapping()	
	public ResponseEntity<?>listarAsignaturas(){
		
		return ResponseEntity.ok(servicio.findAllAsignaturas());
	}
	*/

	
	@GetMapping("/{nomasignatura}")	
	public ResponseEntity<?> buscarAsignaturasPorNombre(@PathVariable String nomasignatura){
		
		return ResponseEntity.ok(servicio.findAsignaturaByNombreContainingIgnoreCase(nomasignatura));
	}
	
	

}
