package com.github.marketahalikova.vedenikuchyne;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.marketahalikova.vedenikuchyne.logika.Menu;
import com.github.marketahalikova.vedenikuchyne.logika.Recept;
import com.github.marketahalikova.vedenikuchyne.logika.SeznamReceptu;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina.Jednotka;

/**
 * Třída testující třídu Menu.
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */

public class MenuTest {

	private Menu menu;
	private List<Recept> seznamReceptu;
	private List<Surovina> seznamSurovin; 
	private Surovina brambora;
	private Recept recept;

	/**
	 * Metoda se provede přes spuštěním každé testovacé metody. Slouží k vytvoření
	 * přípravku - objekty s nimiž budou testovací metody pracovat.
	 */
	@Before
	public void setUp() {
		menu = new Menu();
		seznamReceptu = new ArrayList<>();
		brambora = new Surovina("brambora", Jednotka.ks, 3);
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

	@Test
	public void testMenu() {
		menu.setSeznamReceptu(seznamReceptu); //vložení prázdného seznamu
		assertEquals(true, menu.getSeznamReceptu().isEmpty()); //kontrola, že je seznam prázdný
		menu.vlozitRecept(recept); //vložení receptu do seznamu
		assertEquals(false, menu.getSeznamReceptu().isEmpty()); //kontrola, že už seznam není prázdný
		assertEquals(recept, menu.najdiRecept("Bramboračka"));
		assertEquals(false, menu.vytvoreniSeznamuSurovinMenu().isEmpty());
		assertEquals(true, menu.vytvoreniSeznamuSurovinMenu().contains(brambora));
		assertEquals(true, menu.getNazvyReceptu().contains("Bramboračka"));
		menu.odstranRecept(recept);		
		assertEquals(true, menu.getSeznamReceptu().isEmpty()); //kontrola, že už seznam není prázdný
		assertEquals(null, menu.najdiRecept("Bramboračka"));
		
	}
	


}
