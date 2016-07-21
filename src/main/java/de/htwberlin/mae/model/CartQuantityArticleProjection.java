package de.htwberlin.mae.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * return anzahl, artikel.bezeichnung
 * @author marcus
 *
 */
@Projection(name="quantity-articlelabel", types={Cart.class})
public interface CartQuantityArticleProjection {
	
	Integer getQuantity();
	
	@Value("#{target.article.label}")
	String getLabel();
}
