package de.htwberlin.mae.configuration;

import java.net.URI;
import java.net.URISyntaxException;

import redis.clients.jedis.Jedis;

public class RedisConnectionMananger {

	public static Jedis getConnection() throws URISyntaxException {
		
		if(System.getProperty("ENV_SYSTEM").equalsIgnoreCase("local")){
			Jedis jedis = new Jedis("localhost");
	        return jedis;
		}
		else {
			URI redisURI = new URI(System.getenv("REDIS_URL"));
			Jedis jedis = new Jedis(redisURI);
	        return jedis;
		}
    }
}
