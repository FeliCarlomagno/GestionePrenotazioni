package com.GestionePrenotazioni.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.GestionePrenotazioni.model.Edificio;
import com.github.javafaker.Faker;

@Configuration
@PropertySource ("classpath:application.properties")
public class EdificioConfiguration {
	
	
	@Bean("ParamsEdificio")
	@Scope("prototype")
	public Edificio paramsEdificio(Long id, String nome, String indirizzo, String citta) {
		return new Edificio(id,nome, indirizzo, citta);
	}
	
	@Bean("FakeEdificio")
	@Scope("prototype")
	public Edificio fakeEdificio() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		Edificio e = new Edificio();
		e.setCitta(fake.address().city());
		e.setIndirizzo(fake.address().streetAddress());
		e.setNome("edificio prova");
		return e;
	}

}
