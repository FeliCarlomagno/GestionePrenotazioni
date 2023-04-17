package com.GestionePrenotazioni.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.GestionePrenotazioni.enums.TipoPostazione;
import com.GestionePrenotazioni.model.Edificio;
import com.GestionePrenotazioni.model.Postazione;
import com.github.javafaker.Faker;

@Configuration
@PropertySource ("classpath:application.properties")
public class PostazioneConfiguration {
	
	
	@Bean("Postazione")
	@Scope("prototype")
	public Postazione paramsPostazione(Long id, String descrizione, 
										TipoPostazione tipoPostazione,
										int numMaxOccupanti, Edificio edificio) {
		return new Postazione (id,descrizione,tipoPostazione,numMaxOccupanti,edificio);
	}
	
	
	//FAKE POSTAZIONE:
	@Bean("FakePostazione")
	@Scope("prototype")
	public Postazione fakePostazione() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		Postazione p = new Postazione();
		int numeroOccupantiRandom = fake.number().numberBetween(1, 50);
		p.setNumMaxOccupanti(numeroOccupantiRandom);
		if(numeroOccupantiRandom == 1) {
			p.setTipo(TipoPostazione.PRIVATO);
		} else if(numeroOccupantiRandom >= 25) {
			p.setTipo(TipoPostazione.OPENSPACE);
		}else {
			p.setTipo(TipoPostazione.SALARIUNIONI);
		}
		
		p.setDescrizione("Luogo comune dove poter esprimere le proprie idee.");
		
		return p;
	}
}
