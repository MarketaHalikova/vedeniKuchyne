package com.github.marketahalikova.vedenikuchyne.logika;

/**
 * 
 * @author Markéta Halíková, Johanna Švugerová, Martin Weisser
 *
 *         Třída znázorňující jednotlivou surovinu, uchovávající její název a
 *         příslušnou jednotku.
 */
public class Surovina {

	private String nazev;
	private Jednotka jednotka;
	private int mnozstvi;

	/**
	 * Konstruktor třídy Surovina bez mnozstvi
	 * 
	 * @param nazev
	 * @param jednotka
	 */
	public Surovina(String nazev, Jednotka jednotka) {
		this.nazev = nazev;
		this.jednotka = jednotka;
	}

	/**
	 * Konstruktor třídy Surovina s mnozstvim
	 * 
	 * @param nazev
	 * @param jednotka
	 * @param mnozstvi
	 */
	public Surovina(String nazev, Jednotka jednotka, int mnozstvi) {
		this.nazev = nazev;
		this.jednotka = jednotka;
		this.setMnozstvi(mnozstvi);
	}

	
	public enum Jednotka {
	    g, kg, l, ml, ks, 
	}
	
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
	 * @param název dané suroviny
	 */
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	/**
	 * Metoda vracící jednotku dané suroviny.
	 * 
	 * @return řetezec se jménem jednotky
	 */
	public Jednotka getJednotka() {
		return jednotka;
	}

	/**
	 * Metoda nastavující jednotku dané suroviny.
	 * 
	 * @param jednotka
	 *            řetezec se jménem suroviny
	 */
	public void setJednotka(Jednotka jednotka) {
		this.jednotka = jednotka;
	}

	/**
	 * Metoda vracící množství dané suroviny
	 * 
	 * @return mnozstvi
	 */
	public int getMnozstvi() {
		return mnozstvi;
	}

	/**
	 * Metoda nastavující množství dle daného parametru
	 * 
	 * @param mnozstvi
	 */
	public void setMnozstvi(int mnozstvi) {
		this.mnozstvi = mnozstvi;
	}

}
