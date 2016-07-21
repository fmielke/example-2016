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

import de.htwberlin.mae.model.Article;

@Transactional
@RepositoryRestResource(collectionResourceRel = "articles", path = "articles")
public interface ArticleRepository extends PagingAndSortingRepository<Article, UUID>{

	/**
	 * Override default JPA Methods to enable Spring Caching
	 */
	
	//@Override
	@Cacheable(value = "articleCache")
	Article findOne(UUID id);

	@Override
	@Cacheable(value = "articleCache")
	Iterable<Article> findAll();

	@Override
	@Cacheable(value = "articleCache")
	Page<Article> findAll(Pageable pageable);
	
	//@Override
	@Cacheable(value = "articleCache")
	Iterable<Article> findAll(Iterable<UUID> ids);
	
	@Override
	@CacheEvict(cacheNames="articleCache", allEntries=true)
	<S extends Article> S save(S entity);

	@Override
	@CacheEvict(value = "articleCache", allEntries=true)
	void delete(UUID id);
	
	
	/**
	 * adding custom search methods -> under /search resource
	 */
	@RestResource(path="label", rel="label")
	public List<Article> findByLabelIgnoreCase(@Param("value") String label);
	
	@RestResource(path="price", rel="price")
	public List<Article> findByPrice(@Param("value") Double price);
	
	@RestResource(path="price-between", rel="price-between")
	public List<Article> findByPriceBetween(@Param("min") Double pricemin, @Param("max") Double pricemax);
	
	@RestResource(path="price-higher", rel="price-higher")
	public List<Article> findByPriceGreaterThan(@Param("value") Double price);
	
	@RestResource(path="price-lesser", rel="price-lesser")
	public List<Article> findByPriceLessThan(@Param("value") Double price);
}
