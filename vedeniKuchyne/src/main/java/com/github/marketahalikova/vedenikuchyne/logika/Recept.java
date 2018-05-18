package com.github.marketahalikova.vedenikuchyne.logika;

/**
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */
public class Recept {

	private String nazev;

	private String postup;

	private String kategorie;
	/**
	 * Konstruktor metody Recept.
	 * 
	 * @param nazev - název recpetu
	 * @param postup - postup přípravy
	 * @param kategorie - kategorie receptu (předkrm/krm/zákrm)
	 */
	public Recept(String nazev, String postup, String kategorie){
		this.nazev = nazev;
		this.postup = postup;
		this.kategorie = kategorie;
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
