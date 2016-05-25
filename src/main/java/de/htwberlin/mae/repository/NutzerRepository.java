package de.htwberlin.mae.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;





import de.htwberlin.mae.model.Nutzer;


@RepositoryRestResource(collectionResourceRel = "nutzer", path = "nutzer")
public interface NutzerRepository extends PagingAndSortingRepository<Nutzer, Long> {
	
	List<Nutzer> findByName(@Param("name") String name);
}
