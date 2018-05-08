package com.github.marketahalikova.vedenikuchyne.logika;

import java.util.List;

/**
 * 
 * @author Markéta Halíková, Johanna Švugorevá, Martin Weisser
 *
 */
public class Menu {

	private String datum;
	
	private List<Recept> seznamReceptu;

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public List<Recept> getSeznamReceptu() {
		return seznamReceptu;
	}

	public void setSeznamReceptu(List<Recept> seznamReceptu) {
		this.seznamReceptu = seznamReceptu;
	}

}
