package de.htwberlin.mae.repository;

import java.util.UUID;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import de.htwberlin.mae.configuration.CustomRestMvcConfiguration;
import de.htwberlin.mae.model.Warenkorb;

@Transactional
@RepositoryRestResource(collectionResourceRel = "warenkorb", path = "warenkorb")
public interface WarenkorbRepository extends PagingAndSortingRepository<Warenkorb, UUID> {

	/**
	 * Override default JPA Methods to enable Spring Caching
	 */
	@Override
	@Cacheable(value = "warenkorbCache")
	Warenkorb findOne(UUID id);

	@Override
	@Cacheable(value = "warenkorbCache")
	Iterable<Warenkorb> findAll();

	@Override
	@Cacheable(value = "warenkorbCache")
	Page<Warenkorb> findAll(Pageable pageable);

	@Override
	@Cacheable(value = "warenkorbCache")
	Iterable<Warenkorb> findAll(Iterable<UUID> ids);

	@Override
	@CacheEvict(value = "warenkorbCache", allEntries=true)
	<S extends Warenkorb> S save(S entity);

	@Override
	@CacheEvict(value = "warenkorbCache", allEntries=true)
	void delete(UUID id);
}
