package com.github.marketahalikova.vedenikuchyne.logika;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */
public class Sklad extends Observable implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private List<Surovina> seznamSurovinSkladu;
	private Map<String, Surovina> skladJakoString;

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
	 * Metoda odstraňující surovinu zadanou parametrem ze seznamu skladu
	 * 
	 * @param surovina
	 */
	public void odstranSurovinu(Surovina surovina) {
		seznamSurovinSkladu.remove(surovina);
		setChanged();
		notifyObservers();
	}

	/**
	 * Metoda převádějící seznam surovin na skladu do mapy, jejíž klíčem řetezec ve
	 * formátu "název, množství, jednotka" a hodnotou daná surovina.
	 * 
	 * @return Map<String, String>
	 */
	public Map<String, Surovina> getSkladAsString() {
		skladJakoString.clear();
		for (Surovina surovina : seznamSurovinSkladu) {
			String retezec = surovina.getNazev() + ", " + surovina.getMnozstvi() + ", " + surovina.getJednotka();
			skladJakoString.put(retezec, surovina);
		}

		return skladJakoString;
	}

	/**
	 * Metoda vracející surovinu dle zadaného řetezce z mapy surovin na skaldé
	 * obsahujicí surovinu jako hodnotu a řetězec jako klíč.
	 *
	 * @param hledanyRetezec
	 * @return Surovina
	 */
	public Surovina najdiSurovinu(String hledanyRetezec) {
		Surovina hledana = skladJakoString.get(hledanyRetezec);

		return hledana;
	}

}
