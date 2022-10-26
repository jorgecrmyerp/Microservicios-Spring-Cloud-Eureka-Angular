package com.jgr.modelo.microservicio.datos.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

//@EqualsAndHashCode

@Entity
@Table(name = "alumnos")
public class Alumno {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;


	private String nombre;

	private String apellidos;

	private String email;
	
	@Lob
	@JsonIgnore
	private byte[] foto;

	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	// sobreescribo el equals asteriscando el create porque si no,da problemas
	// cuando hace el compare
	// en las pruebas
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Alumno)) {
			return false;
		}
		Alumno other = (Alumno) obj;
		return  other.id!=null && Objects.equals(this.id, other.id);
	}
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, createAt, email, id, nombre);
	}


}
