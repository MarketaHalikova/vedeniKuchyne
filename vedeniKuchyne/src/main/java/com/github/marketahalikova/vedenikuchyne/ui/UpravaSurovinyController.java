package com.github.marketahalikova.vedenikuchyne.ui;

import java.util.Observable;

import com.github.marketahalikova.vedenikuchyne.logika.Kuchyne;
import com.github.marketahalikova.vedenikuchyne.logika.Recept;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina.Jednotka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Kontroler, který zprostředkovává komunikaci mezi logikou a oknem Upravit
 * surovinu.
 * 
 * @author Markéta Halíková, Johanna Švugerová, Martin Weisser
 *
 */
public class UpravaSurovinyController extends Observable {

	private Kuchyne kuchyne;
	private String vybrana;
	private Surovina surovina;

	@FXML
	private TextField nazev;
	@FXML
	private TextField mnozstvi;
	@FXML
	private ComboBox<String> jednotka;
	@FXML
	private Alert maloInfo;

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
		
		jednotka.setValue(kuchyne.getAktualniSklad().najdiSurovinu(vybrana).getJednotka().name());
		
	/*	
	 	String jedn = kuchyne.getAktualniSklad().najdiSurovinu(vybrana).getJednotka().name();
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
	*/
	}
	
	/**
	 * Metoda pro akci spuštěnou talčítkem "Uložit". 
	 * Surovina se změní podle zadaných kritérií a přepíše se v seznamu surovin na skladě.
	 * Při chybějích nebo mylných udajích se zobrazí alert s nápovědou.
	 * 
	 * @param event
	 */
	public void upravitSurovinuBtn(ActionEvent event) {
		
		Surovina nalezena = kuchyne.getAktualniSklad().najdiSurovinu(vybrana);

		// nelze přidat recept, který již v databázi je
		for (Surovina surovina: kuchyne.getAktualniSklad().getSeznamSurovinSkladu()) {
			
			if(nazev.getText().trim().toLowerCase().equals(surovina.getNazev().trim().toLowerCase()) && !surovina.equals(nalezena)){
				maloInfo = new Alert(AlertType.INFORMATION);
				maloInfo.setTitle("Pozor!");
				maloInfo.setHeaderText(null);
				maloInfo.setContentText(
						"Surovina s tímto názvem již na skladě je. Upravte její množství.");
				Stage stage = (Stage) maloInfo.getDialogPane().getScene().getWindow();
				stage.setAlwaysOnTop(true);
				maloInfo.showAndWait();
				return;
			} 
		}
		
		
		
		
		
		double mnoz = 0;
		String regexDecimal = "\\d{0,2}\\.\\d{1,2}";
		String regexInteger = "\\d+";
		String jedn = (String) jednotka.getSelectionModel().getSelectedItem();
		
		if (!mnozstvi.getText().isEmpty() && mnozstvi.getText().matches(regexDecimal + "|" + regexInteger)) {
			mnoz = Double.parseDouble(mnozstvi.getText());
		}

		if (!(nazev.getText().isEmpty() || mnozstvi.getText().isEmpty()
				|| jednotka.getSelectionModel().isEmpty()
				|| !mnozstvi.getText().matches(regexDecimal + "|" + regexInteger))) {
			
			
			Surovina nova = new Surovina (nazev.getText(), Jednotka.valueOf(jedn), mnoz);
			kuchyne.getAktualniSklad().odstranSurovinu(surovina);
			kuchyne.getAktualniSklad().vlozitSurovinu(nova);
			
			setChanged();
			notifyObservers();
			
			((Node) (event.getSource())).getScene().getWindow().hide();
		}
		else {
			maloInfo = new Alert(AlertType.INFORMATION);
			maloInfo.setTitle("Pozor!");
			maloInfo.setHeaderText(null);
			maloInfo.setContentText(
					"U suroviny musí být zadaný název, množství i jednotka! Množství musé být zadané jako celé nebo desetinné číslo!");
			Stage stage = (Stage) maloInfo.getDialogPane().getScene().getWindow();
			stage.setAlwaysOnTop(true);
			maloInfo.showAndWait();
		}

	}

}
