package com.jgr.micro.app.usuarios.test.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jgr.micro.app.usuarios.entity.Alumno;
import com.jgr.micro.app.usuarios.repository.IAlumnoRepository;

/**
 * The Class IAlumnoRepositoryTest.
 */
@DataJpaTest
class IAlumnoRepositoryTest {

	/** The alumno repository. */
	@Autowired
	IAlumnoRepository alumnoRepository;

	/**
	 * Test find by id.
	 */
	@Test
	void testFindById() {
		Optional<Alumno> alumno = alumnoRepository.findById(1L);
		assertTrue(alumno.isPresent());
		assertEquals("ALUMNO1", alumno.get().getNombre());
	}

	/**
	 * Test find by id no such element exception. En caso de que no exista el
	 * elemento lanza excepcion, verificamos que lo hace
	 */
	@Test
	void testFindByIdNoSuchElementException() {
		Optional<Alumno> alumno = alumnoRepository.findById(99999L);

		assertThrows(NoSuchElementException.class, alumno::orElseThrow);
		assertFalse(alumno.isPresent());

	}
	
	@Test
	void testFindAll() {
		
		List<Alumno> alumnos =  (List<Alumno>) alumnoRepository.findAll();
		assertFalse(alumnos.isEmpty());
		assertEquals(alumnos.size(),5,()->"no coincide el numero de filas");


	}
	@Test
	void testSaveGiven() {
		
		Alumno al= new Alumno();
		al.setNombre("NOMBREPRUEBA");
		al.setApellidos("APELLIDOSPRUEBA");
		al.setEmail("MAIL@MAIL.COM");
		al.setCreateAt(new Date());
		Alumno salvado=alumnoRepository.save(al);
		
		alumnoRepository.findById(salvado.getId());
	
		assertAll(
				() -> {assertNotNull(salvado);},
				() -> {assertEquals(al.getNombre(),salvado.getNombre());});

		
		
	}
	
	
	

}
