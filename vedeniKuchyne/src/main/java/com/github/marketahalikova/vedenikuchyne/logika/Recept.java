package com.github.marketahalikova.vedenikuchyne.logika;

import java.util.List;

/**
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */
public class Recept {

	private String nazev;

	private String postup;

	private String kategorie;
	
	private List<Surovina> seznamSurovinReceptu;
	/**
	 * Konstruktor metody Recept.
	 * 
	 * @param nazev - název recpetu
	 * @param postup - postup přípravy
	 * @param kategorie - kategorie receptu (předkrm/krm/zákrm)
	 */
	public Recept(String nazev, String postup, String kategorie, List<Surovina> seznamSurovinReceptu){
		this.nazev = nazev;
		this.postup = postup;
		this.kategorie = kategorie;
		this.seznamSurovinReceptu = seznamSurovinReceptu;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public String getPostup() {
		return postup;
	}

	public void setPostup(String postup) {
		this.postup = postup;
	}

	public String getKategorie() {
		return kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

}
