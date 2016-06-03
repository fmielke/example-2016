package de.htwberlin.mae.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.htwberlin.mae.model.Artikel;
import de.htwberlin.mae.repository.ArtikelRepository;

@RequestMapping("/bulk/artikel")
//@RepositoryRestResource
@Controller
public class ArtikelBulkController {

	@Autowired
	private ArtikelRepository artikelRepository;
	
	
	@Autowired
	public ArtikelBulkController(ArtikelRepository artikelRepository) {
		this.artikelRepository = artikelRepository;
	}
	
	
	//TODO Diese funktion muss noch eine HATOAS konformes result liefen. Die Self Ref links müssen noch angepasst werden
	@RequestMapping(
			value = "",
			method = RequestMethod.POST,
			produces = "application/de.htwberlin.mae.artikel.bulk+json"
			)
	public ResponseEntity<ArrayList<Resource<Artikel>>> saveBulkArtikel(@RequestBody List<Artikel> newArtikel, Pageable pageable) throws URISyntaxException {

		ArrayList<Resource<Artikel>> addedArtikel = new ArrayList<Resource<Artikel>>();
		//Set<Long> set = new HashSet<Long>();
		
		for(Artikel artikel : newArtikel) {
			Resource<Artikel> ar = new Resource<Artikel>(this.artikelRepository.save(artikel));
			ar.add(linkTo(methodOn(ArtikelBulkController.class).saveBulkArtikel(newArtikel, pageable)).withSelfRel());
			addedArtikel.add(ar);
		}
		
		//Resources<Artikel> resources = new Resources<Artikel>(newArtikel); 
        //resources.add(linkTo(methodOn(ArtikelBulkController.class).saveBulkArtikel(newArtikel, pageable)).withSelfRel()); 

        // add other links as needed
        URI location = new URI("api/artikel");
        return ResponseEntity.created(location).header("MyResponseHeader", "MyValue").body(addedArtikel);
	}	
}
