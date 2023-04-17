package com.GestionePrenotazioni.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "utenti")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String nomeCompleto;
	private String email;

	
	//CONSTRUCTOR SENZA ID:
		public Utente(String username, String nomeCompleto, String email) {
		super();
		this.username = username;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
	}
}
