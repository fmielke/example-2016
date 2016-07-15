package de.htwberlin.mae.model;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;


@Projection(name="noproductcode", types={Artikel.class})
public interface ArtikelNoProductCodeProjection {

	String getBezeichnung();
	String getPreis();
	List<Warenkorb> getWarenkorb();
}
