package com.GestionePrenotazioni.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GestionePrenotazioni.model.Postazione;
import com.GestionePrenotazioni.model.Prenotazione;
import com.GestionePrenotazioni.model.Utente;
import com.GestionePrenotazioni.repository.PrenotazioneDAORepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PrenotazioneService {
	
	 @Autowired PrenotazioneDAORepository repoPrenotazione;
	 
	public void insertPrenotazione(Prenotazione p) {
		List<Prenotazione> listaUtente = findByUtente_Data(p.getUtente(), p.getData());
		List<Prenotazione> listaPostazione = listByPost_Date(p.getPostazione(), p.getData());
		if(listaUtente.size() > 0) {
			log.error("PRENOTAZIONE GIA' ESISTENTE PER QUESTA DATA!");
		}else if(listaPostazione.size() > 0) {
			log.error("SPIACENTE, LA POSTAZIONE RICHIESTA E' GIA OCCUPATA PER QUESTA DATA!");
		}else {
			repoPrenotazione.save(p);
			log.info("PRENOTAZIONE EFFETTUATA CORRETTAMENTE");
		}
	}
	 
	public Prenotazione findByID(Long id) {
		return repoPrenotazione.findById(id).get();
	}
	
	public List<Prenotazione> findByUser(Utente u){
		return repoPrenotazione.listByUtente(u);
	}
	
	public List<Prenotazione> findAll(){
		return (List<Prenotazione>) repoPrenotazione.findAll();
	}
	
	public List<Prenotazione> findByUtente_Data(Utente u, LocalDate data){
		return repoPrenotazione.listByUsersAndDate(u, data);
	}
	
	public List<Prenotazione> listByPost_Date(Postazione p, LocalDate data){
		return repoPrenotazione.listByPostAndDate(p, data);
	}
}
	
