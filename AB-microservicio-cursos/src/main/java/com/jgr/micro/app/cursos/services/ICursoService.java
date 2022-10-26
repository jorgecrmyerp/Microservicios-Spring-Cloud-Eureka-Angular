package com.jgr.micro.app.cursos.services;

import com.jgr.micro.app.cursos.models.data.Curso;
import com.jgr.micro.generic.services.IGenericService;

/**
 * The Interface ICursoService.
 * Hereda de IGenericService,de zz-microservicio-generic-service
 */
public interface ICursoService extends IGenericService<Curso>{

	
	public Curso findCursoByAlumnoId(Long id);
	
}
