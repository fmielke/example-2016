package de.htwberlin.mae.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Artikel {
	
	@Version // generates E-TAG Header
	private Long version;
	
	@JsonIgnore 
	@LastModifiedDate //generates last-modified header
	private LocalDateTime lastModifiedDate;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private UUID artikelId;

	@NotNull(message = "{artikel.produktCode.notnull.message}")
	@Size(min=5, max=10, message="{artikel.produktCode.minmax.message}")
	private String produktCode;
	
	@NotNull(message = "{artikel.bezeichnung.notnull.message}")
	@Size(min=2, max=255, message="{artikel.bezeichnung.minmax.message}")
	private String bezeichnung;
	
	@NotNull(message = "{artikel.preis.notnull.message}")
	@Min(value = 0, message="{artikel.preis.min.message}")
	private Double preis;
	
	@OneToMany(mappedBy = "artikel", fetch = FetchType.EAGER)
	private List<Warenkorb> warenkorb;
		
	public Artikel() {}
	
	public Artikel(String produktCode, Double preis, String bezeichnung) {
		this.bezeichnung = bezeichnung;
		this.produktCode = produktCode;
		this.preis = preis;
	}

	
	public UUID getArtikelId() {
		return artikelId;
	}
	
	public void setArtikelId(UUID artikelId) {
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
	
	public List<Warenkorb> getWarenkorb() {
		return warenkorb;
	}

	public void setWarenkorb(List<Warenkorb> warenkorb) {
		this.warenkorb = warenkorb;
	}
		
}
