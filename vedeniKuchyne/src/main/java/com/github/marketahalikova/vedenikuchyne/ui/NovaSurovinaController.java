package com.github.marketahalikova.vedenikuchyne.ui;

import java.util.Observable;

import com.github.marketahalikova.vedenikuchyne.logika.Kuchyne;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina.Jednotka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Kontroler, který zprostředkovává komunikaci mezi logikou a oknem Přidat
 * surovinu.
 * 
 * @author Markéta Halíková, Johanna Švugerová, Martin Weisser
 *
 */
public class NovaSurovinaController extends Observable {

	private Kuchyne kuchyne;

	@FXML
	private Alert maloInfo;
	@FXML
	private TextField nazev;
	@FXML
	private TextField mnozstvi;
	@FXML
	private ComboBox < String > jednotka;

	/**
	 * Metoda načte současný stav kuchyně z HomeControlleru.
	 * 
	 * @param kuchyne
	 */
	public void inicializuj(Kuchyne kuchyne) {
		this.kuchyne = kuchyne;
	}

	/**
	 * Metoda pro přidání suroviny po stisknutí talčítka "Přidat".
	 * Surovina se zadaným množstvím, názvem a jednotkou se uloží na sklad.
	 * Při chybějích nebo mylných udajích se zobrazí alert s nápovědou.
	 * @param event
	 */
	public void pridejSurovinuBtn(ActionEvent event) {

		// nelze přidat surovinu s názvem, který již na skladě je
		for (Surovina surovina: kuchyne.getAktualniSklad().getSeznamSurovinSkladu()) {

			if (nazev.getText().trim().toLowerCase().equals(surovina.getNazev().trim().toLowerCase())) {
				maloInfo = new Alert(AlertType.INFORMATION);
				maloInfo.setTitle("Pozor!");
				maloInfo.setHeaderText(null);
				maloInfo.setContentText("Surovina s tímto názvem již na skladě je. Upravte její množství.");
				Stage stage = (Stage) maloInfo.getDialogPane().getScene().getWindow();
				stage.setAlwaysOnTop(true);
				maloInfo.showAndWait();
				return;
			}
		}

		String nazevSuroviny = nazev.getText();
		String jedn = "" + jednotka.getSelectionModel().getSelectedItem();
		double mnoz = 0;
		String regexDecimal = "\\d{0,2}\\.\\d{1,2}";
		String regexInteger = "\\d+";

		if (!mnozstvi.getText().isEmpty() && mnozstvi.getText().matches(regexDecimal + "|" + regexInteger)) {
			mnoz = Double.parseDouble(mnozstvi.getText());
		}

		if (! (nazev.getText().isEmpty() || mnozstvi.getText().isEmpty() || jednotka.getSelectionModel().isEmpty() || !mnozstvi.getText().matches(regexDecimal + "|" + regexInteger))) {

			nazev.clear();
			mnozstvi.clear();
			jednotka.getSelectionModel().clearSelection();

			Surovina nova = new Surovina(nazevSuroviny, Jednotka.valueOf(jedn), mnoz);
			kuchyne.getAktualniSklad().vlozitSurovinu(nova);

			setChanged();
			notifyObservers();

			((Node)(event.getSource())).getScene().getWindow().hide();
		}
		else {
			maloInfo = new Alert(AlertType.INFORMATION);
			maloInfo.setTitle("Pozor!");
			maloInfo.setHeaderText(null);
			maloInfo.setContentText("U suroviny musí být zadaný název, množství i jednotka! Množství musé být zadané jako celé nebo desetinné číslo!");
			Stage stage = (Stage) maloInfo.getDialogPane().getScene().getWindow();
			stage.setAlwaysOnTop(true);
			maloInfo.showAndWait();
		}
	}

}