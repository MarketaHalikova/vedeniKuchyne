package com.github.marketahalikova.vedenikuchyne.logika;

import java.util.List;

/**
 * 
 * @author Markéta Halíková, Johanna Švugorevá, Martin Weisser
 *
 */
public class Menu {

	private String datum;
	
	private List<Recept> seznamReceptuMenu;

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public List<Recept> getSeznamReceptu() {
		return seznamReceptuMenu;
	}

	public void setSeznamReceptu(List<Recept> seznamReceptuMenu) {
		this.seznamReceptuMenu = seznamReceptuMenu;
	}
	
	public void vlozitRecept(Recept recept){
		seznamReceptuMenu.add(recept);
	}

}
