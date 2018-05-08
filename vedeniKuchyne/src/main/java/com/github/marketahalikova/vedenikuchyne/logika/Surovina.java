package com.github.marketahalikova.vedenikuchyne.logika;

/**
 * 
 * @author Markéta Halíková, Johanna Švugerová, Martin Weisser
 *
 * Třída znázorňující jednotlivou surovinu, uchovávající její název a příslušnou jednotku.
 */
public class Surovina {

	private String nazev;

	private String jednotka;

	/**
	 * Metoda získávající název suroviny.
	 * 
	 * @return řetezec s názvem suroviny
	 */
	public String getNazev() {
		return nazev;
	}

	/**
	 * Metoda stanovující název suroviny.
	 * 
	 * @param nazev Název dané suroviny
	 */
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public String getJednotka() {
		return jednotka;
	}

	public void setJednotka(String jednotka) {
		this.jednotka = jednotka;
	}

}
