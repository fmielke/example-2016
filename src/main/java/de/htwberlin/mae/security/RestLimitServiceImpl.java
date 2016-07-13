package de.htwberlin.mae.security;

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import de.htwberlin.mae.configuration.RedisConnectionMananger;
import redis.clients.jedis.Jedis;

@Component
public class RestLimitServiceImpl implements RestLimitService{

	Logger log = LogManager.getRootLogger();
	
	private long usage;
	private long limit = 10;
	
	
	//@Autowired
	//private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public void incrementUsage(String key) throws URISyntaxException {
		Calendar now = Calendar.getInstance();
		key = String.valueOf(now.get(Calendar.MINUTE)) + ":" + key;
		//redisTemplate.opsForValue().increment(key, 1);
		//redisTemplate.opsForValue().getOperations().expire(key, 60, TimeUnit.SECONDS);
		Jedis jedis = RedisConnectionMananger.getConnection();
		usage = jedis.incr(key);
		jedis.expire(key, 60);
		jedis.close();
	}

	@Override
	public boolean isValid() {
		if(usage > limit) {
			return false;
		}
		else {
			return true;
		}
	}

}
