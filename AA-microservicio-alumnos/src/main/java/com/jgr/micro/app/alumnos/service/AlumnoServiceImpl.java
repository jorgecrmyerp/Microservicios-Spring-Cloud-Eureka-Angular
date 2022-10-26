package com.jgr.micro.app.alumnos.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.micro.app.alumnos.repository.IAlumnoRepository;
import com.jgr.micro.generic.services.GenericServiceImpl;
import com.jgr.modelo.microservicio.datos.entity.Alumno;

// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoServiceImpl.
 * Hereda de la clase generica GENERICSERVICEIMPL,como parametros le pasamos la entidad
 * y el repositorio
 */
@Service
public class AlumnoServiceImpl extends GenericServiceImpl<Alumno, IAlumnoRepository> implements IAlumnoService{
	
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
	public Iterable<Alumno> findByNombreOrApellidosContainingIgnoreCase(String nombre,String apellido) {
		return repository.findByNombreOrApellidosContainingIgnoreCase(nombre,apellido);
	}
	
	

}
