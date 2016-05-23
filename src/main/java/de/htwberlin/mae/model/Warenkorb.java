package de.htwberlin.mae.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Warenkorb {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long warenkorbId;
	
	private Integer anzahl;

	public Integer getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(Integer anzahl) {
		this.anzahl = anzahl;
	}
	
	
	
}
