package de.htwberlin.mae.model;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;


@Projection(name="produktcode", types={Artikel.class})
public interface ArtikelNoProductCodeProjection {

	String getProduktCode();
	//String getBezeichnung();
	//String getPreis();
	//List<Warenkorb> getWarenkorb();
}