package com.jgr.micro.generic.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.micro.generic.error.ErrorBBDDException;
import com.jgr.micro.generic.error.IdNoEncontradoException;
import com.jgr.micro.generic.services.IGenericService;

// TODO: Auto-generated Javadoc
/**
 * The ClassGenericController. Generico para poder usarlo heredando en el
 * destino a esta clase le pasaremos la Entidad E y el Serivicio S a su vez el
 * servicio S como parametros necesita una Entidad, le pasamos E
 *
 * @param <E> the element type
 * @param <S> the generic type
 */
@RestController
public class GenericController<E, S extends IGenericService<E>> {

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(GenericController.class);

	/** El servicio S es el que le entra como parametro lo pongo como protected para que pueda usarse en el que hereda de el. */

	@Autowired
	protected S servicio;

	/**
	 * Listar.
	 *
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(servicio.findAll());

	}

	/**
	 * Listar.
	 *
	 * @param pageable the pageable
	 * @return the response entity
	 */

	@GetMapping("/pagina")
	public ResponseEntity<?> listarPaginable(Pageable pageable) {
		return ResponseEntity.ok().body(servicio.findAll(pageable));

	}

	/**
	 * Busca por id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaPorId(@PathVariable Long id) {

		logger.info("busco E->" + id.toString());
		Optional<E> al = servicio.findById(id);

		// solo por usar try/catch

		try {
			if (al == null) {
				throw new IdNoEncontradoException(String.valueOf(id));

			}

		} catch (DataAccessException e) {
			throw new ErrorBBDDException(e.getClass().toString().concat(e.getMessage()));

		}

		if (!al.isPresent()) {
			/*
			 * traceZipkin.currentSpan().tag(
			 * "microservicioalumnos.AlumnoController.buscarPorId",
			 * "E".concat(" no existe->".concat(id.toString())));
			 */
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(al);
	}

	/**
	 * Crea E.
	 *
	 * @param entity the entity
	 * @return the response entity
	 */

	@PostMapping
	public ResponseEntity<?> creaEntidad(@RequestBody E entity) {

		E alDb = servicio.save(entity);

		return ResponseEntity.status(HttpStatus.CREATED).body(alDb);

	}

	/**
	 * Crea entidad.
	 *
	 * @param entity the entity
	 * @param result the result
	 * @return the response entity
	 */
	@PostMapping("/entidad")

	public ResponseEntity<?> creaEntidad(@Valid @RequestBody E entity, BindingResult result) {

		if (result.hasErrors()) {
			return this.validar(result);
		}
		E entityDb = servicio.save(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
	}

	/**
	 * Validar.
	 *
	 * @param result the result
	 * @return the response entity
	 */
	protected ResponseEntity<?> validar(BindingResult result) {
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}

	/**
	 * Borra E.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borraEntidad(@PathVariable Long id) {

		Optional<E> o = servicio.findById(id);

		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		servicio.deleteById(id);

		return ResponseEntity.noContent().build();

	}

}
