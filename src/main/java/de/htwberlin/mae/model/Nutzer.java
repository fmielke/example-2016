package de.htwberlin.mae.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nutzer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long nutzerId;

	private String name;
	
	public Nutzer() {}
	
	public Nutzer(String name) {
		this.name = name;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}

