package com.jgr.micro.app.cursos.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.micro.app.cursos.models.data.Curso;
import com.jgr.micro.app.cursos.services.ICursoService;
import com.jgr.micro.generic.controller.GenericController;
import com.jgr.micro.generic.error.IdNoEncontradoException;
import com.jgr.modelo.microservicio.datos.entity.Alumno;

import brave.Tracer;

// TODO: Auto-generated Javadoc
/**
 * The Class CursosController.
 * Extiende del controlador generico GenericController, como parametros le pasamos la clase curso
 * y la capa de servicio ICursoService,que a su vez hereda de IGenericService 
 * Como en genericcontroller declaramos protected la capa de servicio llamada service
 */
@RestController
public class CursosController extends GenericController<Curso, ICursoService>{
	
	
	/** The circuit breaker factory. */
	//circuitbreaker,control de errores
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;
	
	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(CursosController.class);
	
	/** The tracer. */
	@Autowired
	private Tracer tracer;
	
	/**
	 * Actualiza datos de curso.
	 *
	 * @param curso the curso
	 * @param id the id
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> editarAlumno (@RequestBody Curso curso,@PathVariable Long id){

		Optional<Curso> op = this.servicio.findById(id);
		if (!op.isPresent()) {
			logger.debug("Microservicio Cursos->CursosController->editarAlumno()");
			tracer.currentSpan().tag("Microservicio Cursos->CursosController->editarAlumno()", "no existe curso "+ id.toString());
			throw ( 
					new IdNoEncontradoException("id curso no encontrado editarAlumno->"+id.toString())
					);			
			//return ResponseEntity.notFound().build();
		}

		Curso actualiza = op.get();

		actualiza.setNombre(curso.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.servicio.save(actualiza));


	}

	/**
	 * Asignar alumnos a un curso.
	 *
	 * @param alumnos the alumnos
	 * @param id the id
	 * @return the response entity
	 */
	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos (@RequestBody List<Alumno> alumnos, @PathVariable Long id){

		Optional<Curso> op = this.servicio.findById(id);
		if (!op.isPresent()) {
			tracer.currentSpan().tag("Microservicio Cursos->CursosController->asignarAlumnos()", "no existe curso "+id.toString());
			throw ( new IdNoEncontradoException("id no encontrado asignarAlumnos->"+id.toString()));			
			//return ResponseEntity.notFound().build();
		}

		Curso actualiza = op.get();

		alumnos.forEach(al->{
			actualiza.addAlumno(al);
		});

		return ResponseEntity.status(HttpStatus.CREATED).body(this.servicio.save(actualiza));
	}

	/**
	 * Asignar un alumno a un curso.
	 *
	 * @param alumno the alumno
	 * @param id the id
	 * @return the response entity
	 */
	@PutMapping("/{id}/asignar-alumno")
	public ResponseEntity<?> asignarAlumno (@RequestBody Alumno alumno, @PathVariable Long id){

		Optional<Curso> op = this.servicio.findById(id);
		if (!op.isPresent()) {
			tracer.currentSpan().tag("Microservicio Cursos->CursosController->asignarAlumno()", "no existe curso "+ id.toString());
			throw ( new IdNoEncontradoException("id no encontrado asignarAlumnos->"+id.toString()));			
			//return ResponseEntity.notFound().build();
		}

		Curso actualiza = op.get();
		actualiza.addAlumno(alumno);	


		return ResponseEntity.status(HttpStatus.CREATED).body(this.servicio.save(actualiza));
	}

	/**
	 * Eliminar alumnos de un curso.
	 *
	 * @param alumnos the alumnos
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}/eliminar-alumnos")
	public ResponseEntity<?> eliminarAlumnos (@RequestBody List<Alumno> alumnos, @PathVariable Long id){

		Optional<Curso> op = this.servicio.findById(id);
		if (!op.isPresent()) {
			tracer.currentSpan().tag("Microservicio Cursos->CursosController->eliminarAlumnos()", "no existe curso "+ id.toString());
			throw ( new IdNoEncontradoException("id no encontrado asignarAlumnos->"+id.toString()));			
			//return ResponseEntity.notFound().build();
		}

		Curso actualiza = op.get();

		alumnos.forEach(al->{
			actualiza.removeAlumno(al);
		});

		return ResponseEntity.status(HttpStatus.CREATED).body(this.servicio.save(actualiza));
	}

	/**
	 * Eliminar alumno de un curso.
	 *
	 * @param alumno the alumno
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?> eliminarAlumno (@RequestBody Alumno alumno, @PathVariable Long id){

		Optional<Curso> op = this.servicio.findById(id);
		
		if (!op.isPresent()) {
			tracer.currentSpan().tag("Microservicio Cursos->CursosController->eliminarAlumno()", "no existe curso "+ id.toString());
			throw ( new IdNoEncontradoException("id no encontrado asignarAlumnos->"+id.toString()));			
			//return ResponseEntity.notFound().build();
		}

		Curso actualiza = op.get();		

		actualiza.removeAlumno(alumno);


		return ResponseEntity.status(HttpStatus.CREATED).body(this.servicio.save(actualiza));
	}

	
	/**
	 * Busca curso alumno id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/buscacursoalumnoid/{id}")
	public ResponseEntity<?> buscaCursoAlumnoId (@PathVariable Long id){

		Curso cur= servicio.findCursoByAlumnoId(id);
		
		if(cur!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(cur);
			
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	

}
