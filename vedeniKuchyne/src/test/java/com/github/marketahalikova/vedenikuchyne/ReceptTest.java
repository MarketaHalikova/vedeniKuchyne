package com.github.marketahalikova.vedenikuchyne;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.marketahalikova.vedenikuchyne.logika.Recept;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina.Jednotka;

/**
* Třída testující třídu Recept.
* 
* @author Johanna Švugerová, Markéta Halíková, Martin Weisser
*
*/
public class ReceptTest {
	
	private Recept recept;
	private Surovina brambora;
	private Surovina cibule;
	private List<Surovina> seznam;
 
	/**
	 * Metoda se provede přes spuštěním každé testovacé metody. Slouží k vytvoření
	 * přípravku - objekty s nimiž budou testovací metody pracovat.
	 */
	@Before
	public void setUp() {
		brambora = new Surovina("brambora", Jednotka.ks, 3);
		cibule = new Surovina("cibule", Jednotka.ks, 1);
		seznam = new ArrayList<>();
		seznam.add(brambora);
		recept = new Recept("Bramboračka", "Uvař bramboračku", "predkrm", null );
	}

	/**
	 * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
	 */
	@After
	public void tearDown() {
	}
	
	/**
	 * Metoda testuje settery, gettery a konstuktor třídy Recept. Dále také možnost vložit surovinu do seznamu surovin reeptu
	 * a možnost vypsat tento seznam jako řetezec "jméno, množství, jednotka".
	 */
	@Test
	public void testReceptu() {
		recept.setSeznamSurovinReceptu(seznam);
		assertEquals("Bramboračka", recept.getNazev());
		assertEquals("Uvař bramboračku", recept.getPostup());
		assertEquals("predkrm", recept.getKategorie());
		assertEquals(true, recept.getSeznamSurovinReceptu().contains(brambora));
		assertEquals(true, recept.getSeznamJakoString().contains("brambora, 3.0, ks"));
		recept.setNovouSurovinuReceptu(cibule);
		assertEquals(true, recept.getSeznamSurovinReceptu().contains(cibule));
		recept.setNazev("Bramborák");
		assertEquals("Bramborák", recept.getNazev());
		recept.setKategorie("krm");
		assertEquals("krm", recept.getKategorie());
		recept.setPostup("Udělej bramborák");
		assertEquals("Udělej bramborák", recept.getPostup());
		
		
		
		
	}

}
