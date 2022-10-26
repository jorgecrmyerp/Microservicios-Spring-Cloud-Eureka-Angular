package com.jgr.micro.app.examenes.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.micro.app.alumnos.controller.AlumnoController;
import com.jgr.micro.app.examenes.entity.Examen;
import com.jgr.micro.app.examenes.entity.service.IExamenService;
import com.jgr.micro.generic.controller.GenericController;

import brave.Tracer;


@RestController
public class ExamenesController extends GenericController<Examen,IExamenService>{
	
	
	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(ExamenesController.class);
	
	@Autowired
	private Tracer tracer;
	
	//circuitbreaker,control de errores
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;
	

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizaExamen(@RequestBody Examen ex, @PathVariable Long id) {

		Optional<Examen> o = servicio.findById(id);

		if (!o.isPresent()) {
			logger.debug("Microservicio Examen->actualizaExamen");
			tracer.currentSpan().tag("Microservicio Examen->actualizaExamen", "no existe Examen");
			return ResponseEntity.notFound().build();
		}
		Examen exDb = o.get();

		exDb.setNombre(ex.getNombre());
		exDb.setPreguntas(ex.getPreguntas());
		

		return ResponseEntity.ok().body(servicio.save(exDb));

	}
	
	
}
