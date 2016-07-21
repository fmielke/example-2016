package de.htwberlin.mae.repository;

import java.util.List;
import java.util.Set;

import java.util.UUID;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import de.htwberlin.mae.model.Customer;

@Transactional
@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, UUID> {

	/**
	 * Override default JPA Methods to enable Spring Caching
	 */

	@Override
	@Cacheable(value = "customerCache")
	Customer findOne(UUID id);

	@Override
	@Cacheable(value = "customerCache")
	Iterable<Customer> findAll();

	@Override
	@Cacheable(value = "customerCache")
	Page<Customer> findAll(Pageable pageable);

	@Override
	@Cacheable(value = "customerCache")
	Iterable<Customer> findAll(Iterable<UUID> ids);

	@Override
	@CacheEvict(value = "customerCache", allEntries = true) // cacheEvict
															// allEntries ->
															// clear and reload
															// Cache when Cache
															// changed
	<S extends Customer> S save(S entity);

	@Override
	@CacheEvict(value = "customerCache", allEntries = true)
	void delete(UUID id);

	// restresources found under /nutzer/search/...
	@RestResource(path = "name", rel = "name")
	List<Customer> findByNameContainingIgnoreCase(@Param("name") String name);

	@RestResource(path = "ids", rel = "ids")
	@Query("SELECT customerId, name FROM Customer c WHERE c.customerId IN :ids")
	List<Customer> findByIdsCustom(@Param("ids") Set<UUID> ids);
}
