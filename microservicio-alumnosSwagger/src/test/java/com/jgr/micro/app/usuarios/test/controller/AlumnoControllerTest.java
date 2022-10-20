package com.jgr.micro.app.usuarios.test.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jgr.micro.app.usuarios.controller.AlumnoController;
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
		.andExpect(jsonPath("$[0].nombre").value("Alumno1"))
		.andExpect(jsonPath("$[0].id").value(1L))
		.andExpect(jsonPath("$[1].apellidos").value("Apellido2"))
		.andExpect(jsonPath("$", hasSize(4)))
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
	void testCreaAlumno() throws JsonProcessingException, Exception {

		Alumno al = CrearDatos.creaAlumno1().get();
		

		//asigno el id a mano para que no falle la validacion porque al no ir a bbdd
		//lo va devolver como nulo
		when(alumnoService.save(any())).then(invocation->{
			Alumno alu = invocation.getArgument(0);
			alu.setId((long) 3);
			return alu;
		}
				);

		//when
		mockMvc.perform(post("/api/alumno").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(al)))
		
		//then
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id",is(3))) // de import static org.hamcrest.Matchers.*;
		.andExpect(jsonPath("$.nombre",is("Nombre Alumno1")))
		.andExpect(jsonPath("$.apellidos",is("Apellidos 1")))
		.andExpect(jsonPath("$.email",is("email@mail.com")));
		verify(alumnoService).save(any());
		

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
