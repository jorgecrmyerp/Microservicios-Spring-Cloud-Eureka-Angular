package com.jgr.micro.app.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jgr.micro.app.cursos.models.data.Curso;

/**
 * The Interface ICursoRepository.
 * https://openwebinars.net/blog/hola-mundo-con-spring-data-rest/#:~:text=Spring%20Data%20REST%20es%20un,sobre%20repositorios%20de%20Spring%20Data.
 * CON EL POR DEFECTO
 * http://192.168.31.164:63636/cursos/1->SUPONIENDO QUE EL MICROSERVICIO ESTUVIERA EN EL PUERTO 63636 SACARIA EL DETALLE DEL CURSO 1
 * 
 */
@RepositoryRestResource(path="cursos" , collectionResourceRel="cursos")
public interface ICursoRepository extends JpaRepository<Curso, Long> {
	

}
