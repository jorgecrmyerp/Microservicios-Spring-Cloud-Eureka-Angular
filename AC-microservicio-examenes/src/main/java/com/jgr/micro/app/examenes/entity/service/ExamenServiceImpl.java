package com.jgr.micro.app.examenes.entity.service;

import org.springframework.stereotype.Service;

import com.jgr.micro.app.examenes.entity.Examen;
import com.jgr.micro.app.examenes.entity.repository.ExamenRepository;
import com.jgr.micro.generic.services.GenericServiceImpl;

@Service
public class ExamenServiceImpl extends GenericServiceImpl<Examen,ExamenRepository> implements IExamenService{

}
