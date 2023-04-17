package com.GestionePrenotazioni.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.GestionePrenotazioni.model.Utente;
import com.GestionePrenotazioni.repository.UtenteDAORepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtenteService {
	
	@Autowired private UtenteDAORepository repoUtente;
	
	@Autowired @Qualifier("FakeUtente") private ObjectProvider<Utente> fakeUtenteProvider;
	
	public void createFakeUtenteo() {
		insertUtente(fakeUtenteProvider.getObject());
	}
	
	public void rimuoviUtente(Long id) {
		repoUtente.delete(findByID(id));
		log.info("Utente rimosso correttamente dal Database!");
	}
	
	public void aggiornaUtente(Utente u) {
		repoUtente.save(u);
		log.info("Utente" + u.getNomeCompleto() + "aggiornato");
	}
	
	
	public void insertUtente(Utente u) {
		repoUtente.save(u);
		log.info("Utente" + u.getNomeCompleto() + "aggiunto correttamente al Database!");
		
	}
	
	public Utente findByID(Long id) {
		return repoUtente.findById(id).get();
	}
	
	public List<Utente> findAllUtenti(){
		return (List<Utente>) repoUtente.findAll();
	}
	

}
