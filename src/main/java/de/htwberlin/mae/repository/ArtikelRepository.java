package de.htwberlin.mae.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import de.htwberlin.mae.model.Artikel;

@Transactional
@RepositoryRestResource(collectionResourceRel = "artikel", path = "artikel")
public interface ArtikelRepository extends PagingAndSortingRepository<Artikel, UUID>{

	/**
	 * Override default JPA Methods to enable Spring Caching
	 */
	
	//@Override
	@Cacheable(value = "artikelCache")
	Artikel findOne(UUID id);

	@Override
	@Cacheable(value = "artikelCache")
	Iterable<Artikel> findAll();

	@Override
	@Cacheable(value = "artikelCache")
	Page<Artikel> findAll(Pageable pageable);
	
	//@Override
	@Cacheable(value = "artikelCache")
	Iterable<Artikel> findAll(Iterable<UUID> ids);
	
	@Override
	@CacheEvict(cacheNames="artikelCache", allEntries=true)
	<S extends Artikel> S save(S entity);

	@Override
	@CacheEvict(value = "artikelCache", allEntries=true)
	void delete(UUID id);
	
	
	/**
	 * adding custom search methods -> under /search resource
	 */
	@RestResource(path="bezeichnung", rel="bezeichnung")
	public List<Artikel> findByBezeichnung(@Param("bezeichnung") String bezeichnung);
	
	@RestResource(path="preis", rel="preis")
	public List<Artikel> findByPreis(@Param("preis") Double preis);
}
