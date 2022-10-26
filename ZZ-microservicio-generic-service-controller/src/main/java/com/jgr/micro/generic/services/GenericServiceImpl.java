package com.jgr.micro.generic.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AlumnoServiceImpl. Implementamos con genericos, se le pasa una
 * entidad E y al Crud le pasamos tb una entidad E, R es el nombre que le damos
 * al crudrepository
 * como la vamos a HEREDAR no hace falta la notacion @Service,no se inyecta
 */
//@Service
public class GenericServiceImpl<E, R extends CrudRepository<E, Long>> implements IGenericService<E> {

	/** The repository.
	 * Protected para que lo puedan usar las clases que heredan de el
	 *  */
	@Autowired
	protected R repository;

	/**
	 * devuelve lista de alumnos. readonly=true porque no actualiza
	 *
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly = true)
	public Iterable<E> findAll() {
		return repository.findAll();
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(Long id) {
		return repository.findById(id);
	}

	/**
	 * Save.
	 *
	 * @param al the al
	 * @return the E
	 */
	@Override
	public E save(E entity) {
		return repository.save(entity);
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteById(Long id) {

		repository.deleteById(id);

	}

}
