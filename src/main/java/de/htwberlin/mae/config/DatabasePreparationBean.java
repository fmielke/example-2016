package de.htwberlin.mae.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.htwberlin.mae.model.Artikel;
import de.htwberlin.mae.model.Nutzer;
import de.htwberlin.mae.model.Warenkorb;
import de.htwberlin.mae.repository.ArtikelRepository;
import de.htwberlin.mae.repository.NutzerRepository;
import de.htwberlin.mae.repository.WarenkorbRepository;


/**
 * Implementiert einen clr, welcher nach Spring-Boot start einmalig ausgeführt wird.
 * Wird genutzt um Datenbank mit beispieldaten zu füllen
 * @author marcus
 *
 */
@Component //component sagt, dass es sich um eine Spring Bean handelt
public class DatabasePreparationBean implements CommandLineRunner {
	
	@Autowired //Autowired erzeugt ein Object
	private NutzerRepository nutzerRepository;
	
	@Autowired
	private ArtikelRepository artikelRepository;
	
	@Autowired
	private WarenkorbRepository warenkorbRepository;
	
	
	public void run(String... args) {
		//Spring Data JPA increments Id over all tables, not for each table
		
		//store users -> ID 1-6
		ArrayList<Nutzer> nutzer = new ArrayList<Nutzer>();
		nutzer.add(new Nutzer("Marcus Schindler"));
		nutzer.add(new Nutzer("Fabian Mielke"));
		nutzer.add(new Nutzer("Florian Heilscher"));
		nutzer.add(new Nutzer("Aristide Defo"));
		nutzer.add(new Nutzer("Edmodn Kengne"));
		nutzer.add(new Nutzer("Mai Hong Nguyen"));
		nutzerRepository.save(nutzer);
		
		
		//store artikel -> ID 7-13
		ArrayList<Artikel> artikel = new ArrayList<Artikel>();
		artikel.add(new Artikel("ID-1200", 4.99, "Bier"));
		artikel.add(new Artikel("ID-1201", 7.99, "Wein"));
		artikel.add(new Artikel("ID-1202", 5.60, "Zigaretten"));
		artikel.add(new Artikel("ID-1203", 15.99, "Rum"));
		artikel.add(new Artikel("ID-1204", 0.99, "Feuerzeug"));
		artikel.add(new Artikel("ID-1205", 1.49, "Chips"));
		artikel.add(new Artikel("ID-1206", 0.69, "Wasser"));
		artikelRepository.save(artikel);
		
		
		//store warenkörbe -> X-Y
		ArrayList<Warenkorb> warenkorbs = new ArrayList<Warenkorb>();
		warenkorbs.add(new Warenkorb(nutzerRepository.findOne(1L), artikelRepository.findOne(7L), 3));
		warenkorbs.add(new Warenkorb(nutzerRepository.findOne(1L), artikelRepository.findOne(9L), 2));
		warenkorbs.add(new Warenkorb(nutzerRepository.findOne(1L), artikelRepository.findOne(11L), 1));
		warenkorbs.add(new Warenkorb(nutzerRepository.findOne(1L), artikelRepository.findOne(12L), 2));
		warenkorbs.add(new Warenkorb(nutzerRepository.findOne(2L), artikelRepository.findOne(7L), 1));
		warenkorbs.add(new Warenkorb(nutzerRepository.findOne(2L), artikelRepository.findOne(8L), 1));
		warenkorbs.add(new Warenkorb(nutzerRepository.findOne(3L), artikelRepository.findOne(10L), 1));
		warenkorbs.add(new Warenkorb(nutzerRepository.findOne(4L), artikelRepository.findOne(12L), 4));
		warenkorbs.add(new Warenkorb(nutzerRepository.findOne(5L), artikelRepository.findOne(13L), 1));
		warenkorbs.add(new Warenkorb(nutzerRepository.findOne(6L), artikelRepository.findOne(13L), 2));
		warenkorbs.add(new Warenkorb(nutzerRepository.findOne(4L), artikelRepository.findOne(7L), 3));
		warenkorbs.add(new Warenkorb(nutzerRepository.findOne(5L), artikelRepository.findOne(7L), 5));
		warenkorbRepository.save(warenkorbs);
		
		
	}
}
