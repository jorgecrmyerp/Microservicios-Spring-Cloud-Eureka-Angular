package com.jgr.micro.app.examenes.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jgr.modelo.microservicio.datos.examen.entity.Examen;

/**
 * The Interface IExamenRepository.
 */
public interface IExamenRepository extends PagingAndSortingRepository<Examen,Long >{
	
	
	/**
	 * Busca examenes por nombre.
	 *
	 * @param nombre the nombre
	 * @return the iterable
	 */
	@Query("select exam from Examen exam where exam.nombre like %?1%")
	public List<Examen> findExamByNombre(String nombre);

}
