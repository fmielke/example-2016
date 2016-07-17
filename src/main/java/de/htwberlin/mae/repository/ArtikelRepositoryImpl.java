package de.htwberlin.mae.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwberlin.mae.model.Artikel;

public class ArtikelRepositoryImpl implements ArtikelRepositoryCustom {

	Logger log = LogManager.getRootLogger();
	
	
	public List<Artikel> findWithFilter() {
		List<Artikel> artikel = new ArrayList<Artikel>();
		log.info("FILTER: "/* + filter*/);
		return artikel;
	}

}
