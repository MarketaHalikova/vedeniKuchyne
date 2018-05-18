package com.github.marketahalikova.vedenikuchyne.logika;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */
public class Sklad {

	private List<Surovina> seznamSurovinSkladu;
	private Map<String, String> skladJakoString;

	/**
	 * Konstruktor třídy Sklad
	 */
	public Sklad() {
		seznamSurovinSkladu = new ArrayList<>();
		skladJakoString = new HashMap<>();
	}

	public List<Surovina> getSeznamSurovinSkladu() {
		return seznamSurovinSkladu;
	}

	public void setSeznamSurovin(List<Surovina> seznamSurovinSkladu) {
		this.seznamSurovinSkladu = seznamSurovinSkladu;
	}

	public void vlozitSurovinu(Surovina surovina) {
		seznamSurovinSkladu.add(surovina);
	}

	/**
	 * Metoda převádějící seznam surovin na skladu do mapy, jejíž klíčem je název
	 * suroviny a hodnotou řetezec ve formátu "název, množství, jednotka"
	 * 
	 * @return Map<String, String>
	 */
	public Map<String, String> getSkladAsString() {
		skladJakoString.clear();
		for (Surovina surovina : seznamSurovinSkladu) {
			String retezec = surovina.getNazev() + ", " + surovina.getMnozstvi() + ", " + surovina.getJednotka();
			skladJakoString.put(surovina.getNazev(), retezec);
		}

		return skladJakoString;
	}

}
