package com.jgr.micro.generic.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAlumnoService. Usamos genericos para que sirvan para cualquier
 * entidad
 */
public interface IGenericService<E> {

	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	public Iterable<E> findAll();
	public Page<E> findAll(Pageable pageable);

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<E> findById(Long id);

	/**
	 * Save.
	 *
	 * @param al the al
	 * @return the E
	 */
	public E save(E entity);

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(Long id);

}
