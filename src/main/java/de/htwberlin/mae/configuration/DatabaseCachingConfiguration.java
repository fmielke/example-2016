package de.htwberlin.mae.configuration;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@EnableAutoConfiguration
class DatabaseCachingConfiguration {

	@Bean
	public CacheManager cacheManager() {

		Cache articleCache = new ConcurrentMapCache("articleCache");
		Cache customerCache = new ConcurrentMapCache("customerCache");
		Cache cartCache = new ConcurrentMapCache("cartCache");

		SimpleCacheManager manager = new SimpleCacheManager();
		manager.setCaches(Arrays.asList(
				articleCache, 
				customerCache,
				cartCache
				));

		return manager;
	}
}
