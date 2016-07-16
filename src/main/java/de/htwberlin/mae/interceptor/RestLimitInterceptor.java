/**
 * @author marcus
 * @description This Class is an interceptor for http requests
 */
package de.htwberlin.mae.interceptor;

import de.htwberlin.mae.security.RestLimitServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class RestLimitInterceptor implements HandlerInterceptor {

	Logger log = LogManager.getRootLogger();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, URISyntaxException  {
		
		//use this to enable hal browser
		if(request.getRequestURI().contains("/browser/")){
			return true;
		}
		
		if(request.getHeader("Authorization") != null){
			String key = (request.getHeader("Authorization").contains("Bearer")) ? request.getHeader("Authorization").split("\\.")[1] : "noauth";
			//log.info("KEY: " +  key);
			//check if user does not exceeds limits of X per minute
			RestLimitServiceImpl restLimitService = new RestLimitServiceImpl();
			restLimitService.incrementUsage(key, request);
			if(restLimitService.isValid(request.getMethod())){
				return true;
			}
			else{
				log.warn("client with key " +key +" reached rate limit");
				response.sendError(429, "Rate limit exceeded. Please Upgrade your Plan from Free to Pro.");
				return false;
			}
		}
		else{
			return true;
			
			//response.sendError(429, "Rate limit exceeded. Please Upgrade your Plan from Free to Pro.");
			//return false;
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
