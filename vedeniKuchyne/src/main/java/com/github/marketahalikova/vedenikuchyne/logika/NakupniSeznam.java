package com.github.marketahalikova.vedenikuchyne.logika;

import java.util.List;


/**
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */
public class NakupniSeznam {
	
	private List<Surovina> seznamSurovinNakupu;


	public List<Surovina> getSeznamSurovinNakupu() {
		return seznamSurovinNakupu;
	}
	
	public void setSeznamSurovin(List<Surovina> seznamSurovinNakupu) {
		this.seznamSurovinNakupu = seznamSurovinNakupu;
	}

	public void vlozitSurovinu(Surovina surovina){
		seznamSurovinNakupu.add(surovina);
	}
}
