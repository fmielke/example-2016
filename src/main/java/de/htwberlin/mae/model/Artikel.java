package de.htwberlin.mae.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Artikel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long artikelId;

	private String produktCode;
	private String bezeichnung;
	private Double preis;
	
	@OneToMany
	private List<Warenkorb> warenkoerbe;
		
	public Artikel() {}
	
	public Artikel(String produktCode, Double preis, String bezeichnung) {
		this.bezeichnung = bezeichnung;
		this.produktCode = produktCode;
		this.preis = preis;
	}

	
	public Long getArtikelId() {
		return artikelId;
	}
	
	public void setArtikelId(Long artikelId) {
		this.artikelId = artikelId;
	}
	
	public String getProduktCode() {
		return produktCode;
	}

	public void setProduktCode(String produktCode) {
		this.produktCode = produktCode;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public Double getPreis() {
		return preis;
	}

	public void setPreis(Double preis) {
		this.preis = preis;
	}
	
	public List<Warenkorb> getWarenkoerbe() {
		return warenkoerbe;
	}

	public void setWarenkoerbe(List<Warenkorb> warenkoerbe) {
		this.warenkoerbe = warenkoerbe;
	}
		
}
