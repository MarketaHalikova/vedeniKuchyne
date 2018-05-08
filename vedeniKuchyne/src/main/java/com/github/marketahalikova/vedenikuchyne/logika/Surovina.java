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
	 * @param nazev název dané suroviny
	 */
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	/**
	 * Metoda vracící jednotku dané suroviny.
	 * 
	 * @return řetezec se jménem jednotky
	 */
	public String getJednotka() {
		return jednotka;
	}

	/**
	 * Metoda nastavující jednotku dané suroviny.
	 * 
	 * @param jednotka řetezec se jménem suroviny
	 */
	public void setJednotka(String jednotka) {
		this.jednotka = jednotka;
	}

}
