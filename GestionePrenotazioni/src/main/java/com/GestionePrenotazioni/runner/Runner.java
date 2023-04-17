package com.GestionePrenotazioni.runner;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.GestionePrenotazioni.enums.TipoPostazione;
import com.GestionePrenotazioni.model.Postazione;
import com.GestionePrenotazioni.model.Prenotazione;
import com.GestionePrenotazioni.model.Utente;
import com.GestionePrenotazioni.service.EdificioService;
import com.GestionePrenotazioni.service.PostazioneService;
import com.GestionePrenotazioni.service.PrenotazioneService;
import com.GestionePrenotazioni.service.UtenteService;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class Runner implements ApplicationRunner {
	
	@Autowired PrenotazioneService prenotazioneService;
	
	@Autowired UtenteService utenteService;
	
	@Autowired PostazioneService postazioneService;
	
	@Autowired EdificioService edificioService;
	
	public static Scanner scanner = new Scanner(System.in);
	Utente utente = new Utente();
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		utenteService.createFakeUtenteo();
		edificioService.createFakeEdificio();
		postazioneService.creaFakePostazione();
		
		System.out.println("\n\t HOMEPAGE GESTIONE PRENOTAZIONI: \t\n");
		System.out.println("\t INSERISCI IL TUO ID: ");
		Long idUtente = scanner.nextLong();
		
		try {
			utente = utenteService.findByID(idUtente);
		} catch (Exception e) {
			log.error("NESSUN UTENTE PRESENTE CON QUESTO ID!!");
			System.exit(1);
		}
		
		while(true) {
			startMenu();
		}
		
		
	}
	
	public void startMenu() {
			System.out.println("\n SEGLI COSA VUOI FARE: ");
			System.out.println("\t 1. ELENCO PRENOTAZIONI ");
			System.out.println("\t 2. CERCA POSTAZIONE IN BASE ALLA TIPOLOGIA ");
			System.out.println("\t 3. CERCA POSTAZIONE IN BASE ALLA CITTA' ");
			System.out.println("\t 4. EFFETTUA UNA PRENOTAZIONE ");
			System.out.println("\t 5. EXIT ");
			
			int scelta = scanner.nextInt();
			
			switch(scelta) {
			case 1:
				List<Prenotazione> prenotazioni = prenotazioneService.findByUser(utente);
				
				if(prenotazioni.size() > 0) {
					prenotazioni.forEach(pren -> System.out.println(pren));
				}else {
					log.warn("NON ESISTONO PRENOTAZIONI A TUO NOME!");
				}
				break;
				
			case 2: 
				scanner.nextLine();
				TipoPostazione tipo = null;
				System.out.println("\t INSERISCI IL TIPO DELLA POSTAZIONE PER IL QUALE EFFETUARE UNA RICERCA: ");
				System.out.println("\t 1.PRIVATO | 2.OPENSPACE | 3.SALA RIUNIONI ");
				int sceltaTipo = scanner.nextInt();
				scanner.nextLine();
				
				switch(sceltaTipo) {
				case 1: 
					tipo = TipoPostazione.PRIVATO;
					break;
					
				case 2: 
					tipo = TipoPostazione.OPENSPACE;
					break;
					
				case 3: 
					tipo = TipoPostazione.SALARIUNIONI;
					break;
				}
				
				List<Postazione> listaTipo = postazioneService.findByType(tipo);
				
				if(listaTipo.size() > 0) {
					listaTipo.forEach(lt -> System.out.println(lt));
				}else {
					log.warn("SELEZIONE DEL TIPO ERRATA O TIPOLOGIA INESISTENTE!");
				}
				break;
				
				
			case 3:
				System.out.println("\t INSERISCI IL NOME DELLA CITTA' IN CUI VUOI TROVARE UNA POSTAZIONE: ");
				String citta = scanner.nextLine();
				List<Postazione> listaCitta = postazioneService.findCity(citta);
				if(listaCitta.size() > 0) {
					listaCitta.forEach(c -> System.out.println(c));
				}else {
					log.warn("NON ESISTONO POSTAZIONI IN QUESTA CITTA'!");
				}
				break;
				
			case 4:
				System.out.println("\t INSERISCI L'ID DELLA POSTAZIONE CHE VUOI PRENOTARE: ");
				Long idPostazione = scanner.nextLong();
				Postazione postazione = null;
				
				try {
					postazione = postazioneService.findByID(idPostazione);
				} catch (Exception e) {
					log.error("LA POSTAZIONE SELEZIONATA NON E' ESISTENTE!");
				}
				
				System.out.println("\t INSERISCI UNA DATA PER LA PRENOTAZIONE: ");
				LocalDate dataPrenotazione = generaData();
				prenotazioneService.insertPrenotazione(new Prenotazione (utente, postazione, dataPrenotazione));
				scanner.nextLine();
				break;
				
			case 0: 
				log.warn("\t USCITA IN CORSO!");
				System.exit(0);
				break;
				
			default:
				log.error("\t SCELTA NON VALIDA, ERRORE DI INSERIMENTO!");
				break;
			}
			
			
			
		}
	
		public static LocalDate generaData() {
					System.out.print("\n Inserisci Giorno (DD):");
					int giorno = scanner.nextInt();
					System.out.print("\n Inserisci Mese (MM):");
					int mese = scanner.nextInt();
					System.out.print("\n Inserisci Anno (YYYY):");
					int anno = scanner.nextInt();
					return LocalDate.of(anno, mese, giorno);
				}

}
