package com.jgr.micro.app.usuarios.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new alumno.
 */

/**
 * Instantiates a new alumno.
 */
@NoArgsConstructor

/**
 * Instantiates a new alumno.
 *
 * @param id the id
 * @param nombre the nombre
 * @param apellidos the apellidos
 * @param email the email
 * @param createAt the create at
 */

/**
 * Instantiates a new alumno.
 *
 * @param id the id
 * @param nombre the nombre
 * @param apellidos the apellidos
 * @param email the email
 * @param createAt the create at
 */
@AllArgsConstructor

/**
 * To string.
 *
 * @return the java.lang. string
 */

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * Hash code.
 *
 * @return the int
 */

/**
 * Hash code.
 *
 * @return the int
 */
//@EqualsAndHashCode

@Entity
@Table(name="alumnos")
public class Alumno {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	/** The nombre. */
	private String nombre;
	
	/** The apellidos. */
	private String apellidos;
	
	/** The email. */
	private String email;
	
	/** The create at. */
	@Column(name="create_at")	
	private Date createAt;
	
	/**
	 * Pre persist.
	 * Inicializamos la fecha con la del momento
	 */
	@PrePersist
	public void prePersist() {
		this.createAt= new Date();
	}
	
	
	//sobreescribo el equals asteriscando el create porque si no,da problemas cuando hace el compare
	//en las pruebas

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Alumno)) {
			return false;
		}
		Alumno other = (Alumno) obj;
		return Objects.equals(this.apellidos, other.apellidos)
		//		&& Objects.equals(this.createAt, other.createAt)
				&& Objects.equals(this.email, other.email)
				&& Objects.equals(this.id, other.id)
				&& Objects.equals(this.nombre, other.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, createAt, email, id, nombre);
	}

	
	

}
