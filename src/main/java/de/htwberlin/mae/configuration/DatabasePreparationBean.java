package de.htwberlin.mae.configuration;

import de.htwberlin.mae.model.Article;
import de.htwberlin.mae.model.Customer;
import de.htwberlin.mae.model.Cart;
import de.htwberlin.mae.repository.ArticleRepository;
import de.htwberlin.mae.repository.CustomerRepository;
import de.htwberlin.mae.repository.CartRepository;

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
	private CustomerRepository usersRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CartRepository cartRepository;

    Logger log = LogManager.getRootLogger();

	public void run(String... args) {
        try {
            //Spring Data JPA increments Id over all tables, not for each table

            log.info("db initial setup starts");
            log.info("db initial setup nutzer starts");

            //store users -> ID 1-6
            ArrayList<Customer> customers = new ArrayList<Customer>();
            customers.add(new Customer("Marcus Schindler"));
            customers.add(new Customer("Fabian Mielke"));
            customers.add(new Customer("Florian Heilscher"));
            customers.add(new Customer("Aristide Defo"));
            customers.add(new Customer("Edmodn Kengne"));
            customers.add(new Customer("Mai Hong Nguyen"));
            log.info(customers);
            usersRepository.save(customers);
          
            log.info("db initial setup artikel starts");
            //store artikel -> ID 7-13
            ArrayList<Article> articles = new ArrayList<Article>();
            articles.add(new Article("ID-1200", 4.99, "Bier"));
            articles.add(new Article("ID-1200", 4.99, "Vodka"));
            articles.add(new Article("ID-1201", 7.99, "Wein"));
            articles.add(new Article("ID-1202", 5.60, "Zigaretten"));
            articles.add(new Article("ID-1203", 15.99, "Rum"));
            articles.add(new Article("ID-1204", 0.99, "Feuerzeug"));
            articles.add(new Article("ID-1205", 1.49, "Chips"));
            articles.add(new Article("ID-1206", 0.69, "Wasser"));
            articleRepository.save(articles);
  
            log.info("db initial setup warenkorb starts");
            //store warenkörbe -> X-Y
            
            ArrayList<Cart> carts = new ArrayList<Cart>();
            
            carts.add(new Cart(usersRepository.findByNameContainingIgnoreCase("Marcus Schindler").get(0), articleRepository.findByLabelIgnoreCase("Bier").get(0), 5));
            carts.add(new Cart(usersRepository.findByNameContainingIgnoreCase("Florian Heilscher").get(0), articleRepository.findByLabelIgnoreCase("Bier").get(0), 1));
            carts.add(new Cart(usersRepository.findByNameContainingIgnoreCase("Fabian Mielke").get(0), articleRepository.findByLabelIgnoreCase("Bier").get(0), 2));
            carts.add(new Cart(usersRepository.findByNameContainingIgnoreCase("Aristide Defo").get(0), articleRepository.findByLabelIgnoreCase("Bier").get(0), 4));
            carts.add(new Cart(usersRepository.findByNameContainingIgnoreCase("Marcus Schindler").get(0), articleRepository.findByLabelIgnoreCase("Bier").get(0), 3));
            
            cartRepository.save(carts);
            log.info("db initial setup ends");
        }
        catch (Exception ex) {
            log.error("db initial setup failed");
        }
		
	}
}
