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
public class Article {
	
	@Version // generates E-TAG Header
	private Long version;
	
	@JsonIgnore 
	@LastModifiedDate //generates last-modified header
	private LocalDateTime lastModifiedDate;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private UUID articleId;

	@NotNull(message = "{article.productCode.notnull.message}")
	@Size(min=5, max=10, message="{article.productCode.minmax.message}")
	private String productCode;
	
	@NotNull(message = "{article.label.notnull.message}")
	@Size(min=2, max=255, message="{article.label.minmax.message}")
	private String label;
	
	@NotNull(message = "{article.price.notnull.message}")
	@Min(value = 0, message="{article.price.min.message}")
	private Double price;
	
	@OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
	private List<Cart> carts;
		
	public Article() {}
	
	public Article(String productCode, Double price, String label) {
		this.label = label;
		this.productCode = productCode;
		this.price = price;
	}

	
	public UUID getArticleId() {
		return articleId;
	}
	
	public void setArticleId(UUID articleId) {
		this.articleId = articleId;
	}
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public List<Cart> getCart() {
		return carts;
	}

	public void setCart(List<Cart> carts) {
		this.carts = carts;
	}
		
}
