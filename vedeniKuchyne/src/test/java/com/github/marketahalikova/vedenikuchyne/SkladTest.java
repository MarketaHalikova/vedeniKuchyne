package com.github.marketahalikova.vedenikuchyne;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.marketahalikova.vedenikuchyne.logika.Sklad;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina.Jednotka;

/**
 * Třída testující třídu Sklad.
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */
public class SkladTest {
	
	private Sklad sklad;
	private Surovina brambora;
	private List<Surovina> seznamSurovin;
	private Map<String, Surovina> seznamJakoString;
	
	/**
	 * Metoda se provede přes spuštěním každé testovacé metody. Slouží k vytvoření
	 * přípravku - objekty s nimiž budou testovací metody pracovat.
	 */
	@Before
	public void setUp() {
		sklad = new Sklad();
		seznamSurovin = new ArrayList<>();
		brambora = new Surovina("brambora", Jednotka.ks, 3);
	}

	/**
	 * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
	 */
	@After
	public void tearDown() {
	}

	/**
	 * Metoda testuje vkládání a odebírání suroviny na sklad.
	 * Také testuje jednotlivé seznamy (seznam suroviny a mapu surovin obsahující vždy zvolený řetězec - jméno suroviny,
	 * množství a jednotku).
	 */
	@Test
	public void testSkladu() {
		sklad.setSeznamSurovin(seznamSurovin);
		assertEquals(true, sklad.getSeznamSurovinSkladu().isEmpty()); // na začatku je seznam prázdný
		assertEquals(true, sklad.getSkladAsString().isEmpty()); // na začatku je seznam prázdný
		sklad.vlozitSurovinu(brambora);
		assertEquals(false, sklad.getSeznamSurovinSkladu().isEmpty()); 
		assertEquals(true, sklad.getSeznamSurovinSkladu().contains(brambora)); 
		assertEquals(false, sklad.getSkladAsString().isEmpty()); 
		assertEquals(true, sklad.getSkladAsString().keySet().contains(("brambora, 3.0, ks"))); 
		assertEquals(true, sklad.getSkladAsString().values().contains(brambora)); 
		assertEquals(brambora, sklad.najdiSurovinu("brambora, 3.0, ks")); 
		sklad.odstranSurovinu(brambora);
		assertEquals(true, sklad.getSeznamSurovinSkladu().isEmpty()); // seznam je znovu prázdný
		assertEquals(false, sklad.getSeznamSurovinSkladu().contains(brambora));  //brambora už v seznamu není
		
	}

}
