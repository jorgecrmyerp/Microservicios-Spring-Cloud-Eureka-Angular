package com.jgr.micro.app.cursos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jgr.modelo.microservicio.datos.alumno.entity.Alumno;

// TODO: Auto-generated Javadoc
/**
 * The Interface AlumnoFeignClient.
 * relacion con el microservicio alumnos para obtener los alumnos del curso
 */
@FeignClient(name = "servicio-alumnos")
public interface AlumnoFeignClient {

	/**
	 * Obtener alumnos por curso.
	 *
	 * @param ids the ids
	 * @return the iterable
	 */
	@GetMapping("/alumnos-por-curso")
	public Iterable<Alumno> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);
	
	
	@GetMapping("/datos-alumno/{id}")
	public Alumno obtenerDatosAlumno(@RequestParam Long id);
	
	
	
}
