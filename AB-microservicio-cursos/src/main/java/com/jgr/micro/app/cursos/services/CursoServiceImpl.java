package com.jgr.micro.app.cursos.services;

import org.springframework.stereotype.Service;

import com.jgr.micro.app.cursos.models.data.Curso;
import com.jgr.micro.app.cursos.repository.ICursoRepository;
import com.jgr.micro.generic.services.GenericServiceImpl;

/**
 * The Class CursoServiceImpl.
 * Hereda de  GenericServiceImpl de zz-microservicio-generic-service
 * Como parametros tiene la entidad CURSO y el repositorio, en este caso del de curso
 * 
 */
@Service
public class CursoServiceImpl extends GenericServiceImpl<Curso, ICursoRepository> implements ICursoService {

	

}
