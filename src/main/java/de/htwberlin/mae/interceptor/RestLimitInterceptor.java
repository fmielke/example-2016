/**
 * @author marcus
 * @description This Class is an interceptor for http requests
 */
package de.htwberlin.mae.interceptor;

import de.htwberlin.mae.security.RestLimitServiceImpl;
import de.htwberlin.mae.slackapi.SlackWebhookImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${logentries.token}")
	private String logentriesToken;
	
	private String rateLimitExceededMessage = "Rate limit exceeded. Please Upgrade your Plan from Free to Pro.";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, URISyntaxException  {

		
		//TODO das müsste eigentlich irgendwie anders gelöst werden.
		//use this to enable hal browser
		if(request.getRequestURI().contains("/browser/")){
			return true;
		}
		
		
		if(request.getHeader("Authorization") != null){
			String key = (request.getHeader("Authorization").contains("Bearer")) ? request.getHeader("Authorization").split("\\.")[1] : "noauth";
			RestLimitServiceImpl restLimitService = new RestLimitServiceImpl();
			restLimitService.incrementUsage(key, request);
			if(restLimitService.isValid(request.getMethod())){
				return true;
			}
			else{
				//log.warn("client with key " +key +" reached rate limit");
				SlackWebhookImpl webhook = new SlackWebhookImpl();
				webhook.createSlackMessage(":trollface:", "interceptor", "client with key '" +key +"' reached rate limit");
				webhook.sendRestLimitHook();
				response.sendError(429, rateLimitExceededMessage);
				return false;
			}
		}
		else{
			//wenn Header keine auth hat wird von Spring Security geblockt...
			return true;
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
