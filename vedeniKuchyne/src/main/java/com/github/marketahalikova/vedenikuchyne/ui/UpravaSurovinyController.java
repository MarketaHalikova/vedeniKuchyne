package com.github.marketahalikova.vedenikuchyne.ui;

import com.github.marketahalikova.vedenikuchyne.logika.Kuchyne;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Kontroler, který zprostředkovává komunikaci mezi logikou a oknem Upravit
 * surovinu.
 * 
 * @author Markéta Halíková, Johanna Švugerová, Martin Weisser
 *
 */
public class UpravaSurovinyController {

	private Kuchyne kuchyne;
	private String vybrana;
	private Surovina surovina;

	@FXML
	private TextField nazev;
	@FXML
	private TextField mnozstvi;
	@FXML
	private ComboBox jednotka;

	/**
	 * Metoda načte hodnotu vybrané suroviny z HomeControlleru
	 * 
	 * @param kuchyne
	 *            - aktuální stav kuchyně
	 * @param vybrana
	 *            - vybraná surovina
	 */
	public void nactiHodnoty(Kuchyne kuchyne, String vybrana) {
		this.kuchyne = kuchyne;
		this.vybrana = vybrana;
		vypisSurovinu();
	}

	/**
	 * Metoda vypíše položky zadné suroviny (název, množství a jednotka) do příslušných polí
	 */
	public void vypisSurovinu() {
		surovina = kuchyne.getAktualniSklad().najdiSurovinu(vybrana);
		nazev.setText(surovina.getNazev());
		mnozstvi.setText(Double.toString(surovina.getMnozstvi()));
		
		String jedn = (kuchyne.getAktualniSklad().najdiSurovinu(vybrana).getJednotka().name());
		
		switch(jedn) {
		case "kg":
			jednotka.getSelectionModel().select(0);
			break;
		case "ks":
			jednotka.getSelectionModel().select(1);
			break;
		case "l":
			jednotka.getSelectionModel().select(2);
		}
		
	}

}
