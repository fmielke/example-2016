package de.htwberlin.mae.configuration;

import de.htwberlin.mae.model.Artikel;
import de.htwberlin.mae.model.Nutzer;
import de.htwberlin.mae.model.Warenkorb;
import de.htwberlin.mae.repository.ArtikelRepository;
import de.htwberlin.mae.repository.NutzerRepository;
import de.htwberlin.mae.repository.WarenkorbRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


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

    Logger log = LogManager.getRootLogger();

	public void run(String... args) {
        try {
            //Spring Data JPA increments Id over all tables, not for each table

            log.info("db initial setup starts");
            log.info("db initial setup nutzer starts");

            //store users -> ID 1-6
            ArrayList<Nutzer> nutzer = new ArrayList<Nutzer>();
            nutzer.add(new Nutzer("Marcus Schindler"));
            nutzer.add(new Nutzer("Fabian Mielke"));
            nutzer.add(new Nutzer("Florian Heilscher"));
            nutzer.add(new Nutzer("Aristide Defo"));
            nutzer.add(new Nutzer("Edmodn Kengne"));
            nutzer.add(new Nutzer("Mai Hong Nguyen"));
            nutzerRepository.save(nutzer);
          
            log.info("db initial setup artikel starts");
            //store artikel -> ID 7-13
            ArrayList<Artikel> artikel = new ArrayList<Artikel>();
            artikel.add(new Artikel("ID-1200", 4.99, "Bier"));
            artikel.add(new Artikel("ID-1200", 4.99, "Vodka"));
            artikel.add(new Artikel("ID-1201", 7.99, "Wein"));
            artikel.add(new Artikel("ID-1202", 5.60, "Zigaretten"));
            artikel.add(new Artikel("ID-1203", 15.99, "Rum"));
            artikel.add(new Artikel("ID-1204", 0.99, "Feuerzeug"));
            artikel.add(new Artikel("ID-1205", 1.49, "Chips"));
            artikel.add(new Artikel("ID-1206", 0.69, "Wasser"));
            artikelRepository.save(artikel);
  
            log.info("db initial setup warenkorb starts");
            //store warenkörbe -> X-Y
            
            ArrayList<Warenkorb> warenkorbs = new ArrayList<Warenkorb>();
            
            warenkorbs.add(new Warenkorb(nutzerRepository.findByNameContainingIgnoreCase("Marcus Schindler").get(0), artikelRepository.findByBezeichnungIgnoreCase("Bier").get(0), 5));
            warenkorbs.add(new Warenkorb(nutzerRepository.findByNameContainingIgnoreCase("Florian Heilscher").get(0), artikelRepository.findByBezeichnungIgnoreCase("Bier").get(0), 1));
            warenkorbs.add(new Warenkorb(nutzerRepository.findByNameContainingIgnoreCase("Fabian Mielke").get(0), artikelRepository.findByBezeichnungIgnoreCase("Bier").get(0), 2));
            warenkorbs.add(new Warenkorb(nutzerRepository.findByNameContainingIgnoreCase("Aristide Defo").get(0), artikelRepository.findByBezeichnungIgnoreCase("Bier").get(0), 4));
            warenkorbs.add(new Warenkorb(nutzerRepository.findByNameContainingIgnoreCase("Marcus Schindler").get(0), artikelRepository.findByBezeichnungIgnoreCase("Bier").get(0), 3));
            
            warenkorbRepository.save(warenkorbs);
            log.info("db initial setup ends");
        }
        catch (Exception ex) {
            log.error("db initial setup failed");
        }
		
	}
}
