package com.github.marketahalikova.vedenikuchyne.logika;

import java.util.List;

/**
 * Class SeznamRceptu eviduje seznam všech receptů
 * 
 * @author Markéta Halíková, Johanna Švugerová, Martin Weisser
 *
 */
public class SeznamReceptu {

	
	private List<Recept> seznamReceptu;


	/**
	 * Metoda vrací seznam všech receptů
	 * 
	 * @return List<Recept>
	 */
	public List<Recept> getSeznamReceptu() {
		return seznamReceptu;
	}
	
	/**
	 * Metoda nahrazuje seznam všech receptů seznamem z parametru
	 * 
	 * @param List<Recept>
	 */
	public void setSeznamReceptu(List<Recept> seznamReceptu) {
		this.seznamReceptu = seznamReceptu;
	}
	
	/**
	 * Metoda vkládá recept z parametru do seznamu receptů
	 * 
	 * @param Recept
	 */
	public void vlozitRecept(Recept recept){
		seznamReceptu.add(recept);
	}
	
}
