package com.GestionePrenotazioni.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.GestionePrenotazioni.model.Utente;

@Repository
public interface UtenteDAORepository extends CrudRepository<Utente, Long> {

}
