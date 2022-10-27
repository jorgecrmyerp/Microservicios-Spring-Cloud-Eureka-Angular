package com.jgr.micro.app.cursos.services;

import com.jgr.micro.app.cursos.models.data.Curso;
import com.jgr.micro.generic.services.IGenericService;
import com.jgr.modelo.microservicio.datos.alumno.entity.Alumno;

/**
 * The Interface ICursoService.
 * Hereda de IGenericService,de zz-microservicio-generic-service
 */
public interface ICursoService extends IGenericService<Curso>{

	
	public Curso findCursoByAlumnoId(Long id);
	
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
	
	public Iterable<Alumno> obtenerAlumnosPorCurso(Iterable<Long> ids);
	
	public void eliminarCursoAlumnoPorId(Long id);
	
}
