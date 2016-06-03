package de.htwberlin.mae.controller;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.htwberlin.mae.model.Nutzer;
import de.htwberlin.mae.repository.NutzerRepository;


@RequestMapping("/bulk")
@RepositoryRestController
public class NutzerRestController /*implements  ResourceProcessor<RepositoryLinksResource>*/ {
	
	@Autowired
	private NutzerRepository nutzerRepository;
	
	@Autowired
	public NutzerRestController(NutzerRepository nutzerRepository) {
		this.nutzerRepository = nutzerRepository;
	}
	
	@RequestMapping(
			value = "",
			method = RequestMethod.POST
			)
	public ResponseEntity<?> readOneNutzer(@RequestBody List<Nutzer> nutzer) {

		
		for(Nutzer n : nutzer) {
			Nutzer savedNutzer = this.nutzerRepository.save(n);
			System.out.println(savedNutzer.getName() + " : " + savedNutzer.getNutzerId());
		}
		
		Iterable<Nutzer> allNutzer = this.nutzerRepository.findAll();
		
		Resources<Nutzer> resources = new Resources<Nutzer>(allNutzer); 

        resources.add(linkTo(methodOn(NutzerRestController.class).readOneNutzer(nutzer)).withSelfRel()); 

        // add other links as needed

        return ResponseEntity.ok(resources);
	}
	
	@Bean
	public ResourceProcessor<Resource<Nutzer>> personProcessor() {

		   return new ResourceProcessor<Resource<Nutzer>>() {

		     @Override
		     public Resource<Nutzer> process(Resource<Nutzer> resource) {

		      resource.add(new Link("http://localhost:8080/api/nutzer", "nutzer"));
		      return resource;
		     }
		   };
		}
	
	/*
	@Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource.add(ControllerLinkBuilder.linkTo(Nutzer.class).withRel("bulk"));

        return resource;
    }
    */
    
}
