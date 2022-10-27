package com.jgr.micro.app.examenes.entity.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.jgr.micro.generic.services.IGenericService;
import com.jgr.modelo.microservicio.datos.examen.entity.Asignatura;
import com.jgr.modelo.microservicio.datos.examen.entity.Examen;

// TODO: Auto-generated Javadoc
/**
 * The Interface IExamenService.
 */
public interface IExamenService extends IGenericService<Examen>{
	
	/**
	 * Find exam by nombre.
	 *
	 * @param nombre the nombre
	 * @return the list
	 */
	public List<Examen> findExamByNombre(String nombre);

	/**
	 * Find all asignaturas.
	 *
	 * @return the iterable
	 */
	public Iterable<Asignatura> findAllAsignaturas();
	
	
	public Iterable<Asignatura> findAsignaturaByNombreContainingIgnoreCase(String nombre);

}
