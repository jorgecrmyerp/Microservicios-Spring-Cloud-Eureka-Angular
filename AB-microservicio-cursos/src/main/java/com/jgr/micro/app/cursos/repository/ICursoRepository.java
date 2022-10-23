package com.jgr.micro.app.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jgr.micro.app.cursos.models.data.Curso;

/**
 * The Interface ICursoRepository.
 * https://openwebinars.net/blog/hola-mundo-con-spring-data-rest/#:~:text=Spring%20Data%20REST%20es%20un,sobre%20repositorios%20de%20Spring%20Data.
 */
@RepositoryRestResource(path="cursos" , collectionResourceRel="cursos")
public interface ICursoRepository extends JpaRepository<Curso, Long> {

}
