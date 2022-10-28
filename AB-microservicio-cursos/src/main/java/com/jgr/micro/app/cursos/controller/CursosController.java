package com.jgr.micro.app.cursos.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.micro.app.cursos.models.data.Curso;
import com.jgr.micro.app.cursos.models.data.CursoAlumno;
import com.jgr.micro.app.cursos.services.ICursoService;
import com.jgr.micro.generic.controller.GenericController;
import com.jgr.modelo.microservicio.datos.alumno.entity.Alumno;
import com.jgr.modelo.microservicio.datos.examen.entity.Examen;

import brave.Tracer;

// TODO: Auto-generated Javadoc
/**
 * The Class CursosController. Extiende del controlador generico
 * GenericController, como parametros le pasamos la clase curso y la capa de
 * servicio ICursoService,que a su vez hereda de IGenericService Como en
 * genericcontroller declaramos protected la capa de servicio llamada servicio
 */
@RestController
public class CursosController extends GenericController<Curso, ICursoService> {

	/** The circuit breaker factory. */
	// circuitbreaker,control de errores
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(CursosController.class);

	/** The tracer. */
	@Autowired
	private Tracer tracer;

	@Value("${config.balanceador.test}")
	private String balanceadorTest;
	
	@DeleteMapping("/eliminar-alumno/{id}")
	public ResponseEntity<?> eliminarCursoAlumnoPorId(@PathVariable Long id){
		servicio.eliminarCursoAlumnoPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	@Override
	public ResponseEntity<?> listar(){
		List<Curso> cursos = ((List<Curso>) servicio.findAll()).stream().map(c ->{
			c.getCursoAlumnos().forEach(ca ->{
				Alumno alumno  = new Alumno();
				alumno.setId(ca.getAlumnoId());
				c.addAlumno(alumno);
			});
			return c;
		}).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(cursos);
	}
	
	@GetMapping("/pagina")
	@Override
	public ResponseEntity<?> listarPaginable(Pageable pageable){
		Page<Curso> cursos = servicio.findAll(pageable).map(curso ->{
			curso.getCursoAlumnos().forEach(ca ->{
				Alumno alumno  = new Alumno();
				alumno.setId(ca.getAlumnoId());
				curso.addAlumno(alumno);
			});
			return curso;
		});
		
		return ResponseEntity.ok().body(cursos);
	}
	
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<?> buscaPorId(@PathVariable Long id){
		Optional<Curso> o = servicio.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Curso curso = o.get();
		
		if(curso.getCursoAlumnos().isEmpty() == false) {
			
			List<Long> ids = curso.getCursoAlumnos().stream().map(ca -> ca.getAlumnoId())
					.collect(Collectors.toList());
			
			List<Alumno> alumnos = (List<Alumno>) servicio.obtenerAlumnosPorCurso(ids);
			
			curso.setAlumnos(alumnos);
			
		}
		
		return ResponseEntity.ok().body(curso);
	}
	
	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("balanceador", balanceadorTest);
		response.put("cursos", servicio.findAll());
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
	    Optional<Curso> o = this.servicio.findById(id);
	    if(!o.isPresent()) {
	    	return ResponseEntity.notFound().build();
	    }
	    Curso dbCurso = o.get();
	    dbCurso.setNombre(curso.getNombre());
	    return ResponseEntity.status(HttpStatus.CREATED).body(this.servicio.save(dbCurso));
	}
	
	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id){
	    Optional<Curso> o = this.servicio.findById(id);
	    if(!o.isPresent()) {
	    	return ResponseEntity.notFound().build();
	    }
	    Curso dbCurso = o.get();
	    
	    alumnos.forEach(a -> {
	    	CursoAlumno cursoAlumno = new CursoAlumno();
	    	cursoAlumno.setAlumnoId(a.getId());
	    	cursoAlumno.setCurso(dbCurso);
	    	dbCurso.addCursoAlumno(cursoAlumno);
	    });
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(this.servicio.save(dbCurso));
	}
	
	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
	    Optional<Curso> o = this.servicio.findById(id);
	    if(!o.isPresent()) {
	    	return ResponseEntity.notFound().build();
	    }
	    Curso dbCurso = o.get();
	    CursoAlumno cursoAlumno = new CursoAlumno();
	    cursoAlumno.setAlumnoId(alumno.getId());
	    dbCurso.removeCursoAlumno(cursoAlumno);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(this.servicio.save(dbCurso));
	}
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id){
		Curso curso = servicio.findCursoByAlumnoId(id);
		
		if(curso != null) {
			
			List<Long> examenesIds = (List<Long>) servicio.obtenerExamenesIdsConRespuestasAlumno(id);
			
			List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
				if(examenesIds.contains(examen.getId())) {
					examen.setRespondido(true);
				}
				return examen;
			}).collect(Collectors.toList());
			
			curso.setExamenes(examenes);
		}
		return ResponseEntity.ok(curso);
	}
	
	@PutMapping("/{id}/asignar-examenes")
	public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id){
	    Optional<Curso> o = this.servicio.findById(id);
	    if(!o.isPresent()) {
	    	return ResponseEntity.notFound().build();
	    }
	    Curso dbCurso = o.get();
	    
	    examenes.forEach(e -> {
	    	dbCurso.addExamen(e);
	    });
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(this.servicio.save(dbCurso));
	}
	
	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){
	    Optional<Curso> o = this.servicio.findById(id);
	    if(!o.isPresent()) {
	    	return ResponseEntity.notFound().build();
	    }
	    Curso dbCurso = o.get();
	    
	    dbCurso.removeExamen(examen);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(this.servicio.save(dbCurso));
	}
}
