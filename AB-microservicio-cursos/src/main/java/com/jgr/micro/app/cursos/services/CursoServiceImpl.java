package com.jgr.micro.app.cursos.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jgr.micro.app.cursos.client.AlumnoFeignClient;
import com.jgr.micro.app.cursos.client.RespuestaFeignClient;
import com.jgr.micro.app.cursos.models.data.Curso;
import com.jgr.micro.app.cursos.repository.ICursoRepository;
import com.jgr.micro.generic.services.GenericServiceImpl;
import com.jgr.modelo.microservicio.datos.alumno.entity.Alumno;

/**
 * The Class CursoServiceImpl.
 * Hereda de  GenericServiceImpl de zz-microservicio-generic-service
 * Como parametros tiene la entidad CURSO y el repositorio, en este caso del de curso
 * 
 */
@Service
public class CursoServiceImpl extends GenericServiceImpl<Curso, ICursoRepository> implements ICursoService {
	
	@Autowired
	private RespuestaFeignClient client;
	
	@Autowired
	private AlumnoFeignClient clientAlumno;
	

	@Override
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
		
	}

	@Override
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
		return client.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
	}

	@Override
	public Iterable<Alumno> obtenerAlumnosPorCurso(Iterable<Long> ids) {
		return clientAlumno.obtenerAlumnosPorCurso(ids);
	}

	@Override
	@Transactional
	public void eliminarCursoAlumnoPorId(Long id) {
		repository.eliminarCursoAlumnoPorId(id);
	}
	

}
