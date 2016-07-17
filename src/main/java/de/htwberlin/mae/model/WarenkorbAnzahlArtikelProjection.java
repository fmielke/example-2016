package de.htwberlin.mae.model;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="anzahl-artikel", types={Warenkorb.class})
public interface WarenkorbAnzahlArtikelProjection {
	
	Integer getAnzahl();
	Artikel getArtikel();
}
