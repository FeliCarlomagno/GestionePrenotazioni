package com.GestionePrenotazioni.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.GestionePrenotazioni.model.Edificio;

@Repository
public interface EdificioDAORepository extends CrudRepository<Edificio, Long> {

	@Query(value = "SELECT e FROM Edificio e ORDER BY RANDOM() LIMIT 1")
	public Edificio edificioRandom();
}
