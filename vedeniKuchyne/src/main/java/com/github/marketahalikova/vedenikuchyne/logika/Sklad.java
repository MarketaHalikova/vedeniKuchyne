package com.github.marketahalikova.vedenikuchyne.logika;

import java.util.List;

/**
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */
public class Sklad {

	private List<Surovina> seznamSurovinSkladu;


	public List<Surovina> getSeznamSurovinSkladu() {
	return seznamSurovinSkladu;
}
	
	public void setSeznamSurovin(List<Surovina> seznamSurovinSkladu) {
		this.seznamSurovinSkladu = seznamSurovinSkladu;
	}
	
	public void vlozitSurovinu(Surovina surovina){
		seznamSurovinSkladu.add(surovina);
	}
}
