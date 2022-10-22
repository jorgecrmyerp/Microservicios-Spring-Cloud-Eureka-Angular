package com.jgr.micro.app.usuarios.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.micro.app.usuarios.entity.Alumno;
import com.jgr.micro.app.usuarios.error.ErrorBBDDException;
import com.jgr.micro.app.usuarios.error.IdNoEncontradoException;
import com.jgr.micro.app.usuarios.service.IAlumnoService;
import org.springframework.core.env.Environment;

import brave.Tracer;

/**
 * The Class AlumnoController.
 */
@RestController
//si se modifica el environment o algo en git
@RefreshScope
//@RequestMapping("/api/alumnos")
public class AlumnoController {

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(AlumnoController.class);

	/** The i alumno service. */
	/*
	 * @Autowired private Tracer traceZipkin;
	 */
	@Autowired
	private IAlumnoService iAlumnoService;

	/**
	 * Listar.
	 *
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(iAlumnoService.findAll());

	}

	/**
	 * Busca por id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaPorId(@PathVariable Long id) {

		logger.info("busco alumno->" + id.toString());
		Optional<Alumno> al = iAlumnoService.findById(id);

		// solo por usar try/catch

		try {
			if (al == null) {
				throw new IdNoEncontradoException(String.valueOf(id));

			}

		} catch (DataAccessException e) {
			throw new ErrorBBDDException(e.getClass().toString().concat(e.getMessage()));

		}

		if (!al.isPresent()) {
			/*
			 * traceZipkin.currentSpan().tag(
			 * "microservicioalumnos.AlumnoController.buscarPorId",
			 * "alumno".concat(" no existe->".concat(id.toString())));
			 */
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(al);
	}

	/**
	 * Crea alumno.
	 *
	 * @param al the al
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> creaAlumno(@RequestBody Alumno al) {

		Alumno alDb = iAlumnoService.save(al);

		return ResponseEntity.status(HttpStatus.CREATED).body(alDb);

	}

	/**
	 * Actualiza alumno.
	 *
	 * @param al the al
	 * @param id the id
	 * @return the response entity
	 */
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

	/**
	 * Borra alumno.
	 *
	 * @param id the id
	 * @return the response entity
	 */
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
