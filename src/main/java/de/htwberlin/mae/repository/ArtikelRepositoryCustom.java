package de.htwberlin.mae.repository;

import java.util.List;

import de.htwberlin.mae.model.Artikel;

public interface ArtikelRepositoryCustom {
	
	
	public List<Artikel> findWithFilter();
}
