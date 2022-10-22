package com.jgr.micro.app.usuarios.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.micro.app.usuarios.entity.Alumno;
import com.jgr.micro.app.usuarios.repository.IAlumnoRepository;
import com.jgr.micro.generic.services.GenericServiceImpl;

/**
 * The Class AlumnoServiceImpl.
 * Hereda de la clase generica GENERICSERVICEIMPL,como parametros le pasamos la entidad
 * y el repositorio
 */
@Service
public class AlumnoServiceImpl extends GenericServiceImpl<Alumno, IAlumnoRepository> implements IAlumnoService{
	
	/** The i alumno repository. */
	@Autowired
	private IAlumnoRepository iAlumnoRepository;

	/**
	 * devuelve lista de alumnos.
	 * readonly=true porque no actualiza
	 *
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly=true)
	public Iterable<Alumno> findAll() {
		return iAlumnoRepository.findAll();
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@Override
	@Transactional(readOnly=true)
	public Optional<Alumno> findById(Long id) {
		return iAlumnoRepository.findById(id);
	}

	/**
	 * Save.
	 *
	 * @param al the al
	 * @return the alumno
	 */
	@Override
	public Alumno save(Alumno al) {
		return iAlumnoRepository.save(al);
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteById(Long id) {
		
		iAlumnoRepository.deleteById(id);
		
	}

}
