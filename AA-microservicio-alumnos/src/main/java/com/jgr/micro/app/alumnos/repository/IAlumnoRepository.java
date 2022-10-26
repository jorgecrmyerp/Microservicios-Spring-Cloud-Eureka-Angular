package com.jgr.micro.app.alumnos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jgr.modelo.microservicio.datos.entity.Alumno;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAlumnosRepository.
 */
@Repository
public interface IAlumnoRepository extends CrudRepository<Alumno, Long>{
	
	
	/**
	 * Busca nombre or apellido.
	 *
	 * @param term the term
	 * @return the iterable
	 */
	@Query("select a from Alumno a where a.nombre like %?1% or a.apellidos like %?1%")
	public Iterable<Alumno> buscaNombreOApellido(String term);
	
	/**
	 * Find by name or apellidos containing ignore case.
	 *
	 * @param term the term
	 * @return the iterable
	 */
	public Iterable<Alumno> findByNombreOrApellidosContainingIgnoreCase(String nombre,String apellido);

}
