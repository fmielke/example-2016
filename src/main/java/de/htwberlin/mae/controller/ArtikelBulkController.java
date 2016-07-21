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
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.htwberlin.mae.model.Article;
import de.htwberlin.mae.repository.ArticleRepository;


@RepositoryRestController
public class ArtikelBulkController {

	private ArticleRepository articleRepository;
	
	
	@Autowired
	public ArtikelBulkController(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	
	//TODO Diese funktion muss noch eine HATOAS konformes result liefen. Die Self Ref links müssen noch angepasst werden
	@RequestMapping(
			value = "/artikel/bulk",
			method = RequestMethod.POST
			)
	public @ResponseBody ResponseEntity<ArrayList<Resource<Article>>> saveBulkArtikel(@RequestBody List<Article> newArticle, Pageable pageable) throws URISyntaxException {

		ArrayList<Resource<Article>> addedArticles = new ArrayList<Resource<Article>>();
		
		for(Article article : newArticle) {
			Resource<Article> ar = new Resource<Article>(this.articleRepository.save(article));
			ar.add(linkTo(methodOn(ArtikelBulkController.class).saveBulkArtikel(newArticle, pageable)).withSelfRel());
			addedArticles.add(ar);
		}
		
		
		//Resources<Artikel> resources = new Resources<Artikel>(newArtikel); 
        //resources.add(linkTo(methodOn(ArtikelBulkController.class).saveBulkArtikel(newArtikel, pageable)).withSelfRel()); 

        // add other links as needed
        URI location = new URI("api/article");
        return ResponseEntity.created(location).header("MyResponseHeader", "MyValue").body(addedArticles);
	}
}
