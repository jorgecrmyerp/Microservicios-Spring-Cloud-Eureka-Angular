package com.jgr.micro.app.usuarios.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jgr.micro.app.usuarios.repository.IAlumnoRepository;

@SpringBootTest
class AlumnoServiceImplTest {
	
	IAlumnoRepository iAlumnoRepository;
	AlumnoServiceImpl alumnoServiceImpl;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		iAlumnoRepository= mock(IAlumnoRepository.class);
		alumnoServiceImpl= mock(AlumnoServiceImpl.class);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented"); // TODO
	}

}
