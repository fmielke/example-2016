package de.htwberlin.mae.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Warenkorb {
	
	@Version // generates E-TAG Header
	private Long version;
	
	@JsonIgnore 
	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long warenkorbId;
	
	@ManyToOne
	@JoinColumn(name = "artikel_id")
	private Artikel artikel;
	
	@ManyToOne
	@JoinColumn(name = "nutzer_id")
	private Nutzer nutzer;
	
	private Integer anzahl;

	public Warenkorb() {}
	
	public Warenkorb(Nutzer nutzer, Artikel artikel, Integer anzahl){
		this.nutzer = nutzer;
		this.artikel = artikel;
		this.anzahl = anzahl;
	}
		
	public Nutzer getNutzer() {
		return nutzer;
	}

	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}
	
	public Artikel getArtikel() {
		return artikel;
	}

	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

	public Integer getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(Integer anzahl) {
		this.anzahl = anzahl;
	}
	
	@Override
	public String toString() {
		return this.nutzer.getName() + " | " + this.artikel.getBezeichnung() + " | " + this.getAnzahl();
	}
}
