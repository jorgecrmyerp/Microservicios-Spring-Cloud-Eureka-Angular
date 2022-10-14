package com.jgr.micro.app.usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jgr.micro.app.usuarios.entity.Alumno;
import com.jgr.micro.app.usuarios.repository.IAlumnoRepository;
import com.jgr.micro.app.usuarios.service.AlumnoServiceImpl;
import com.jgr.micro.app.usuarios.service.IAlumnoService;
import static org.mockito.Mockito.*;

@SpringBootTest
class MicroservicioUsuariosApplicationTests {

	@Test
	void contextLoads() {
		IAlumnoRepository iAlumnoRepository;
		IAlumnoService iAlumnoService = new AlumnoServiceImpl();
		
		Optional<Alumno> alumno1 = Optional.ofNullable(new Alumno());
		Optional<Alumno> alumno2= Optional.of(new Alumno());
		Optional<Alumno> alumno3= Optional.of(new Alumno());
		Alumno alumnob = new Alumno();
		List<Alumno> alumnos = new ArrayList<>();
		
		alumnob.setId(new Random().nextLong());
		alumnob.setNombre("Nombre Alumno1");
		alumnob.setApellidos("Apellidos 1");
		alumnob.setEmail("email@mail.com");
		alumnob.setCreateAt(new Date());
		
		alumno1=Optional.of(alumnob);
		
		for(int i=0;i<5;i++) {
			Alumno al = new Alumno();
			
			al.setId(Long.valueOf(i));
			al.setNombre("Alumno"+i);
			al.setApellidos("Apellidos"+i);
			al.setEmail("Email"+i);
			al.setCreateAt(new Date());
			
			
		}		
		when (iAlumnoService.findAll()).thenReturn(alumnos);
		when(iAlumnoService.findById(1L)).thenReturn(alumno1);
		when(iAlumnoService.findById(2L)).thenReturn(alumno2);
		when(iAlumnoService.findById(3L)).thenReturn(alumno3);
		
		
		
	}

}
