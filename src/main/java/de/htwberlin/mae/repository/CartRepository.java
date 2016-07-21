package de.htwberlin.mae.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import de.htwberlin.mae.model.Cart;

@Transactional
@RepositoryRestResource(collectionResourceRel = "carts", path = "carts")
public interface CartRepository extends PagingAndSortingRepository<Cart, UUID> {

	/**
	 * Override default JPA Methods to enable Spring Caching
	 */
	@Override
	@Cacheable(value = "cartCache")
	Cart findOne(UUID id);

	@Override
	@Cacheable(value = "cartCache")
	Iterable<Cart> findAll();

	@Override
	@Cacheable(value = "cartCache")
	Page<Cart> findAll(Pageable pageable);

	@Override
	@Cacheable(value = "cartCache")
	Iterable<Cart> findAll(Iterable<UUID> ids);

	@Override
	@CacheEvict(value = "cartCache", allEntries=true)
	<S extends Cart> S save(S entity);

	@Override
	@CacheEvict(value = "cartCache", allEntries=true)
	void delete(UUID id);
	
	
	@RestResource(path="quantity", rel="quantity")
	public List<Cart> findByQuantity(@Param("value") Integer quantity);
	
	@RestResource(path="quantity-higher", rel="quantity-higher")
	public List<Cart> findByQuantityGreaterThan(@Param("value") Integer quantity);
}
