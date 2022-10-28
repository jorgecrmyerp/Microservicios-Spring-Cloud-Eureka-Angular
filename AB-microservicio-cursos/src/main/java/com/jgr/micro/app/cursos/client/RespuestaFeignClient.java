package com.jgr.micro.app.cursos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The Interface RespuestaFeignClient.
 * relaciona el microservicio respuestas para obtener los examenes que ha respondido el alumno
 */
@FeignClient(name="servicio-respuestas")
public interface RespuestaFeignClient {

	/**
	 * Obtener examenes ids con respuestas alumno.
	 *
	 * @param alumnoId the alumno id
	 * @return the iterable
	 */
	@GetMapping("/alumno/{alumnoId}/examenes-respondidos")
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(@PathVariable Long alumnoId);
}
