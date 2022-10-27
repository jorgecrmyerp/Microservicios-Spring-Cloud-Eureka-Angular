package com.jgr.micro.app.examenes.entity.service;

import com.jgr.micro.generic.services.IGenericService;
import com.jgr.modelo.microservicio.datos.examen.entity.Asignatura;


public interface IAsignaturaService  extends IGenericService<Asignatura>{
	/**
	 * Find all asignaturas.
	 *
	 * @return the iterable
	 */
	public Iterable<Asignatura> findAllAsignaturas();
	
	
	public Iterable<Asignatura> findAsignaturaByNombreContainingIgnoreCase(String nombre);
	
	
	
	
	
}
