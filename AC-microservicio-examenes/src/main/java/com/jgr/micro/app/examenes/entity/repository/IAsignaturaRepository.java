package com.jgr.micro.app.examenes.entity.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jgr.modelo.microservicio.datos.examen.entity.Asignatura;

/**
 * The Interface IAsignaturaRepository.
 */
public interface IAsignaturaRepository extends PagingAndSortingRepository<Asignatura,Long >{
	
	/**
	 * Find by nombre ignore case.
	 *
	 * @param nombre the nombre
	 * @return the iterable
	 */
	public Iterable<Asignatura> findAsignaturaByNombreContainingIgnoreCase(String nombre);

}
