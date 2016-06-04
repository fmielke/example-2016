package de.htwberlin.mae.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.htwberlin.mae.model.Warenkorb;

@RepositoryRestResource(collectionResourceRel = "warenkorb", path = "warenkorb")
public interface WarenkorbRepository extends PagingAndSortingRepository<Warenkorb, Long> {

	/**
	 * Override default JPA Methods to enable Spring Caching
	 */
	@Override
	@Cacheable(value = "warenkorbCache")
	Warenkorb findOne(Long id);

	@Override
	@Cacheable(value = "warenkorbCache")
	Iterable<Warenkorb> findAll();

	@Override
	@Cacheable(value = "warenkorbCache")
	Page<Warenkorb> findAll(Pageable pageable);

	@Override
	@Cacheable(value = "warenkorbCache")
	Iterable<Warenkorb> findAll(Iterable<Long> ids);

	@Override
	@CacheEvict(value = "warenkorbCache", allEntries=true)
	<S extends Warenkorb> S save(S entity);

	@Override
	@CacheEvict(value = "warenkorbCache", allEntries=true)
	void delete(Long id);
}
