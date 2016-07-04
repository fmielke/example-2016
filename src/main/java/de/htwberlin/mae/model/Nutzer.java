package de.htwberlin.mae.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Nutzer {
	
	@Version // generates E-TAG Header
	private Long version;
	
	@JsonIgnore 
	@LastModifiedDate //generates last-modified header
	private LocalDateTime lastModifiedDate;
	


	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private UUID nutzerId;

	private String name;
	
	@OneToMany(mappedBy = "nutzer")
	private List<Warenkorb> warenkorb;
	
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

	public UUID getNutzerId() {
		return nutzerId;
	}

	public void setNutzerId(UUID nutzerId) {
		this.nutzerId = nutzerId;
	}
	
	public List<Warenkorb> getWarenkorb() {
		return warenkorb;
	}

	public void setWarenkorb(List<Warenkorb> warenkorb) {
		this.warenkorb = warenkorb;
	}
	
}

