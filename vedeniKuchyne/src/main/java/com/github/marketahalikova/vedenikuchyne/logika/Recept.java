package com.github.marketahalikova.vedenikuchyne.logika;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída Recept
 * 
 * Tato třída slouží po správu receptů
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */
public class Recept implements Serializable {

	private static final long serialVersionUID = 9066672598984451474L;

	private String nazev;

	private String postup;

	private String kategorie;

	private List < Surovina > seznamSurovinReceptu;

	/**
	 * Konstruktor třídy Recept.
	 * 
	 * @param nazev - název recpetu
	 * @param postup - postup přípravy
	 * @param kategorie - kategorie receptu (předkrm/krm/zákrm)
	 */
	public Recept(String nazev, String postup, String kategorie, List < Surovina > seznamSurovinReceptu) {
		this.nazev = nazev;
		this.postup = postup;
		this.kategorie = kategorie;
		this.seznamSurovinReceptu = seznamSurovinReceptu;
	}

	/**
	 * Metoda získává název receptu
	 */
	public String getNazev() {
		return nazev;
	}

	/**
	 * Metoda nastavuje název receptu
	 */
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	/**
	 * Metoda vrací postup receptu
	 */
	public String getPostup() {
		return postup;
	}

	/**
	 * Metoda nastavuje postup receptu
	 */
	public void setPostup(String postup) {
		this.postup = postup;
	}

	/**
	 * Metoda vrací kategorii receptu
	 */
	public String getKategorie() {
		return kategorie;
	}

	/**
	 * Metoda nastavuje kategorii receptu
	 */
	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	/**
	 * Metoda nastavuje seznam surovin receptu
	 */
	public void setSeznamSurovinReceptu(List < Surovina > seznamSurovinReceptu) {
		this.seznamSurovinReceptu = seznamSurovinReceptu;
	}

	/**
	 * Metoda nastavuje novou surovinu receptu
	 */
	public void setNovouSurovinuReceptu(Surovina surovina) {
		this.seznamSurovinReceptu.add(surovina);
	}

	/**
	 * Metoda vrací seznam surovin receptu
	 */
	public List < Surovina > getSeznamSurovinReceptu() {
		return seznamSurovinReceptu;
	}

	/**
	 * Metoda vrací seznam názvů surovin v receptu.
	 * 
	 * @return List<String>
	 */
	public List < String > getSeznamJakoString() {
		List < String > seznam = new ArrayList < >();
		for (Surovina surovina: seznamSurovinReceptu) {
			seznam.add(surovina.getNazev() + ", " + surovina.getMnozstvi() + ", " + surovina.getJednotka());
		}
		return seznam;
	}

}