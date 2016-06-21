package de.htwberlin.mae.repository;


import java.util.List;
import java.util.Set;




import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import org.springframework.data.rest.core.annotation.RestResource;

import de.htwberlin.mae.model.Nutzer;

@RepositoryRestResource(collectionResourceRel = "nutzer", path = "nutzer")
public interface NutzerRepository extends PagingAndSortingRepository<Nutzer, String> {
	
	/**
	 * Override default JPA Methods to enable Spring Caching
	 */
	
	
	@Override
	@Cacheable(value = "nutzerCache")
	Nutzer findOne(String id);
	
	
	@Override
	@Cacheable(value = "nutzerCache")
	Iterable<Nutzer> findAll();

	@Override
	@Cacheable(value = "nutzerCache")
	Page<Nutzer> findAll(Pageable pageable);
	
	@Override
	@Cacheable(value = "nutzerCache")
	Iterable<Nutzer> findAll(Iterable<String> ids);
	
	@Override
	@CacheEvict(value = "nutzerCache", allEntries=true) //cacheEvict allEntries -> clear and reload Cache when Cache changed
	<S extends Nutzer> S save(S entity);

	@Override
	@CacheEvict(value = "nutzerCache", allEntries=true)
	void delete(String id);
	
	//restresources found under /nutzer/search/...
	@RestResource(path = "names", rel = "names")
	List<Nutzer> findByName(@Param("name") String name);
	
	@Query("SELECT nutzerId, name FROM Nutzer n WHERE n.nutzerId IN :ids")
	List<Nutzer> findByIdsCustom(@Param("ids") Set<String> ids, Pageable pageable);
}
