/**
 * @author marcus
 * @description This Class is an interceptor for http requests
 */
package de.htwberlin.mae.interceptor;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import de.htwberlin.mae.security.RestLimitServiceImpl;

@Component
public class RestLimitInterceptor implements HandlerInterceptor {
	
	Logger log = LogManager.getRootLogger();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, URISyntaxException  {

		if(request.getHeader("Authorization") != null){
			String key = (request.getHeader("Authorization").contains("Bearer")) ? request.getHeader("Authorization").split("\\.")[1] : "noauth";
			//log.info("KEY: " +  key);
			//check if user does not exceeds limits of 20 per minute
			RestLimitServiceImpl restLimitService = new RestLimitServiceImpl();
			restLimitService.incrementUsage(key);
			if(restLimitService.isValid()){
				return true;
			}
			else{
				response.sendError(429, "Rate limit exceeded. Please Upgrade your Plan from Free to Pro.");
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	@Override
	public void postHandle(	HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//method exected
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		//request complete
	}
}
