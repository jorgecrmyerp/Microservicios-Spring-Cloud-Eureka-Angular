package com.jgr.micro.app.respuestas.services;

import com.jgr.micro.app.respuestas.models.entity.Respuesta;

public interface IRespuestaService {

	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);

	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);

	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);
}