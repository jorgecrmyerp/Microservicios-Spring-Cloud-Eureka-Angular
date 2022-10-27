package com.jgr.micro.app.respuestas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.jgr.micro.app.respuestas.models.entity.Respuesta;
import com.jgr.micro.app.respuestas.services.IRespuestaService;

import brave.Tracer;

// TODO: Auto-generated Javadoc
/**
 * The Class RespuestaController.
 */
@RestController
public class RespuestaController {

	/** The service. */
	@Autowired
	private IRespuestaService service;
	
	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(RespuestaController.class);

	/** The tracer. */
	@Autowired
	private Tracer tracer;

	/** The circuit breaker factory. */
	// circuitbreaker,control de errores
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

	
	
	/**
	 * Crear.
	 *
	 * @param respuestas the respuestas
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas){
		respuestas = ((List <Respuesta>)respuestas).stream().map(r -> {
			r.setAlumnoId(r.getAlumno().getId());
			return r;
		}).collect(Collectors.toList());
		Iterable<Respuesta> respuestasDb = service.saveAll(respuestas);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuestasDb);
	}
	
	/**
	 * Obtener respuestas por alumno por examen.
	 *
	 * @param alumnoId the alumno id
	 * @param examenId the examen id
	 * @return the response entity
	 */
	@GetMapping("/alumno/{alumnoId}/examen/{examenId}")
	public ResponseEntity<?> obtenerRespuestasPorAlumnoPorExamen(@PathVariable Long alumnoId, @PathVariable Long examenId){
		Iterable<Respuesta> respuestas = service.findRespuestaByAlumnoByExamen(alumnoId, examenId);
		return ResponseEntity.ok(respuestas);
	}
	
	/**
	 * Obtener examenes ids con respuestas alumno.
	 *
	 * @param alumnoId the alumno id
	 * @return the response entity
	 */
	@GetMapping("/alumno/{alumnoId}/examenes-respondidos")
	public ResponseEntity<?> obtenerExamenesIdsConRespuestasAlumno(@PathVariable Long alumnoId){
		Iterable<Long> examenesIds = service.findExamenesIdsConRespuestasByAlumno(alumnoId);
		return ResponseEntity.ok(examenesIds);
	}

}
