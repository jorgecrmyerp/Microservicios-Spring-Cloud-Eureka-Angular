package com.jgr.micro.app.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jgr.micro.app.cursos.models.data.Curso;

/**
 * The Interface ICursoRepository.
 * 
 */

public interface ICursoRepository extends PagingAndSortingRepository<Curso, Long> {
	
	//con el join fetch se trae de una vez todos los alumnos 
	
	@Query ("select cur from Curso cur join fetch cur.alumnos al  where al.id=?1")	
	public Curso findCursoByAlumnoId(Long id);
	
	
	@Modifying
	@Query("delete from CursoAlumno ca where ca.alumnoId=?1")
	public void eliminarCursoAlumnoPorId(Long id);
	
	

}
