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
public class Cart {
	
	@Version // generates E-TAG Header
	private Long version;
	
	@JsonIgnore
	@LastModifiedDate //generates last-modified header
	private LocalDateTime lastModifiedDate;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private UUID cartId;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "article_id")
	private Article article;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@NotNull(message = "{cart.quantity.notnull.message}")
	@Min(value = 1, message = "{cart.quantity.min.message}")
	public Integer quantity;

	public Cart() {}
	
	public Cart(Customer customer, Article artikel, Integer quantity){
		this.customer = customer;
		this.article = artikel;
		this.quantity = quantity;
	}
	
	public UUID getCartId() {
		return cartId;
	}

	public void setCartId(UUID cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Article getArticle() {
		return article;
	}

	public void setArtcle(Article article) {
		this.article = article;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return this.customer.getName() + " | " + this.article.getLabel() + " | " + this.getQuantity();
	}
}
