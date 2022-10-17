package com.jgr.micro.app.usuarios.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.jgr.micro.app.usuarios.entity.Alumno;
import com.jgr.micro.app.usuarios.service.AlumnoServiceImpl;
import com.jgr.micro.app.usuarios.service.IAlumnoService;
import com.jgr.micro.app.usuarios.test.CrearDatos;

@SpringBootTest
class IAlumnoRepositoryTest {
	
	@MockBean
	//@Mock
	IAlumnoRepository alumnoRepository;
	
	//@Autowired
	//@InjectMocks
	
	IAlumnoService alumnoService;

	@Test
	@Disabled
	void contextLoads() {
		when (alumnoService.findById(1L)).thenReturn(new CrearDatos().creaAlumno1());
		when (alumnoService.findById(2L)).thenReturn(new CrearDatos().creaAlumno2());
		when (alumnoService.findById(3L)).thenReturn(new CrearDatos().creaAlumno3());
		when (alumnoService.findAll()).thenReturn(new CrearDatos().listaAlumnos());
	
		
		
	}

}
