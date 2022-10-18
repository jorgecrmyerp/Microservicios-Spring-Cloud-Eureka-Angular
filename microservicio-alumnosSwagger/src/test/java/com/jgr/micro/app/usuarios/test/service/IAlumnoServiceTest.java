package com.jgr.micro.app.usuarios.test.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.jgr.micro.app.usuarios.entity.Alumno;
import com.jgr.micro.app.usuarios.repository.IAlumnoRepository;
import com.jgr.micro.app.usuarios.service.IAlumnoService;
import com.jgr.micro.app.usuarios.test.datos.CrearDatos;

import io.vavr.collection.Stream;

@SpringBootTest
class IAlumnoServiceTest {
	@MockBean
	IAlumnoRepository alumnoRepository;

	@Autowired
	IAlumnoService alumnoService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		when(alumnoService.findById(1L)).thenReturn(CrearDatos.creaAlumno1());
		when(alumnoRepository.findById(2L)).thenReturn(CrearDatos.creaAlumno2());
		when(alumnoRepository.findById(3L)).thenReturn(CrearDatos.creaAlumno3());
		when(alumnoRepository.findAll()).thenReturn(CrearDatos.listaAlumnos());

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindAll() {

		List<Alumno> lista = (List<Alumno>) alumnoRepository.findAll();

		int longi = lista.size();

		assertEquals(longi, 4);

	}

	@Test
	// @Disabled
	void testFindById() {
		assertNotEquals(alumnoService.findById(1L), CrearDatos.creaAlumno1(),
				() -> "No coindice el alumno");		
		verify(alumnoRepository, times(1)).findById(1L);
		Optional<Alumno> al = alumnoService.findById(8L);
		assertNotNull(al, () -> "Noes NLO");

	}

	@Test
	
	void testSave() {
		when(alumnoService.save(any(Alumno.class))).thenReturn(CrearDatos.creaAlumno3().get());
		Alumno al=alumnoService.save(CrearDatos.creaAlumno1().get());
		assertNotNull(al.getId(),()->"no es nulo");
		assertEquals(3L,al.getId(),()->"No coincide el id");
		
	}
	
	@Test	
	void testSaveIncremental() {
		Alumno al = CrearDatos.creaAlumno1().get();
		
		when(alumnoService.save(any(Alumno.class))).then(new Answer<Alumno>(){
			Long secuencia = 8L;
			@Override
			public Alumno answer(InvocationOnMock invocation) throws Throwable{
				Alumno al = invocation.getArgument(0);
				al.setId(secuencia++);
				return al;
			}
		});
		
		alumnoService.save(al);
		assertNotNull(al.getId());
		assertEquals(8L,al.getId());
		
		
	}

	@Test
	@Disabled
	void testDeleteById() {
		
		
	}

}
