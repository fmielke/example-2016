package de.htwberlin.mae.model;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;


/**
 * return only produktcode
 * @author marcus
 *
 */
@Projection(name="produktcode", types={Artikel.class})
public interface ArtikelNoProduktCodeProjection {

	String getProduktCode();
}