package com.github.marketahalikova.vedenikuchyne.logika;

import java.util.Observable;

/**
 * Class Kuchyne
 * 
 * 
 *
 * @author Markéta Halíková, Johanna Švugerová, Martin Weisser
 * @version
 */
public class Kuchyne extends Observable {

	public Menu menu;
	public NakupniSeznam nakupniSeznam;
	public Sklad sklad;
	public SeznamReceptu seznamReceptu;

	public Kuchyne() {

		this.menu = new Menu();
		this.nakupniSeznam = new NakupniSeznam();
		this.sklad = new Sklad();
		this.seznamReceptu = new SeznamReceptu();

		naplneniDaty();
	}

	private void naplneniDaty() {

		// tady bude naplneni provizornimi daty
		seznamReceptu.vlozitRecept(new Recept("Svíčková", "Uvař svíčkovou.", "krm"));
		seznamReceptu.vlozitRecept(new Recept("Rajská polévka", "Udělej rajskou.", "predkrm"));
		seznamReceptu.vlozitRecept(new Recept("Tiramisu", "Udělej tiramisu.", "zakrm"));

		sklad.vlozitSurovinu(new Surovina("jablko", "kg", 3));
		sklad.vlozitSurovinu(new Surovina("marijanka", "g", 20));
		sklad.vlozitSurovinu(new Surovina("vodka", "l", 10));
	}

	/**
	 * Metoda vrací aktuální nákupní seznam
	 * 
	 * @return NakupniSeznam
	 */
	public NakupniSeznam getAktualniNakupniSeznam() {
		return nakupniSeznam;
	}

	/**
	 * Metoda vrací aktuální stav skladu
	 * 
	 * @return Sklad
	 */
	public Sklad getAktualniSklad() {
		return sklad;
	}

	/**
	 * Metoda vrací aktuální menu
	 * 
	 * @return Menu
	 */
	public Menu getAktualniMenu() {
		return menu;
	}

	/**
	 * Metoda vrací aktuální seznam všech receptů
	 * 
	 * @return seznamReceptů
	 */
	public SeznamReceptu getAktualniSeznamReceptu() {
		return seznamReceptu;
	}

}
