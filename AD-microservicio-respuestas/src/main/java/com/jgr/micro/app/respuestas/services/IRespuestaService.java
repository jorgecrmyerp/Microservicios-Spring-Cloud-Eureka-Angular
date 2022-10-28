package com.jgr.micro.app.respuestas.services;

import com.jgr.micro.app.respuestas.models.entity.Respuesta;

// TODO: Auto-generated Javadoc
/**
 * The Interface IRespuestaService.
 */
public interface IRespuestaService {

	/**
	 * Save all.
	 *
	 * @param respuestas the respuestas
	 * @return the iterable
	 */
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);

	/**
	 * Find respuesta by alumno by examen.
	 *
	 * @param alumnoId the alumno id
	 * @param examenId the examen id
	 * @return the iterable
	 */
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);

	/**
	 * Find examenes ids con respuestas by alumno.
	 *
	 * @param alumnoId the alumno id
	 * @return the iterable
	 */
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);
	
	
	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	public Iterable<Respuesta> findAll();
	
}