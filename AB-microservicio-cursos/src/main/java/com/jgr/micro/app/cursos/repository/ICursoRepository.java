package com.jgr.micro.app.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.jgr.micro.app.cursos.models.data.Curso;

/**
 * The Interface ICursoRepository.
 * 
 */

public interface ICursoRepository extends JpaRepository<Curso, Long> {
	

}
