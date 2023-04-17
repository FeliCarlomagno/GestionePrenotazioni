package com.GestionePrenotazioni.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.GestionePrenotazioni.model.Postazione;
import com.GestionePrenotazioni.model.Prenotazione;
import com.GestionePrenotazioni.model.Utente;

@Repository
public interface PrenotazioneDAORepository extends CrudRepository<Prenotazione, Long> {
	
	@Query (value = "SELECT p FROM Prenotazione p INNER JOIN p.utente u WHERE u = :utente")
	public List<Prenotazione> listByUtente(Utente utente);
	
	@Query(value = "SELECT p FROM Prenotazione p INNER JOIN p.utente u WHERE u = :utente AND p.data = :data")
	public List<Prenotazione> listByUsersAndDate(Utente utente, LocalDate data);
	
	@Query(value = "SELECT p FROM Prenotazione p INNER JOIN p.postazione post WHERE post = :post AND p.data = :data")
	public List<Prenotazione> listByPostAndDate(Postazione post, LocalDate data);
}
