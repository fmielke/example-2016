package de.htwberlin.mae.security;

import java.net.URISyntaxException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

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
	private long limitGET = 10;
	private long limitPOST = 5;
	private long limitPUT = 5;
	private long limitDELETE = 5;
	
	
	//@Autowired
	//private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public void incrementUsage(String key, HttpServletRequest req) throws URISyntaxException {
		Calendar now = Calendar.getInstance();
		key = String.valueOf(now.get(Calendar.MINUTE)) + ":" + req.getMethod() + "_" + key;
		
		//redisTemplate.opsForValue().increment(key, 1);
		//redisTemplate.opsForValue().getOperations().expire(key, 60, TimeUnit.SECONDS);
		Jedis jedis = RedisConnectionMananger.getConnection();
		usage = jedis.incr(key);
		jedis.expire(key, 60);
		jedis.close();
	}

	@Override
	public boolean isValid(String requestMethod) {
		if(usage > limitGET && requestMethod.equalsIgnoreCase("GET")) {
			return false;
		}
		if(usage > limitPOST  && requestMethod.equalsIgnoreCase("POST")){
			return false;
		}
		if(usage > limitPUT && requestMethod.equalsIgnoreCase("PUT")){
			return false;
		}
		if(usage > limitDELETE && requestMethod.equalsIgnoreCase("DELETE")){
			return false;
		}
		return true;
	}

}
