package com.GestionePrenotazioni.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.GestionePrenotazioni.enums.TipoPostazione;
import com.GestionePrenotazioni.model.Edificio;
import com.GestionePrenotazioni.model.Postazione;
import com.GestionePrenotazioni.repository.PostazioneDAORepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostazioneService {
	
	@Autowired private PostazioneDAORepository repoPostazione;
	@Autowired private EdificioService edificioService;
	
	@Autowired @Qualifier ("FakePostazione") private ObjectProvider<Postazione> fakePostazioneProvider;
	
	//METODI:
	public void creaFakePostazione() {
		Postazione p = (fakePostazioneProvider.getObject());
		Edificio e = edificioService.edificioRandom();
		p.setEdificio(e);
		insertPostazione(p);
		
	}
	
	public void insertPostazione(Postazione p) {
		repoPostazione.save(p);
		log.info("Postazione aggiunta correttamente al Database!");
	}
	
	public void aggiornaPostazione(Postazione p) {
		repoPostazione.save(p);
	}
	
	
	
	public Postazione findByID(Long id) {
		return repoPostazione.findById(id).get();
	}
	
	public List<Postazione> findAll(){
		return (List<Postazione>) repoPostazione.findAll();
	}
	
	public List<Postazione> findCity(String citta){
		return repoPostazione.listByCitta(citta);
	}
	
	public List<Postazione> findByType(TipoPostazione tipoPostazione){
		return repoPostazione.listByTipo(tipoPostazione);
	}
}
