package com.github.marketahalikova.vedenikuchyne.logika;

import java.util.Observable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class SeznamRceptu eviduje seznam všech receptů
 * 
 * @author Markéta Halíková, Johanna Švugerová, Martin Weisser
 *
 */
public class SeznamReceptu extends Observable implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private List<Recept> seznamReceptu;
	private List<String> seznamKategorie;

	/**
	 * Konstruktor třídy SeznamRecpetu
	 */
	public SeznamReceptu() {
		seznamReceptu = new ArrayList<>();
		seznamKategorie = new ArrayList<>();
	}

	/**
	 * Metoda vrací seznam všech receptů
	 * 
	 * @return List<Recept>
	 */
	public List<Recept> getSeznamReceptu() {
		return seznamReceptu;
	}

	/**
	 * Metoda nahrazuje seznam všech receptů seznamem z parametru
	 * 
	 * @param List<Recept>
	 */
	public void setSeznamReceptu(List<Recept> seznamReceptu) {
		this.seznamReceptu = seznamReceptu;
	}

	/**
	 * Metoda vkládá recept z parametru do seznamu receptů
	 * 
	 * @param Recept
	 */
	public void vlozitRecept(Recept recept) {
		seznamReceptu.add(recept);
		setChanged();
		notifyObservers();
	}
	
	/** 
	 * Metoda najde recept dle zadaného názvu.
	 * @param nazev
	 */
	public Recept najdiRecept(String nazev) {
		Recept hledany = null;
		for(Recept recept : seznamReceptu) {
			if(recept.getNazev().equals(nazev))
				hledany = recept;
		}
		
		return hledany;

	}

	/**
	 * 
	 * Metoda vracící seznam receptů dle kategorie z parametru
	 * 
	 * @param kategorie
	 * @return List<Recept>
	 */
	public List<String> getPodleKategorie(String kategorie) {
		seznamKategorie.clear();
		for (Recept recept : seznamReceptu) {
			if (recept.getKategorie().equals(kategorie)) {
				seznamKategorie.add(recept.getNazev());
			}
		}

		return seznamKategorie;
	}

}
