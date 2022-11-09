package com.jgr.micro.app.alumnos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.micro.app.alumnos.client.CursoFeignClient;
import com.jgr.micro.app.alumnos.repository.IAlumnoRepository;
import com.jgr.micro.generic.services.GenericServiceImpl;
import com.jgr.modelo.microservicio.datos.alumno.entity.Alumno;

/**
 * The Class AlumnoServiceImpl.
 * Hereda de la clase generica GENERICSERVICEIMPL,como parametros le pasamos la entidad
 * y el repositorio
 */
@Service
public class AlumnoServiceImpl extends GenericServiceImpl<Alumno, IAlumnoRepository> implements IAlumnoService{

	@Autowired
	private CursoFeignClient clientCurso;
	
	/**
	 * Busca nombre or apellido.
	 *
	 * @param term the term
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly=true)
	public Iterable<Alumno> buscaNombreOApellido(String term) {
		
		return repository.buscaNombreOApellido(term);
	}

	/**
	 * Find by name or apellidos containing ignore case.
	 *
	 * @param term the term
	 * @return the iterable
	 */
	@Override
	public Iterable<Alumno> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombre,String apellido) {
		return repository.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(nombre,apellido);
	}


	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAllById(Iterable<Long> ids) {
		return repository.findAllById(ids);
	}
	
	@Override
	public void eliminarCursoAlumnoPorId(Long id) {
		clientCurso.eliminarCursoAlumnoPorId(id);
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		super.deleteById(id);
		this.eliminarCursoAlumnoPorId(id);
	}
	

	
	

}
