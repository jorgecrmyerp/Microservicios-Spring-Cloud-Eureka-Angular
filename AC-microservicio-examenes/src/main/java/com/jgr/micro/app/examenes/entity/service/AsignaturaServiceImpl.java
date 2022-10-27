package com.jgr.micro.app.examenes.entity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.micro.app.examenes.entity.repository.IAsignaturaRepository;
import com.jgr.micro.generic.services.GenericServiceImpl;
import com.jgr.modelo.microservicio.datos.examen.entity.Asignatura;

@Service
public class AsignaturaServiceImpl extends GenericServiceImpl<Asignatura,IAsignaturaRepository> implements IAsignaturaService{
	/**
	 * Find all asignaturas.
	 *
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly=true)
	public Iterable<Asignatura> findAllAsignaturas(){
		return  repository.findAll();
	}

	/**
	 * Find by nombre ignore case.
	 *
	 * @param nombre the nombre
	 * @return the iterable
	 */
	@Override
	public Iterable<Asignatura> findAsignaturaByNombreContainingIgnoreCase(String nombre) {
		return  repository.findAsignaturaByNombreContainingIgnoreCase(nombre);
	}

}
