package de.htwberlin.mae.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.htwberlin.mae.model.ErrorInformation;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Logger log = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	String timestamp = DateTime.now().toString();
    	String type = "about:blank";
    	String title = authenticationException.getLocalizedMessage();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String detail = authenticationException.toString();
		String instance = request.getRequestURI();
		
		ErrorInformation errorInformation = new ErrorInformation(timestamp, type, title, status, detail, instance);
	    response.setContentType("application/problem+json");
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    response.getOutputStream().println(mapper.writeValueAsString(errorInformation));
    }
}

