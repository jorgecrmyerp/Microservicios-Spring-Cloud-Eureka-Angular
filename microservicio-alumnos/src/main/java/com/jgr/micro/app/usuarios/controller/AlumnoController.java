package com.jgr.micro.app.usuarios.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.micro.app.usuarios.entity.Alumno;
import com.jgr.micro.app.usuarios.service.IAlumnoService;

import brave.Tracer;

@RestController
public class AlumnoController {

	private final Logger logger = LoggerFactory.getLogger(AlumnoController.class);

	@Autowired
	private Tracer traceZipkin;

	@Autowired
	private IAlumnoService iAlumnoService;

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(iAlumnoService.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscaPorId(@PathVariable Long id) {

		logger.info("busco alumno->" + id.toString());
		Optional<Alumno> al = iAlumnoService.findById(id);

		if (!al.isPresent()) {
			traceZipkin.currentSpan().tag("microservicioalumnos.AlumnoController.buscarPorId",
					"alumno".concat(" no existe->".concat(id.toString())));
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(al);
	}

	@PostMapping
	public ResponseEntity<?> creaAlumno(@RequestBody Alumno al) {

		Alumno alDb = iAlumnoService.save(al);

		return ResponseEntity.status(HttpStatus.CREATED).body(alDb);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizaAlumno(@RequestBody Alumno al, @PathVariable Long id) {

		Optional<Alumno> o = iAlumnoService.findById(id);

		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alDb = o.get();

		alDb.setNombre(al.getNombre());
		alDb.setApellidos(al.getApellidos());
		alDb.setEmail(al.getEmail());

		return ResponseEntity.ok().body(iAlumnoService.save(alDb));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> borraAlumno(@PathVariable Long id) {

		Optional<Alumno> o = iAlumnoService.findById(id);

		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		iAlumnoService.deleteById(id);

		return ResponseEntity.noContent().build();

	}

}
