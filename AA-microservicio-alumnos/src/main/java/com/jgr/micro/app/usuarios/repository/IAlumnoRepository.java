package com.jgr.micro.app.usuarios.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jgr.modelo.microservicio.datos.entity.Alumno;

/**
 * The Interface IAlumnosRepository.
 */
@Repository
public interface IAlumnoRepository extends CrudRepository<Alumno, Long>{

}
