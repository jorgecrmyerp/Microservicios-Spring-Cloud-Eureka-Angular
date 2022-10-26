package com.jgr.micro.app.alumnos.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.micro.app.alumnos.service.IAlumnoService;
import com.jgr.micro.generic.controller.GenericController;
import com.jgr.modelo.microservicio.datos.entity.Alumno;

import brave.Tracer;


/**
 * The Class AlumnoController.
 * hereda de CommonController del microserviciogenerico
 * como parametros le pasamos la entidad y el servicio
 */
@RestController
//si se modifica el environment o algo en git
@RefreshScope


//@RequestMapping("/api/alumnos")
public class AlumnoController extends GenericController<Alumno, IAlumnoService>{

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(AlumnoController.class);
	
	@Autowired
	private Tracer tracer;
	
	//circuitbreaker,control de errores
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;
	
	//@Autowired
	//no hace falta inyectar el servicio porque como hereda de GenericController
	//este ya tiene definida la variable service que conecta con la capa de servicio
	//private IAlumnoService iAlumnoService;

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
	
	@GetMapping("/nombreoapellido/{term}")
	public ResponseEntity<?> buscaNombreOrApellido( @PathVariable String term) {
		
		return ResponseEntity.ok().body(servicio.buscaNombreOApellido(term));		
		
		
	}
	@GetMapping("/nombreoapellidoignoramayusculas/{nombre}/{apellido}")
	public ResponseEntity<?> findByNombreOrApellidosContainingIgnoreCase( @PathVariable String nombre,@PathVariable String apellido) {
		
		List <Alumno> alumnos=(List<Alumno>) servicio.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(nombre, apellido);
		
		if (alumnos.size()>0) {
			return 
					ResponseEntity.ok().body(alumnos);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		}
		
				
		
		
	}
	

}
