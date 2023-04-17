package com.GestionePrenotazioni.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.GestionePrenotazioni.model.Utente;
import com.github.javafaker.Faker;

@Configuration
@PropertySource ("classpath:application.properties")
public class UtenteConfiguration {
	
	
	
	@Bean("ParamsUtente")
	@Scope("prototype")
	public Utente paramsUtente (String username, String nomeCompleto, String email) {
		return new Utente(username,nomeCompleto,email);
	}
	
	@Bean("FakeUtente")
	@Scope("prototype")
	public Utente fakeUtente() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		String name = fake.name().firstName();
		String lastname = fake.name().lastName();
		
		Utente u = new Utente();
		u.setUsername(name + "." + lastname + "." + fake.number().toString());
		u.setNomeCompleto(name + " " + lastname);
		u.setEmail(name.charAt(0) + "." + lastname + "@example.com");
		
		return u;
	}
}
