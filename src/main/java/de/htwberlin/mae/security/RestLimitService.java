package de.htwberlin.mae.security;

import java.net.URISyntaxException;

import redis.clients.jedis.Jedis;

public interface RestLimitService {
		
	public boolean isValid();
		
	public void incrementUsage(String key) throws URISyntaxException;

}
