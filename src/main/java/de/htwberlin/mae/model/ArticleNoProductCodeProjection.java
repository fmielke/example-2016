package de.htwberlin.mae.model;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;


/**
 * return only productcode
 * @author marcus
 *
 */
@Projection(name="productcode", types={Article.class})
public interface ArticleNoProductCodeProjection {

	String getProductCode();
}