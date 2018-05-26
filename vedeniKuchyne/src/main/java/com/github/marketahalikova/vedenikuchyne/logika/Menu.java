package com.github.marketahalikova.vedenikuchyne.logika;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * 
 * @author Markéta Halíková, Johanna Švugorevá, Martin Weisser
 *
 */
public class Menu extends Observable /*implements Serializable*/ {

	
	//private static final long serialVersionUID = 1L;
	private List<Recept> seznamReceptuMenu;
	private List<String> seznamNazvuReceptu;
	public List<Surovina> seznamVsechSurovinMenu;

	public Menu() {
		seznamReceptuMenu = new ArrayList<>();
		seznamNazvuReceptu = new ArrayList<>();
		seznamVsechSurovinMenu = new ArrayList<>();
	}

	public List<Recept> getSeznamReceptu() {
		return seznamReceptuMenu;
	}

	public void setSeznamReceptu(List<Recept> seznamReceptuMenu) {
		this.seznamReceptuMenu = seznamReceptuMenu;
	}

	public void vlozitRecept(Recept recept) {
		seznamReceptuMenu.add(recept);
	}

	public List<String> getNazvyReceptu() {
		seznamNazvuReceptu.clear();
		for (Recept recept : seznamReceptuMenu) {
			seznamNazvuReceptu.add(recept.getNazev());
		}

		return seznamNazvuReceptu;
	}

	/**
	 * Metoda odstraňující recept zadaný parametrem ze seznamu receptů v menu
	 * 
	 * @param Recept
	 */
	public void odstranRecept(Recept recept) {
		seznamReceptuMenu.remove(recept);
		setChanged();
		notifyObservers();
	}

	public Recept najdiRecept(String hledanyRetezec) {
		Recept hledany = null;

		int len = seznamReceptuMenu.size();
		for (int i = 0; i < len; i++) {
			if (seznamReceptuMenu.get(i).getNazev().equals(hledanyRetezec)) {
				hledany = seznamReceptuMenu.get(i);
			}
		}
		return hledany;
	}

	/**
	 * Metoda vytvoří seznam všech surovin z receptů ze menu ten následně upraví
	 * tak, že suroviny, které mají stejný název a jednotku spojí v jedu tím že
	 * sečte jejich množství a v novém setříděném seznamu bude vedena jako jedna
	 * surovina suroviny, které mají stejný název ale jinou jednotku dá na nová
	 * seznam tak jak jsou vrací takto setříděný nový seznam
	 * 
	 * @retun List<Surovina>
	 */
	public List<Surovina> vytvoreniSeznamuSurovinMenu() {
		seznamVsechSurovinMenu.clear();
		List<Surovina> seznamSurovinMenuNesetrideny = new ArrayList<>();

		// Vytvoření nesetříděného seznamu
		for (Recept recept : seznamReceptuMenu) {
			for (Surovina surovinaReceptu : recept.getSeznamSurovinReceptu()) {
				seznamSurovinMenuNesetrideny.add(surovinaReceptu);
			}
		}


		// Vytvoření setříděného seznamu
		for (Surovina surovinaMenuNetridena : seznamSurovinMenuNesetrideny) {
			if (seznamVsechSurovinMenu.contains(surovinaMenuNetridena)) {
				Surovina sur = seznamVsechSurovinMenu.get(seznamVsechSurovinMenu.indexOf(surovinaMenuNetridena));
				sur.setMnozstvi(round(sur.getMnozstvi() + surovinaMenuNetridena.getMnozstvi(), 1));
			} else {
				seznamVsechSurovinMenu.add(surovinaMenuNetridena);
			}
		}

		return seznamVsechSurovinMenu;

	}

	/**
	 * Metoda zaokrouhlí double z parametru
	 * na počet desetiných míst z precision v parametru
	 * 
	 * @param double, int
	 * @return double
	 */
	private static double round(double value, int precision) {
		int scale = (int) Math.pow(10, precision);
		return (double) Math.round(value * scale) / scale;
	}

}
