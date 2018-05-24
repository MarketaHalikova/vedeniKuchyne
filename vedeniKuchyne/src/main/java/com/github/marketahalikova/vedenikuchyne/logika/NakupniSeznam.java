package com.github.marketahalikova.vedenikuchyne.logika;

import java.util.ArrayList;
import java.util.List;


/**
 *  Třída reprezentující list surovin, které jsou potřeba nakoupit.
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */
public class NakupniSeznam {
	
	private List<Surovina> seznamSurovinNakupu;

	/** 
	 * Metoda vrací nákupní seznam.
	 * @return list s nákupním seznamem surovin
	 */
	public List<Surovina> getSeznamSurovinNakupu() {
		return seznamSurovinNakupu;
	}
	
	/**
	 * Metoda nastaví požadovaný nákpuní seznam.
	 * @param seznamSurovinNakupu - list se surovinami 
	 */
	public void setSeznamSurovin(List<Surovina> seznamSurovinNakupu) {
		this.seznamSurovinNakupu = seznamSurovinNakupu;
	}

	/**
	 * Metoda pro vložení suroviny do seznamu
	 * 
	 * @param surovina
	 */
	public void vlozitSurovinu(Surovina surovina){
		seznamSurovinNakupu.add(surovina);
	}
	
	public List<String> nakupniSeznamToString(){
		List<String> nakupniSeznamToString = new ArrayList<>();
		for (Surovina surovina : seznamSurovinNakupu) {
			nakupniSeznamToString.add(surovina.getNazev() + " " + surovina.getMnozstvi() + surovina.getJednotka());
		}
		return nakupniSeznamToString;
	}
}
