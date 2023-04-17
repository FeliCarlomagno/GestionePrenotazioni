package com.GestionePrenotazioni.model;

import com.GestionePrenotazioni.enums.TipoPostazione;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "postazioni")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Postazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descrizione;
	
	@Enumerated(EnumType.STRING)
	private TipoPostazione tipo;
	
	private int numMaxOccupanti;
	
	@ManyToOne
	@JoinColumn
	private Edificio edificio;
	
	//CONSTRUCTOR SENZA ID:
	public Postazione(String descrizione, TipoPostazione tipo, int numMaxOccupanti,
			Edificio edificio) {
		super();
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.numMaxOccupanti = numMaxOccupanti;
		this.edificio = edificio;
	}
	
	
}
