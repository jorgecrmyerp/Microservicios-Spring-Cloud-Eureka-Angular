package com.jgr.micro.app.cursos.repository;

import org.springframework.data.repository.CrudRepository;

import com.jgr.micro.app.cursos.models.data.Curso;

public interface ICursoRepository extends CrudRepository<Curso, Long> {

}
