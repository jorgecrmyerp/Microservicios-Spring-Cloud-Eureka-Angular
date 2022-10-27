package com.jgr.micro.app.alumnos.service;


import com.jgr.micro.generic.services.IGenericService;
import com.jgr.modelo.microservicio.datos.alumno.entity.Alumno;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAlumnoService.
 * HEREDA DE LA INTERFAZ GENERICA del microservicio generic. como parametro le paso la entidad,que en este
 * caso es alumno
 */
public interface IAlumnoService extends IGenericService<Alumno>{
	
	public Iterable<Alumno> buscaNombreOApellido(String term);
	 
	public Iterable<Alumno> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombre,String apellido);
	
public Iterable<Alumno> findAllById(Iterable<Long> ids);
	
public void eliminarCursoAlumnoPorId(Long id);
	
	

}
