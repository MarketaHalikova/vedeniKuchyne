package com.github.marketahalikova.vedenikuchyne;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.marketahalikova.vedenikuchyne.logika.NakupniSeznam;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina.Jednotka;

/**
 * Třída testující třídu Surovina.
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */
public class SurovinaTest {

	private Surovina brambora;
	private Surovina mleko;

	/**
	 * Metoda se provede přes spuštěním každé testovacé metody. Slouží k vytvoření
	 * přípravku - objekty s nimiž budou testovací metody pracovat.
	 */
	@Before
	public void setUp() {
		brambora = new Surovina("brambora", Jednotka.ks, 3);
		mleko = new Surovina("mléko", Jednotka.l);
	}

	/**
	 * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
	 */
	@After
	public void tearDown() {
	}

	/**
	 * Testuje gettery, settery a konstruktory třídy Suroivna
	 */
	@Test
	public void testSuroviny() {
		assertEquals("brambora", brambora.getNazev());
		assertEquals(3.0, brambora.getMnozstvi(), 0.0001);
		assertEquals(Jednotka.ks, brambora.getJednotka());
		assertEquals("mléko", mleko.getNazev());
		assertEquals(Jednotka.l, mleko.getJednotka());
		mleko.setNazev("mlíčko"); // název změnen
		assertEquals("mlíčko", mleko.getNazev()); // kontrola změny
		brambora.setJednotka(Jednotka.kg); // jednotka brambory změněna na kg
		brambora.setMnozstvi(6); // zmeněno množství
		assertEquals(6.0, brambora.getMnozstvi(), 0.0001); // kontrola změny
		assertEquals(Jednotka.kg, brambora.getJednotka()); // kontrola změny

	}

}
