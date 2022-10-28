package com.jgr.micro.app.examenes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.jgr.modelo.microservicio.datos.examen.entity.Examen;
import com.jgr.micro.app.examenes.entity.service.IExamenService;
import com.jgr.micro.generic.controller.GenericController;

import brave.Tracer;


/**
 * The Class ExamenesController.
 */
@RestController
public class ExamenesController extends GenericController<Examen, IExamenService> {

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(ExamenesController.class);

	/** The tracer. */
	@Autowired
	private Tracer tracer;

	/** The circuit breaker factory. */
	// circuitbreaker,control de errores
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

	/**
	 * Actualiza examen.
	 *
	 * @param ex the ex
	 * @param id the id
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizaExamen(@RequestBody Examen ex, @PathVariable Long id) {

		Optional<com.jgr.modelo.microservicio.datos.examen.entity.Examen> o = servicio.findById(id);

		if (!o.isPresent()) {
			logger.debug("Microservicio Examen->actualizaExamen");
			tracer.currentSpan().tag("Microservicio Examen->actualizaExamen", "no existe Examen");
			return ResponseEntity.notFound().build();
		}
		Examen exDb = o.get();

		exDb.setNombre(ex.getNombre());
		/*
		 * //si no esta en las que nos envian,las marco para eliminar List<Pregunta>
		 * eliminadas = new ArrayList<>();
		 * 
		 * exDb.getPreguntas().forEach( preg->{ if(!ex.getPreguntas().contains(preg)) {
		 * eliminadas.add(preg); }
		 * 
		 * });
		 * 
		 * eliminadas.forEach(preg->{ exDb.removePregunta(preg); });
		 */
		exDb.getPreguntas().stream().filter(preg -> !ex.getPreguntas().contains(preg)).forEach(exDb::removePregunta);

		exDb.setPreguntas(ex.getPreguntas());

		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(exDb));

	}
	
	@GetMapping("/buscarnomexamen/{nomexamen}")	
	public ResponseEntity<?> buscarExamenPorNombre(@PathVariable String nomexamen){
		
		return ResponseEntity.ok(servicio.findExamByNombre(nomexamen));
	}
	
	

}
