/**
 * 
 */
package com.jgr.micro.app.usuarios.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author JORGE
 *
 */
class AlumnoTest {

	Alumno al1;
	Alumno al2;
	Alumno al3;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	@DisplayName("en setUpBeforeClass()")
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	@Disabled
	static void tearDownAfterClass() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		al1 = new Alumno();
		al2 = new Alumno();
		al3 = new Alumno();

		al1.setId(new Random().nextLong());
		al1.setNombre("Nombre Alumno1");
		al1.setApellidos("Apellidos 1");
		al1.setEmail("email@mail.com");
		al1.setCreateAt(new Date());

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.jgr.micro.app.usuarios.entity.Alumno#hashCode()}.
	 */
	@Test
	@Disabled
	void testHashCode() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.jgr.micro.app.usuarios.entity.Alumno#prePersist()}.
	 */
	@Test
	@Disabled
	void testPrePersist() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.jgr.micro.app.usuarios.entity.Alumno#Alumno()}.
	 */
	@Test
	@Disabled
	void testAlumno() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.jgr.micro.app.usuarios.entity.Alumno#Alumno(Long, String, String, String, Date)}.
	 */
	@Test
	@Disabled
	void testAlumnoLongStringStringStringDate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.jgr.micro.app.usuarios.entity.Alumno#getId()}.
	 */
	@Test
	@Disabled
	@DisplayName("en testGetId")
	void testGetId() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.jgr.micro.app.usuarios.entity.Alumno#getNombre()}.
	 */
	@Test
	void testGetNombre() {

		String esperado = "Nombre Alumno1";
		String real = al1.getNombre();

		assertAll(() -> {
			Assertions.assertEquals(esperado, real,
					() -> "no coinciden los valores".concat(esperado + "/").concat(real));
		}, () -> {
			assertTrue(esperado.equalsIgnoreCase(real));
		}

		);

	}

	/**
	 * Test method for
	 * {@link com.jgr.micro.app.usuarios.entity.Alumno#getApellidos()}.
	 */
	@Test
	void testGetApellidos() {
		String esperado = "Apellidos 1";
		String real = al1.getApellidos();
		assertTrue(real.equals(esperado));
	}

	/**
	 * Test method for {@link com.jgr.micro.app.usuarios.entity.Alumno#getEmail()}.
	 */
	@Test
	void testGetEmail() {

		assertNotNull(al1.getEmail());
	}

	/**
	 * Test method for
	 * {@link com.jgr.micro.app.usuarios.entity.Alumno#getCreateAt()}.
	 */
	@Test
	void testGetCreateAt() {
		assertNotNull(al1.getCreateAt());

	}

	/**
	 * Test method for {@link com.jgr.micro.app.usuarios.entity.Alumno#setId(Long)}.
	 */
	@Test
	void testSetId() {

		al2.setId(al1.getId());
		assertTrue(al2.getId().equals(al1.getId()));

	}

	/**
	 * Test method for
	 * {@link com.jgr.micro.app.usuarios.entity.Alumno#setNombre(String)}.
	 */
	@Test
	@Disabled
	void testSetNombre() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.jgr.micro.app.usuarios.entity.Alumno#setApellidos(String)}.
	 */
	@Test
	@Disabled
	void testSetApellidos() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.jgr.micro.app.usuarios.entity.Alumno#setEmail(String)}.
	 */
	@Test
	@Disabled
	void testSetEmail() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.jgr.micro.app.usuarios.entity.Alumno#setCreateAt(Date)}.
	 */
	@Test
	@Disabled
	void testSetCreateAt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.jgr.micro.app.usuarios.entity.Alumno#toString()}.
	 */
	@Test
	@Disabled
	void testToString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.jgr.micro.app.usuarios.entity.Alumno#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject() {
		al2 = al1;

		assertTrue(al1.equals(al2),
				() -> "no coinciden los valores".concat(al1.toString() + "/").concat(al2.toString()));

	}

	/**
	 * Test method for
	 * {@link com.jgr.micro.app.usuarios.entity.Alumno#canEqual(java.lang.Object)}.
	 */
	@Test
	@Disabled
	void testCanEqual() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link java.lang.Object#Object()}.
	 */
	@Test
	@Disabled
	void testObject() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link java.lang.Object#getClass()}.
	 */
	@Test
	void testGetClass() {

		System.out.println(al1.getClass());
		System.out.println(al1.getClass().getCanonicalName());

		assertAll(() -> {
			assertTrue(al1.getClass().toString().equalsIgnoreCase("class com.jgr.micro.app.usuarios.entity.Alumno"),
					() -> "no coinciden los valores de getClass");
		}, () -> {
			assertEquals(al1.getClass().getCanonicalName().trim(), "com.jgr.micro.app.usuarios.entity.Alumno",
					() -> "no coinciden los valores de getCanonicalName()");
		});

	}

	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject1() {
		al2 = al1;

		assertEquals(al2, al1);
	}

	/**
	 * Test method for {@link java.lang.Object#clone()}.
	 */
	@Test
	@Disabled
	void testClone() {

		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	@Disabled
	void testToString1() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link java.lang.Object#notify()}.
	 */
	@Test
	@Disabled
	void testNotify() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link java.lang.Object#notifyAll()}.
	 */
	@Test
	@Disabled
	void testNotifyAll() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link java.lang.Object#wait()}.
	 */
	@Test
	@Disabled
	void testWait() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link java.lang.Object#wait(long)}.
	 */
	@Test
	@Disabled
	void testWaitLong() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link java.lang.Object#wait(long, int)}.
	 */
	@Test
	@Disabled
	void testWaitLongInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link java.lang.Object#finalize()}.
	 */
	@Test
	@Disabled
	void testFinalize() {
		fail("Not yet implemented"); // TODO
	}

}
