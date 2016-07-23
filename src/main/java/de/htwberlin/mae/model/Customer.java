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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer {
	
	@Version // generates E-TAG Header
	private Long version;
	
	@JsonIgnore 
	@LastModifiedDate //generates last-modified header
	private LocalDateTime lastModifiedDate;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private UUID customerId;

	@NotNull(message = "{customer.name.notnull.message}")
	@Size(min=2, max=255, message = "{customer.name.minmax.message}")
	private String name;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	private List<Cart> carts;
	
	public Customer() {}
	
	public Customer(String name) {
		this.name = name;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

	public List<Cart> getCart() {
		return carts;
	}

	public void setCart(List<Cart> carts) {
		this.carts = carts;
	}
	
}

