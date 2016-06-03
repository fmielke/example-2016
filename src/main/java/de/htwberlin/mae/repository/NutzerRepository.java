package de.htwberlin.mae.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import org.springframework.data.rest.core.annotation.RestResource;

import de.htwberlin.mae.model.Nutzer;

@RepositoryRestResource(collectionResourceRel = "nutzer", path = "nutzer")
public interface NutzerRepository extends PagingAndSortingRepository<Nutzer, Long> {
	
	//restresources found under /nutzer/search/...
	@RestResource(path = "names", rel = "names")
	List<Nutzer> findByName(@Param("name") String name);	
}
