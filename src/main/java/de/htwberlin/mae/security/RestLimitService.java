package de.htwberlin.mae.security;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

public interface RestLimitService {
		
	public boolean isValid(String requestMethod);
		
	public void incrementUsage(String key, HttpServletRequest req) throws URISyntaxException;

}
