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

		Cache artikelCache = new ConcurrentMapCache("artikelCache");
		Cache nutzerCache = new ConcurrentMapCache("nutzerCache");
		Cache warenkorbCache = new ConcurrentMapCache("warenkorbCache");

		SimpleCacheManager manager = new SimpleCacheManager();
		manager.setCaches(Arrays.asList(
				artikelCache, 
				nutzerCache,
				warenkorbCache
				));

		return manager;
	}
}
