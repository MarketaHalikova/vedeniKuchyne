package com.github.marketahalikova.vedenikuchyne.logika;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.github.marketahalikova.vedenikuchyne.logika.Surovina.Jednotka;

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
		
		Surovina knedlik = new Surovina("knedlík", Jednotka.ks, 3);
		Surovina marijanka = new Surovina("marijanka", Jednotka.g, 20);
		Surovina mrkev = new Surovina("mrkev", Jednotka.ks, 3);
		List<Surovina> seznamSurovinReceptu = new ArrayList<>();
		seznamSurovinReceptu.add(knedlik);
		seznamSurovinReceptu.add(marijanka);
		seznamSurovinReceptu.add(mrkev);
		
		seznamReceptu.vlozitRecept(new Recept("Svíčková", "Uvař svíčkovou.", "krm", seznamSurovinReceptu));
		seznamReceptu.vlozitRecept(new Recept("Rajská polévka", "Udělej rajskou.", "predkrm", seznamSurovinReceptu));
		seznamReceptu.vlozitRecept(new Recept("Tiramisu", "Udělej tiramisu.", "zakrm", seznamSurovinReceptu));


		sklad.vlozitSurovinu(new Surovina("jablko", Jednotka.kg, 3));
		sklad.vlozitSurovinu(new Surovina("marijanka", Jednotka.g, 20));
		sklad.vlozitSurovinu(new Surovina("vodka", Jednotka.l, 10));
		
		menu.vlozitRecept(new Recept("Sushi", "Zabij rybu, uvař rýži.", "krm", seznamSurovinReceptu ));
		menu.vlozitRecept(new Recept("Losos", "Zabij rybu.", "krm", seznamSurovinReceptu ));

		
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
