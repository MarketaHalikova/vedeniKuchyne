package com.github.marketahalikova.vedenikuchyne.logika;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Třída Kuchyne
 * 
 * Hlavní třída aplikace.
 * Slouží pro vrácení hodnot skladu, menu, nákupního seznamu i receptů.
 * Také se tu ukládají data do souborů a zároveň se z těchto souborů i čtou.
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

	/**
	 * Konstruktor třídy Kuchyne
	 */
	public Kuchyne() {

		this.menu = new Menu();
		this.nakupniSeznam = new NakupniSeznam();
		this.sklad = new Sklad();
		this.seznamReceptu = new SeznamReceptu();

		try {
			nactiData();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * Metoda serializuje data tříd a uloží do souborů
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws URISyntaxException 
	 * 
	 */
	public void ulozData() throws FileNotFoundException, IOException, URISyntaxException {
		
		ObjectOutputStream surovinyData = new ObjectOutputStream(new FileOutputStream(new File(getClass().getResource("/suroviny.txt").toURI())));
		surovinyData.writeObject(sklad);
		surovinyData.close();

		ObjectOutputStream receptyData = new ObjectOutputStream(new FileOutputStream(new File(getClass().getResource("/recepty.txt").toURI())));
		receptyData.writeObject(seznamReceptu);
		receptyData.close();

	}

	/**
	 * Metoda načte data tříd ze souborů a deserializuje je
	 * @throws URISyntaxException 
	 * 
	 */
	public void nactiData() throws URISyntaxException {
		try {
			ObjectInputStream surovinyData = new ObjectInputStream(new FileInputStream(new File(getClass().getResource("/suroviny.txt").toURI())));
			sklad = (Sklad) surovinyData.readObject();
			surovinyData.close();

			ObjectInputStream receptyData = new ObjectInputStream(new FileInputStream(new File(getClass().getResource("/recepty.txt").toURI())));
			seznamReceptu = (SeznamReceptu) receptyData.readObject();
			receptyData.close();

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