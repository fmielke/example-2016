package de.htwberlin.mae.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.htwberlin.mae.model.Warenkorb;

@RepositoryRestResource(collectionResourceRel = "warenkorb", path = "warenkorb")
public interface WarenkorbRepository extends PagingAndSortingRepository<Warenkorb, Long> {
	
}
