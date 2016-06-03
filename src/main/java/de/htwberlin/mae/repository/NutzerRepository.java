package de.htwberlin.mae.repository;


import java.util.List;
import java.util.Set;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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
	
	@Query("SELECT nutzerId, name FROM Nutzer n WHERE n.nutzerId IN :ids")
	List<Nutzer> findByIdsCustom(@Param("ids") Set<Long> ids, Pageable pageable);
}
