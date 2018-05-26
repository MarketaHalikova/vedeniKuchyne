package com.github.marketahalikova.vedenikuchyne.logika;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
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

		//naplneniDaty();
		nactiData();

		//ulozData();
	}

	private void naplneniDaty() {

		// tady bude naplneni provizornimi daty
		Surovina knedlik = new Surovina("knedlík", Jednotka.ks, 3);
		Surovina marijanka = new Surovina("marijanka", Jednotka.kg, 25);
		Surovina mrkev = new Surovina("mrkev", Jednotka.ks, 3);
		List < Surovina > seznamSurovinReceptu = new ArrayList < >();
		seznamSurovinReceptu.add(knedlik);
		seznamSurovinReceptu.add(marijanka);
		seznamSurovinReceptu.add(mrkev);

		Surovina lizatko = new Surovina("lízátko", Jednotka.ks, 25);
		Surovina bonbon = new Surovina("bonbón", Jednotka.ks, 3);
		List < Surovina > seznamSurovinReceptu2 = new ArrayList < >();
		seznamSurovinReceptu2.add(lizatko);
		seznamSurovinReceptu2.add(bonbon);

		seznamReceptu.vlozitRecept(new Recept("Svíčková", "Uvař svíčkovou.", "krm", seznamSurovinReceptu));
		seznamReceptu.vlozitRecept(new Recept("Rajská polévka", "Udělej rajskou.", "predkrm", seznamSurovinReceptu));
		seznamReceptu.vlozitRecept(new Recept("Tiramisu", "Udělej tiramisu.", "zakrm", seznamSurovinReceptu));

		sklad.vlozitSurovinu(new Surovina("jablko", Jednotka.kg, 3));
		sklad.vlozitSurovinu(new Surovina("marijanka", Jednotka.kg, 20));
		sklad.vlozitSurovinu(new Surovina("vodka", Jednotka.l, 10));

		menu.vlozitRecept(new Recept("Sushi", "Zabij rybu, uvař rýži.", "krm", seznamSurovinReceptu));
		menu.vlozitRecept(new Recept("Losos", "Zabij rybu.", "krm", seznamSurovinReceptu2));

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
		List < Surovina > provizorniSeznam = new ArrayList < >();

		for (Surovina surovinaMenu: menu.vytvoreniSeznamuSurovinMenu()) {
			if (sklad.getSeznamSurovinSkladu().contains(surovinaMenu)) {
				Surovina sur = sklad.getSeznamSurovinSkladu().get(sklad.getSeznamSurovinSkladu().indexOf(surovinaMenu));
				if (sur.getMnozstvi() < surovinaMenu.getMnozstvi()) {
					provizorniSeznam.add(new Surovina(sur.getNazev(), sur.getJednotka(), round(surovinaMenu.getMnozstvi() - sur.getMnozstvi(), 1)));
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
	public List < String > srovnaniSurovinReceptuSeSkladem(Recept recept) {

		List < Surovina > SurovinyChybejiciNaSklade = new ArrayList < >();
		List < Surovina > provizorniSeznam = new ArrayList < >();

		for (Surovina surovinaMenu: recept.getSeznamSurovinReceptu()) {
			if (sklad.getSeznamSurovinSkladu().contains(surovinaMenu)) {
				Surovina sur = sklad.getSeznamSurovinSkladu().get(sklad.getSeznamSurovinSkladu().indexOf(surovinaMenu));
				if (sur.getMnozstvi() < surovinaMenu.getMnozstvi()) {
					provizorniSeznam.add(new Surovina(sur.getNazev(), sur.getJednotka(), round(surovinaMenu.getMnozstvi() - sur.getMnozstvi(), 1)));
				}
			} else {
				provizorniSeznam.add(surovinaMenu);
			}
		}

		SurovinyChybejiciNaSklade.addAll(provizorniSeznam);

		List < String > SurovinyChybejiToString = new ArrayList < >();
		for (Surovina surovina: SurovinyChybejiciNaSklade) {
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
	private static double round(double value, int precision) {
		int scale = (int) Math.pow(10, precision);
		return (double) Math.round(value * scale) / scale;
	}

	/**
	 * Metoda uloží data tříd do souborů
	 * 
	 */
	public void ulozData() {
		try {
			ObjectOutputStream surovinyData = new ObjectOutputStream(new FileOutputStream("suroviny.txt"));
			surovinyData.writeObject(sklad);
			surovinyData.close();

			ObjectOutputStream receptyData = new ObjectOutputStream(new FileOutputStream("recepty.txt"));
			receptyData.writeObject(seznamReceptu);
			receptyData.close();

			ObjectOutputStream menuData = new ObjectOutputStream(new FileOutputStream("menu.txt"));
			menuData.writeObject(menu);
			menuData.close();

		}

		catch(FileNotFoundException e) {
			System.out.println("Nepodařilo se otevřít vstupní soubory.");
			return;
		}
		catch(InvalidClassException e) {
			System.out.println("Chyba ve třídách určené k serializaci.");
			return;
		}
		catch(NotSerializableException e) {
			System.out.println("Neserializované objekty.");
			return;
		}
		catch(IOException e) {
			System.out.println("Nepodařilo se uložit do souborů.");
			return;

		}
	}

	/**
	 * Metoda načte data tříd ze souborů
	 * 
	 */
	public void nactiData() {
		try {
			ObjectInputStream surovinyData = new ObjectInputStream(new FileInputStream("suroviny.txt"));
			sklad = (Sklad) surovinyData.readObject();
			surovinyData.close();

			ObjectInputStream receptyData = new ObjectInputStream(new FileInputStream("recepty.txt"));
			seznamReceptu = (SeznamReceptu) receptyData.readObject();
			receptyData.close();

			ObjectInputStream menuData = new ObjectInputStream(new FileInputStream("menu.txt"));
			menu = (Menu) menuData.readObject();
			menuData.close();
		}

		catch(FileNotFoundException e) {
			System.out.println("Nepodařilo se otevřít soubory s daty.");
			return;
		}
		catch(StreamCorruptedException e) {
			System.out.println("Chybná struktura souborů s daty.");
			return;
		}
		catch(IOException e) {
			System.out.println("Chyba při čtení ze souborů s uloženými objekty.");
			return;
		}
		catch(ClassNotFoundException e) {
			System.out.println("V souborech nejsou data.");
			return;
		}

	}

}