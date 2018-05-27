package com.github.marketahalikova.vedenikuchyne;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.marketahalikova.vedenikuchyne.logika.Recept;
import com.github.marketahalikova.vedenikuchyne.logika.SeznamReceptu;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina.Jednotka;

/**
 * Třída testující třídu SeznamReceptu.
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */
public class SeznamReceptuTest {
	private List<Recept> seznam;
	private List<Surovina> seznamSurovin;
	private Recept recept;
	private Surovina brambora;
	private Surovina cibule;
	private SeznamReceptu seznamReceptu;

	/**
	 * Metoda se provede přes spuštěním každé testovacé metody. Slouží k vytvoření
	 * přípravku - objekty s nimiž budou testovací metody pracovat.
	 */
	@Before
	public void setUp() {
		seznamReceptu = new SeznamReceptu();
		brambora = new Surovina("brambora", Jednotka.ks, 3);
		seznam = new ArrayList<>();
		seznamSurovin = new ArrayList<>();
		seznamSurovin.add(brambora);
		recept = new Recept("Bramboračka", "Uvař bramboračku", "predkrm", seznamSurovin);
	}

	/**
	 * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
	 */
	@After
	public void tearDown() {
	}

	/**
	 * Metoda testuje vložení receptu do seznamu, jeho případnou prázdnost a možnost získat seznam receptů podle kategorie.
	 */
	@Test
	public void testSeznamuReceptu() {
		seznamReceptu.setSeznamReceptu(seznam); // vložen prázdný seznam
		assertEquals(true, seznamReceptu.getSeznamReceptu().isEmpty()); // kontrola prázdnosti seznamu
		seznamReceptu.vlozitRecept(recept);// vložen recept do seznamu
		assertEquals(false, seznamReceptu.getSeznamReceptu().isEmpty()); // seznam už není prázdný
		assertEquals(recept, seznamReceptu.najdiRecept("Bramboračka"));
		assertEquals(true, seznamReceptu.getPodleKategorie("krm").isEmpty());
		assertEquals(true, seznamReceptu.getPodleKategorie("predkrm").contains("Bramboračka"));
	}

}
