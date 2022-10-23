package com.jgr.micro.app.cursos.models.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jgr.modelo.microservicio.datos.entity.Alumno;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
//@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cursos")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	private String nombre;

	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Alumno> alumnos;

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public Curso() {
		this.alumnos = new ArrayList<Alumno>();
	}

	public void addAlumno(Alumno alumno) {
		this.alumnos.add(alumno);

	}

	public void removeAlumno(Alumno alumno) {
		this.alumnos.remove(alumno);

	}
	
	

}
