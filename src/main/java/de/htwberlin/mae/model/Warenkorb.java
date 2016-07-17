package de.htwberlin.mae.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Warenkorb {
	
	@Version // generates E-TAG Header
	private Long version;
	
	@JsonIgnore
	@LastModifiedDate //generates last-modified header
	private LocalDateTime lastModifiedDate;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private UUID warenkorbId;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "artikel_id")
	private Artikel artikel;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "nutzer_id")
	private Nutzer nutzer;
	
	@NotNull(message = "{warenkorb.anzahl.notnull.message}")
	@Min(value = 1, message = "{warenkorb.anzahl.min.message}")
	public Integer anzahl;

	public Warenkorb() {}
	
	public Warenkorb(Nutzer nutzer, Artikel artikel, Integer anzahl){
		this.nutzer = nutzer;
		this.artikel = artikel;
		this.anzahl = anzahl;
	}
	
	public UUID getWarenkorbId() {
		return warenkorbId;
	}

	public void setWarenkorbId(UUID warenkorbId) {
		this.warenkorbId = warenkorbId;
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
