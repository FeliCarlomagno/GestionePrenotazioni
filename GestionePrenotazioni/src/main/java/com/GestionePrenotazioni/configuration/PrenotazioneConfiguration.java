package com.GestionePrenotazioni.configuration;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.GestionePrenotazioni.model.Postazione;
import com.GestionePrenotazioni.model.Prenotazione;
import com.GestionePrenotazioni.model.Utente;

@Configuration
public class PrenotazioneConfiguration {
	
	@Bean("Prenotazione")
	@Scope("prototype")
	public Prenotazione prenotazione(Postazione postazione, Utente utente, LocalDate data) {
		Prenotazione p = new Prenotazione();
		p.setPostazione(postazione);
		p.setUtente(utente);
		p.setData(data);
		
		return p;
	}
}
