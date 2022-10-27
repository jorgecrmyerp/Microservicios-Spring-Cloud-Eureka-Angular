package com.jgr.micro.app.examenes.entity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.micro.app.examenes.entity.repository.IExamenRepository;
import com.jgr.micro.generic.services.GenericServiceImpl;
import com.jgr.modelo.microservicio.datos.examen.entity.Examen;

// TODO: Auto-generated Javadoc
/**
 * The Class ExamenServiceImpl.
 */
@Service
public class ExamenServiceImpl extends GenericServiceImpl<Examen,IExamenRepository> implements IExamenService{

	
	/**
	 * Find exam by nombre.
	 *
	 * @param nombre the nombre
	 * @return the list
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Examen> findExamByNombre(String nombre) {
		return repository.findExamByNombre(nombre);
	}
	
	

}
