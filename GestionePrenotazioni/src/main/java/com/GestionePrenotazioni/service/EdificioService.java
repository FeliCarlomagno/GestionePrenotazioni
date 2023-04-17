package com.GestionePrenotazioni.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.GestionePrenotazioni.model.Edificio;
import com.GestionePrenotazioni.repository.EdificioDAORepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EdificioService {
	
	@Autowired EdificioDAORepository repoEdificio;
	
	@Autowired @Qualifier("FakeEdificio") private ObjectProvider<Edificio> fakeEdificioProvider;
	
	public void createFakeEdificio() {
		insertEdificio(fakeEdificioProvider.getObject());
	}
	
	
	public void insertEdificio(Edificio e) {
		repoEdificio.save(e);
	}
	
	public Edificio finById(Long id) {
		return repoEdificio.findById(id).get();
	}
	
	public List<Edificio> findAll(){
		return (List<Edificio>) repoEdificio.findAll();
	}
	
	public Edificio edificioRandom() {
		return repoEdificio.edificioRandom();
	}
	
	
	
}
