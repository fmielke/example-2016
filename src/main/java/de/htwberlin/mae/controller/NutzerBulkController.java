package de.htwberlin.mae.controller;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.htwberlin.mae.model.Nutzer;
import de.htwberlin.mae.repository.NutzerRepository;

//TODO es sollte eigenlich mit repositoryrestcontroller funktionieren
@RepositoryRestController
public class NutzerBulkController {
	
	@Autowired
	private NutzerRepository nutzerRepository;
	
	
	@Autowired
	public NutzerBulkController(NutzerRepository nutzerRepository) {
		this.nutzerRepository = nutzerRepository;
	}
	
	
	//TODO Diese funktion muss noch eine HATOAS konformes result liefen. bisher nur arraylist
	@RequestMapping(
			value = "/nutzer/bulk",
			method = RequestMethod.POST
			)
	public @ResponseBody ResponseEntity<ArrayList<Resource<Nutzer>>> saveBulkNutzer(@RequestBody List<Nutzer> newNutzer, Pageable pageable) throws URISyntaxException {

		ArrayList<Nutzer> nutzerAdded = new ArrayList<Nutzer>();
		
		ArrayList<Resource<Nutzer>> addedNutzer = new ArrayList<Resource<Nutzer>>();
		//Set<Long> set = new HashSet<Long>();
		
		for(Nutzer nutzer : newNutzer) {
			Resource<Nutzer> an = new Resource<Nutzer>(this.nutzerRepository.save(nutzer));
			an.add(linkTo(methodOn(NutzerBulkController.class).saveBulkNutzer(newNutzer, pageable)).withSelfRel());
			addedNutzer.add(an);
		}
		
		//List<Nutzer> nutzerPage= this.nutzerRepository.findByIdsCustom(set, pageable);
		
		//System.out.println("Nutzerpagesize: " + nutzerPage.size());
		//nutzerPage.forEach((n) -> {System.out.println("Nutzer: " + n.getName());});
		
		Resources<Nutzer> resources = new Resources<Nutzer>(nutzerAdded); 
        resources.add(linkTo(methodOn(NutzerBulkController.class).saveBulkNutzer(nutzerAdded, pageable)).withSelfRel()); 

        // add other links as needed
        URI location = new URI("api/nutzer");
        //return new ResponseEntity<PagedResources<Nutzer>>(assembler.toResource(nutzerPage, nutzerResourceAssembler), HttpStatus.CREATED);
        return ResponseEntity.created(location).header("MyResponseHeader", "MyValue").body(addedNutzer);
	}
		
	/*
	@Bean
	public ResourceProcessor<Resource<Nutzer>> personProcessor() {

		   return new ResourceProcessor<Resource<Nutzer>>() {

		     @Override
		     public Resource<Nutzer> process(Resource<Nutzer> resource) {

		      resource.add(new Link("http://localhost:8080/api/nutzer", "bulk"));
		      return resource;
		     }
		   };
		}
*/
	/*
	@Override
	public RepositoryLinksResource process(RepositoryLinksResource resource) {
		resource.add(ControllerLinkBuilder.linkTo(Nutzer.class).withRel("bulk"));
		return resource;
	} 
	*/   
}
