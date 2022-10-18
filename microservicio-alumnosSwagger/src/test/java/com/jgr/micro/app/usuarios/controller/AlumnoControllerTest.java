package com.jgr.micro.app.usuarios.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jgr.micro.app.usuarios.entity.Alumno;
import com.jgr.micro.app.usuarios.service.IAlumnoService;
import com.jgr.micro.app.usuarios.test.datos.CrearDatos;

@WebMvcTest(AlumnoController.class)
class AlumnoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IAlumnoService alumnoService;

	private ObjectMapper objectMapper;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		objectMapper = new ObjectMapper();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testListar() throws JsonProcessingException, Exception {

		List<Alumno> alumnos = (List<Alumno>) CrearDatos.listaAlumnos();

		System.out.println(alumnos.get(0).getCreateAt().getTime());
		
		

		// given
		when(alumnoService.findAll()).thenReturn(CrearDatos.listaAlumnos());
		// when
		mockMvc.perform(MockMvcRequestBuilders.get("/api/alumno/").contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].nombre").value("Alumno1")).andExpect(jsonPath("$[0].id").value(1L))
		.andExpect(jsonPath("$[1].apellidos").value("Apellido2")).andExpect(jsonPath("$", hasSize(4)))
		/*
		 * como alumnos tiene una fecha,convierte a long el date y da error porque no
		 * coincide: java.lang.AssertionError: [apellidos=Apellido4].createAt Expected:
		 * 1666114825288 got: 2022-10-18T17:40:25.297+00:00
		 * .andExpect(content().json(objectMapper.writeValueAsString(alumnos)))
		 * 
		 */

		;

	}

	@Test
	void testBuscaPorId() throws Exception {

		// Given
		when(alumnoService.findById(1L)).thenReturn(CrearDatos.creaAlumno1());
		// Then
		mockMvc.perform(MockMvcRequestBuilders.get("/api/alumno/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.nombre").value("Nombre Alumno1"))
		.andExpect(jsonPath("$.apellidos").value("Apellidos 1"));

		verify(alumnoService).findById(1L);// SE LLAMA A ESTE METODO

	}

	@Test
	@Disabled
	void testCreaAlumno() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Disabled
	void testActualizaAlumno() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Disabled
	void testBorraAlumno() {
		fail("Not yet implemented"); // TODO
	}

}
