package com.jgr.micro.app.usuarios.test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.jgr.micro.app.usuarios.entity.Alumno;

public class CrearDatos {
	
	
	

	public static Optional<Alumno> creaAlumno1() {

		Alumno al1 = new Alumno();
		al1.setId(new Random().nextLong());
		al1.setNombre("Nombre Alumno1");
		al1.setApellidos("Apellidos 1");
		al1.setEmail("email@mail.com");
		al1.setCreateAt(new Date());

		return Optional.ofNullable(al1);
	}

	public static Optional<Alumno> creaAlumno2() {

		Alumno al1 = new Alumno();
		al1.setId(new Random().nextLong());
		al1.setNombre("Nombre Alumno2");
		al1.setApellidos("Apellidos 2");
		al1.setEmail("email@mail.com");
		al1.setCreateAt(new Date());

		return Optional.ofNullable(al1);
	}

	public static Optional<Alumno> creaAlumno3() {

		Alumno al1 = new Alumno();
		al1.setId(new Random().nextLong());
		al1.setNombre("Nombre Alumno3");
		al1.setApellidos("Apellidos 3");
		al1.setEmail("email@mail.com");
		al1.setCreateAt(new Date());

		return Optional.ofNullable(al1);
	}

	public static Iterable<Alumno> listaAlumnos() {

		List<Alumno> alumnos = new ArrayList<>();

		for (int i = 1; i < 5; i++) {
			Alumno al = new Alumno();

			al.setId(Long.valueOf(i));
			al.setNombre("Alumno" + i);
			al.setApellidos("Apellido" + i);
			al.setEmail("email" + i + "@loquesea.com");
			al.setCreateAt(new Date());
			alumnos.add(al);
		}
		return alumnos;

	}

}
