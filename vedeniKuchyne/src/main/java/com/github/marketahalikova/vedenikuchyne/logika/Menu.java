package com.github.marketahalikova.vedenikuchyne.logika;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * 
 * @author Markéta Halíková, Johanna Švugorevá, Martin Weisser
 *
 */
public class Menu extends Observable{

	private String datum;
	
	private List<Recept> seznamReceptuMenu;
	private List<String> seznamNazvuReceptu;

	public Menu() {
		seznamReceptuMenu = new ArrayList<>();
		seznamNazvuReceptu = new ArrayList<>();
	}
	
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
	
	public List<String> getNazvyReceptu() {
		seznamNazvuReceptu.clear();
		for (Recept recept : seznamReceptuMenu) {
			seznamNazvuReceptu.add(recept.getNazev());
		}

		return seznamNazvuReceptu;
	}

}
