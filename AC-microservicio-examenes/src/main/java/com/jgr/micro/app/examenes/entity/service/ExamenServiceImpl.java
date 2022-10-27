package com.jgr.micro.app.examenes.entity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.micro.app.examenes.entity.repository.IAsignaturaRepository;
import com.jgr.micro.app.examenes.entity.repository.IExamenRepository;
import com.jgr.micro.generic.services.GenericServiceImpl;
import com.jgr.modelo.microservicio.datos.examen.entity.Asignatura;
import com.jgr.modelo.microservicio.datos.examen.entity.Examen;

// TODO: Auto-generated Javadoc
/**
 * The Class ExamenServiceImpl.
 */
@Service
public class ExamenServiceImpl extends GenericServiceImpl<Examen,IExamenRepository> implements IExamenService{

	
	/** The asignatura repository. */
	private IAsignaturaRepository asignaturaRepository;
	
	/**
	 * Find exam by nombre.
	 *
	 * @param nombre the nombre
	 * @return the list
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Examen> findExamByNombre(String nombre) {
		return repository.findExamByNombre(nombre);
	}
	
	/**
	 * Find all asignaturas.
	 *
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly=true)
	public Iterable<Asignatura> findAllAsignaturas(){
		return asignaturaRepository.findAll();
	}

	/**
	 * Find by nombre ignore case.
	 *
	 * @param nombre the nombre
	 * @return the iterable
	 */
	@Override
	public Iterable<Asignatura> findAsignaturaByNombreContainingIgnoreCase(String nombre) {
		return asignaturaRepository.findAsignaturaByNombreContainingIgnoreCase(nombre);
	}

}
