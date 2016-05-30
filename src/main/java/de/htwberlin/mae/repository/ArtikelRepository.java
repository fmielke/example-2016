package de.htwberlin.mae.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.htwberlin.mae.model.Artikel;


@RepositoryRestResource(collectionResourceRel = "artikel", path = "artikel")
public interface ArtikelRepository extends PagingAndSortingRepository<Artikel, Long> {
	
	
	
	List<Artikel> findByBezeichnung(@Param("bezeichnung") String bezeichnung);
	List<Artikel> findByPreis(@Param("preis") Double preis);
	
}
