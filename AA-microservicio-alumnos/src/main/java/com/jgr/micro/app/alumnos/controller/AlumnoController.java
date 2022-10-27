package com.jgr.micro.app.alumnos.controller;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jgr.micro.app.alumnos.service.IAlumnoService;
import com.jgr.micro.generic.controller.GenericController;
import com.jgr.modelo.microservicio.datos.alumno.entity.Alumno;

import brave.Tracer;

// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoController. hereda de CommonController del
 * microserviciogenerico como parametros le pasamos la entidad y el servicio
 */
@RestController
//si se modifica el environment o algo en git
@RefreshScope

//@RequestMapping("/api/alumnos")
public class AlumnoController extends GenericController<Alumno, IAlumnoService> {

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(AlumnoController.class);

	/** The tracer. */
	@Autowired
	private Tracer tracer;

	/** The circuit breaker factory. */
	// circuitbreaker,control de errores
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

	// @Autowired
	// no hace falta inyectar el servicio porque como hereda de GenericController
	// este ya tiene definida la variable service que conecta con la capa de
	// servicio
	// private IAlumnoService iAlumnoService;
	
	

	@GetMapping("/alumnos-por-curso")
	public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids){
		return ResponseEntity.ok(servicio.findAllById(ids));
	}

	/**
	 * Actualiza E.
	 *
	 * @param al the al
	 * @param id the id
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizaAlumno(@RequestBody Alumno al, @PathVariable Long id) {

		Optional<Alumno> o = servicio.findById(id);

		if (!o.isPresent()) {
			logger.debug("Microservicio Alumno->actualizaAlumno");
			tracer.currentSpan().tag("Microservicio Alumno->actualizaAlumno", "no existe alumno");
			return ResponseEntity.notFound().build();
		}
		Alumno alDb = o.get();

		alDb.setNombre(al.getNombre());
		alDb.setApellidos(al.getApellidos());
		alDb.setEmail(al.getEmail());

		return ResponseEntity.ok().body(servicio.save(alDb));

	}
	
	/**
	 * Editar foto.
	 *
	 * @param alumno the alumno
	 * @param result the result
	 * @param id the id
	 * @return the response entity
	 */
	@PutMapping("/validar/{id}")
	public ResponseEntity<?> editarFoto(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Alumno> o = servicio.findById(id);
		
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoDb = o.get();
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellidos(alumno.getApellidos());
		alumnoDb.setEmail(alumno.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(alumnoDb));
	}

	/**
	 * Busca nombre or apellido.
	 *
	 * @param term the term
	 * @return the response entity
	 */
	@GetMapping("/nombreoapellido/{term}")
	public ResponseEntity<?> buscaNombreOrApellido(@PathVariable String term) {

		return ResponseEntity.ok().body(servicio.buscaNombreOApellido(term));

	}

	/**
	 * Find by nombre or apellidos containing ignore case.
	 *
	 * @param nombre the nombre
	 * @param apellido the apellido
	 * @return the response entity
	 */
	@GetMapping("/nombreoapellidoignoramayusculas/{nombre}/{apellido}")
	public ResponseEntity<?> findByNombreOrApellidosContainingIgnoreCase(@PathVariable String nombre,
			@PathVariable String apellido) {

		List<Alumno> alumnos = (List<Alumno>) servicio
				.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(nombre, apellido);

		if (alumnos.size() > 0) {
			return ResponseEntity.ok().body(alumnos);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}

	}

	/**
	 * Ver foto.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id) {

		Optional<Alumno> o = servicio.findById(id);

		if (o.isEmpty() || o.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}

		Resource imagen = new ByteArrayResource(o.get().getFoto());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
	}

	/**
	 * Crear con foto.
	 *
	 * @param alumno the alumno
	 * @param result the result
	 * @param archivo the archivo
	 * @return the response entity
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult result, 
			@RequestParam MultipartFile archivo) throws IOException {
		if(!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		return super.creaEntidad(alumno, result);
	}
	
	
	
	/**
	 * Editar con foto.
	 *
	 * @param alumno the alumno
	 * @param result the result
	 * @param id the id
	 * @param archivo the archivo
	 * @return the response entity
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result, @PathVariable Long id,
			@RequestParam MultipartFile archivo) throws IOException{
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Alumno> o = servicio.findById(id);
		
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoDb = o.get();
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellidos(alumno.getApellidos());
		alumnoDb.setEmail(alumno.getEmail());
		
		if(!archivo.isEmpty()) {
			alumnoDb.setFoto(archivo.getBytes());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(alumnoDb));
	}
	
	

}
