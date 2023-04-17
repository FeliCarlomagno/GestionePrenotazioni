package com.GestionePrenotazioni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.GestionePrenotazioni.enums.TipoPostazione;
import com.GestionePrenotazioni.model.Postazione;


@Repository
public interface PostazioneDAORepository extends CrudRepository<Postazione, Long> {
	
	@Query(value = "SELECT p FROM Postazione p INNER JOIN p.edificio e WHERE LOWER(e.citta)LIKE LOWER('%' || :citta || '%')")
	public List<Postazione> listByCitta(String citta);
	
	@Query (value = "SELECT p FROM Postazione p WHERE p.tipo LIKE :tipo")
	public List<Postazione> listByTipo(TipoPostazione tipo);
	
}
