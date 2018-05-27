package com.github.marketahalikova.vedenikuchyne.logika;

import java.io.Serializable;

/**
 * Třída Surovina
 * 
 * Třída znázorňující jednotlivou surovinu, uchovávající její název a
 * příslušnou jednotku.
 * 
 * @author Markéta Halíková, Johanna Švugerová, Martin Weisser
 *
 */
public class Surovina implements Serializable {

	private static final long serialVersionUID = -914687629813799760L;
	private String nazev;
	private Jednotka jednotka;
	private double mnozstvi;

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
	public Surovina(String nazev, Jednotka jednotka, double mnozstvi) {
		this.nazev = nazev;
		this.jednotka = jednotka;
		this.setMnozstvi(mnozstvi);
	}

	/**
	 * Metoda definujicí enumerace Jednotka
	 */
	public enum Jednotka {
		kg,
		l,
		ks,
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
	public double getMnozstvi() {
		return mnozstvi;
	}

	/**
	 * Metoda nastavující množství dle daného parametru
	 * 
	 * @param mnozstvi
	 */
	public void setMnozstvi(double mnozstvi) {
		this.mnozstvi = mnozstvi;
	}

	/**
	 * Metoda přepisuje funkci equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) 
			return true;
		if (obj == null) 
			return false;
		if (getClass() != obj.getClass()) 
			return false;
		Surovina other = (Surovina) obj;
		if (jednotka != other.jednotka) 
			return false;
		if (nazev == null) {
			if (other.nazev != null) 
				return false;
		} else if (!nazev.equals(other.nazev)) 
			return false;
		return true;
	}

}