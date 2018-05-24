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
		Surovina marijanka = new Surovina("marijanka", Jednotka.kg, 25);
		Surovina mrkev = new Surovina("mrkev", Jednotka.ks, 3);
		List<Surovina> seznamSurovinReceptu = new ArrayList<>();
		seznamSurovinReceptu.add(knedlik);
		seznamSurovinReceptu.add(marijanka);
		seznamSurovinReceptu.add(mrkev);
		
		seznamReceptu.vlozitRecept(new Recept("Svíčková", "Uvař svíčkovou.", "krm", seznamSurovinReceptu));
		seznamReceptu.vlozitRecept(new Recept("Rajská polévka", "Udělej rajskou.", "predkrm", seznamSurovinReceptu));
		seznamReceptu.vlozitRecept(new Recept("Tiramisu", "Udělej tiramisu.", "zakrm", seznamSurovinReceptu));


		sklad.vlozitSurovinu(new Surovina("jablko", Jednotka.kg, 3));
		sklad.vlozitSurovinu(new Surovina("marijanka", Jednotka.kg, 20));
		sklad.vlozitSurovinu(new Surovina("vodka", Jednotka.l, 10));
		
		menu.vlozitRecept(new Recept("Sushi", "Zabij rybu, uvař rýži.", "krm", seznamSurovinReceptu ));
		menu.vlozitRecept(new Recept("Losos", "Zabij rybu.", "krm", seznamSurovinReceptu ));

		//System.out.println(srovnaniSurovinReceptuSeSkladem(new Recept("Svíčková", "Uvař svíčkovou.", "krm", seznamSurovinReceptu)));

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
	
	
	/**
	 * Metoda porovnává setříděn seznam surovin menu se seznamem surovin na skladě
	 * suroviny, které je potřeba dokoupit (případně množství existujících surovin) dá do nákoupního seznamu
	 * 
	 */
	public void srovnaniSurovinMenuSeSkladem() {
		
		nakupniSeznam.setSeznamSurovin(null);
		List<Surovina> provizorniSeznam = new ArrayList<>();


		for (Surovina surovinaMenu : menu.vytvoreniSeznamuSurovinMenu()) {
			if(sklad.getSeznamSurovinSkladu().contains(surovinaMenu)) {
				Surovina sur = sklad.getSeznamSurovinSkladu().get(sklad.getSeznamSurovinSkladu().indexOf(surovinaMenu));
				if(sur.getMnozstvi()<surovinaMenu.getMnozstvi()){
					provizorniSeznam.add(new Surovina(sur.getNazev(), sur.getJednotka(), round(surovinaMenu.getMnozstvi() - sur.getMnozstvi(),1)));
				}
			} else {
				provizorniSeznam.add(surovinaMenu);
			}
		}
		
		nakupniSeznam.setSeznamSurovin(provizorniSeznam);
		
		//System.out.println("nakupni listek: " + nakupniSeznam.nakupniSeznamToString()); // 3 suroviny
																	// suroviny a by melo byt jeden kus;
																	// suroviny f 0.4g;
																	// suroviny d 4ks
	}

	/**
	 * Metoda porovnává seznam surovin receptu se seznamem surovin na skladě
	 * suroviny, které je potřeba pro výrobu receptu dokoupit (případně množství existujících surovin) 
	 * vrátí jako seznam stringů
	 * 
	 * @param Recept
	 * @return List<String>
	 */
	public List<String> srovnaniSurovinReceptuSeSkladem(Recept recept) {
		
		List<Surovina> SurovinyChybejiciNaSklade = new ArrayList<>();
		List<Surovina> provizorniSeznam = new ArrayList<>();


		for (Surovina surovinaMenu : recept.getSeznamSurovinReceptu()) {
			if(sklad.getSeznamSurovinSkladu().contains(surovinaMenu)) {
				Surovina sur = sklad.getSeznamSurovinSkladu().get(sklad.getSeznamSurovinSkladu().indexOf(surovinaMenu));
				if(sur.getMnozstvi()<surovinaMenu.getMnozstvi()){
					provizorniSeznam.add(new Surovina(sur.getNazev(), sur.getJednotka(), round(surovinaMenu.getMnozstvi() - sur.getMnozstvi(),1)));
				}
			} else {
				provizorniSeznam.add(surovinaMenu);
			}
		}
		
		SurovinyChybejiciNaSklade.addAll(provizorniSeznam);
		
		List<String> SurovinyChybejiToString = new ArrayList<>();
		for (Surovina surovina : SurovinyChybejiciNaSklade) {
			SurovinyChybejiToString.add(surovina.getNazev() + " " + surovina.getMnozstvi() + surovina.getJednotka());
		}
		
		return SurovinyChybejiToString;
	}
	
	/**
	 * Metoda zaokrouhlí double z parametru
	 * na počet desetiných míst z precision v parametru
	 * 
	 * @param double, int
	 * @return double
	 */
	private static double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}
	

}
