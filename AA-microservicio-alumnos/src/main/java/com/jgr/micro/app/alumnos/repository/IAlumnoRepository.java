package com.jgr.micro.app.alumnos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jgr.modelo.microservicio.datos.alumno.entity.Alumno;

/**
 * The Interface IAlumnosRepository.
 */
@Repository
public interface IAlumnoRepository extends PagingAndSortingRepository<Alumno, Long>{
	
	
	/**
	 * Busca en nombre o en apellido.
	 *
	 * @param term the term
	 * @return the iterable
	 */
	@Query("select a from Alumno a where a.nombre like %?1% or a.apellidos like %?1%")
	public Iterable<Alumno> buscaNombreOApellido(String term);
	
	/**
	 * Find by name or apellidos ignorando mayusculas/minusculas.
	 *
	 * @param nombre the nombre
	 * @param apellido the apellido
	 * @return the iterable
	 */
	public Iterable<Alumno> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombre,String apellido);

	
	
	
}
