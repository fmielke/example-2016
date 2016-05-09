package de.htwberlin.mae.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.htwberlin.mae.spring.model.Root;

@RestController
public class RootController {
	
	private final String response = "Willkommen auf dem Web-Server des Projektes \"REST-Leitfaden \" aus dem Kurs der Management der Anwendungsentwicklung der HTW-Berlin.";
	
	@RequestMapping(value="/", method = RequestMethod.GET)
    public Root root() {
        return new Root(response);
    }
}
