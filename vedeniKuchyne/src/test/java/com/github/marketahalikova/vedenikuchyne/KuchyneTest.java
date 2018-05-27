package com.github.marketahalikova.vedenikuchyne;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.marketahalikova.vedenikuchyne.logika.Kuchyne;
import com.github.marketahalikova.vedenikuchyne.logika.Recept;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina.Jednotka;


/**
 * Třída testující třídu Kuchyne.
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */
public class KuchyneTest {
	private Kuchyne kuchyne;
	private List<Surovina> seznamSurovinNakup;
	private List<Surovina> seznamSurovinSklad;
	private List<Recept> seznamReceptuMenu;
	private List<Recept> seznamReceptu;
	private List<Surovina> seznamSurovinReceptu;
	private Surovina brambora;
	private Surovina cibule;
	private Recept gulas;
	
	/**
	 * Metoda se provede přes spuštěním každé testovacé metody. Slouží k vytvoření
	 * přípravku - objekty s nimiž budou testovací metody pracovat.
	 */
	@Before
	public void setUp() {
		kuchyne = new Kuchyne();
		seznamSurovinNakup = new ArrayList<>();
		seznamSurovinSklad = new ArrayList<>();
		seznamReceptuMenu = new ArrayList<>();
		seznamSurovinReceptu = new ArrayList<>();
		seznamReceptu = new ArrayList<>();
		brambora = new Surovina("brambora", Jednotka.ks, 3);
		cibule = new Surovina("cibule", Jednotka.ks, 1);
		gulas = new Recept("Guláš", "Uvař guláš", "krm", seznamSurovinReceptu );
		seznamSurovinReceptu.add(cibule);
		seznamReceptuMenu.add(gulas);
		seznamReceptu.add(gulas);
		kuchyne.getAktualniNakupniSeznam().setSeznamSurovin(seznamSurovinNakup);
		kuchyne.getAktualniSklad().setSeznamSurovin(seznamSurovinSklad);
		kuchyne.getAktualniMenu().setSeznamReceptu(seznamReceptuMenu);
		kuchyne.getAktualniSeznamReceptu().setSeznamReceptu(seznamReceptu);
		//seznamSurovinReceptu = kuchyne.getAktualniMenu().vytvoreniSeznamuSurovinMenu();
	}

	/**
	 * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
	 */
	@After
	public void tearDown() {
	}

	/**
	 * Metoda testuje stav kuchyne.
	 * Konkrétně testuje srovnávaní surovin v receptu se sruovinami na skladě
	 *  a surovin v menu se surovinami na skladě.
	 */
	@Test
	public void testKuchyne() {
		assertEquals(true, kuchyne.getAktualniNakupniSeznam().getSeznamSurovinNakupu().isEmpty());
		kuchyne.getAktualniNakupniSeznam().vlozitSurovinu(brambora);
		assertEquals(true, kuchyne.getAktualniNakupniSeznam().getSeznamSurovinNakupu().contains(brambora));
		assertEquals(true, kuchyne.getAktualniSklad().getSeznamSurovinSkladu().isEmpty());
		assertEquals(false, kuchyne.getAktualniMenu().getSeznamReceptu().isEmpty());
		assertEquals(true, kuchyne.getAktualniMenu().getSeznamReceptu().contains(gulas));
		kuchyne.getAktualniMenu().vytvoreniSeznamuSurovinMenu();
		kuchyne.srovnaniSurovinMenuSeSkladem();
		assertEquals(true, kuchyne.getAktualniNakupniSeznam().getSeznamSurovinNakupu().contains(cibule));
		assertEquals(true, kuchyne.srovnaniSurovinReceptuSeSkladem(gulas).contains("cibule 1.0ks"));
	}

}
