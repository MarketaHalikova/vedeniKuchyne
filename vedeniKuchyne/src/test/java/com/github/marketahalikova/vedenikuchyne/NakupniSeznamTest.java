package com.github.marketahalikova.vedenikuchyne;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.marketahalikova.vedenikuchyne.logika.NakupniSeznam;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina.Jednotka;


/**
 * Testovací třída k otestování třídy NakupniSeznam
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */
public class NakupniSeznamTest {
	private List<Surovina> seznamSurovin;
	private Surovina brambora;
	private NakupniSeznam nakupniSeznam;
	private List<String> seznamJakoString;

	/**
	 * Metoda se provede přes spuštěním každé testovacé metody. Slouží k vytvoření
	 * přípravku - objekty s nimiž budou testovací metody pracovat.
	 */
	@Before
	public void setUp() {
		seznamSurovin = new ArrayList<>();
		brambora = new Surovina("brambora", Jednotka.ks, 3);
		nakupniSeznam = new NakupniSeznam();
		seznamJakoString = new ArrayList<>();

	}

	/**
	 * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
	 */
	@After
	public void tearDown() {
	}

	/**
	 * Testuje vložení prázdného seznamu surovin do nákupního seznamu, přidání suroviny
	 * a převedení seznamu surovin na seznam s textovým řetězcem.
	 */
	@Test
	public void testVlozeniSuroviny() {
		nakupniSeznam.setSeznamSurovin(seznamSurovin); // settuju prázdný seznam
		assertEquals(true, nakupniSeznam.getSeznamSurovinNakupu().isEmpty()); // na začatku je seznam prázdný
		nakupniSeznam.vlozitSurovinu(brambora);
		assertEquals(false, nakupniSeznam.getSeznamSurovinNakupu().isEmpty()); // už není prázdný
		assertEquals(true, nakupniSeznam.getSeznamSurovinNakupu().contains(brambora)); //obsahuje bramboru 
		assertEquals(true, seznamJakoString.isEmpty());  // na začatku je prázdný
		seznamJakoString = nakupniSeznam.nakupniSeznamToString();
		assertEquals(false, seznamJakoString.isEmpty());  // už není prádzdný
		assertEquals(true, seznamJakoString.contains("brambora 3.0ks")); // seznam obsahuje String se surovinou ve zvoleném tvaru
	}

}
