package com.github.marketahalikova.vedenikuchyne.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import com.github.marketahalikova.vedenikuchyne.logika.Kuchyne;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina.Jednotka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Kontroler, který zprostředkovává komunikaci mezi logikou a oknem Inventura.
 * 
 * 
 * @author Markéta Halíková, Johanna Švugerová, Martin Weisser
 *
 */
public class InventuraController extends Observable {

	private Kuchyne kuchyne;
	private List < Surovina > seznamInventura;
	@FXML
	private TextField nazevSuroviny;
	@FXML
	private TextField mnozstvi;
	@FXML
	private ComboBox < Jednotka > jednotka;
	@FXML
	private ListView < String > inventura;
	@FXML
	private Alert maloInfo;

	/**
	 * Metoda předává kontroleru aktuální stav kuchyně.
	 * 
	 * @param kuchyne
	 *            - aktuální stav kuchyně
	 */
	public void inicializuj(Kuchyne kuchyne) {
		this.kuchyne = kuchyne;
		seznamInventura = new ArrayList < >();
	}

	/**
	 * Metoda přidá zadanou surovinu do seznamu surovin v receptu a vypíše ji do
	 * pole seznamu surovin. Při chybějích nebo mylných udajích se zobrazí alert s
	 * nápovědou.
	 */
	public void pridejSurovinu() {

		String nazev = nazevSuroviny.getText();
		String jedn = "" + jednotka.getSelectionModel().getSelectedItem();
		double mnoz = 0;
		String regexDecimal = "\\d{0,2}\\.\\d{1,2}";
		String regexInteger = "\\d+";

		if (!mnozstvi.getText().isEmpty() && mnozstvi.getText().matches(regexDecimal + "|" + regexInteger)) {
			mnoz = Double.parseDouble(mnozstvi.getText());
		}

		if (! (nazevSuroviny.getText().isEmpty() || mnozstvi.getText().isEmpty() || jednotka.getSelectionModel().isEmpty() || !mnozstvi.getText().matches(regexDecimal + "|" + regexInteger))) {

			inventura.getItems().add(nazevSuroviny.getText() + ", " + mnozstvi.getText() + ", " + jednotka.getSelectionModel().getSelectedItem());

			Surovina surovina = new Surovina(nazevSuroviny.getText(), Jednotka.valueOf(jedn), mnoz);
			seznamInventura.add(surovina);

			nazevSuroviny.clear();
			mnozstvi.clear();
			jednotka.getSelectionModel().clearSelection();

		} else {
			maloInfo = new Alert(AlertType.INFORMATION);
			maloInfo.setTitle("Pozor!");
			maloInfo.setHeaderText(null);
			maloInfo.setContentText("U suroviny musí být zadaný název, množství i jednotka! Množství musé být zadané jako celé nebo desetinné číslo!");
			Stage stage = (Stage) maloInfo.getDialogPane().getScene().getWindow();
			stage.setAlwaysOnTop(true);
			maloInfo.showAndWait();
		}

	}

	/**
	 * Metoda pro akci spuštěnou talčítkem "Zadat inventuru". Přepíše aktuální
	 * seznam surovin na skladě za nově zadaný.
	 * 
	 * @param event
	 */
	public void zadejInventuruBtn(ActionEvent event) {

		// Přidat kontrolu prázdnosti seznamu vs možnost prázdného skladu??
		if (seznamInventura.isEmpty()) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Jste si jisti?");
			alert.setHeaderText("Chystáte se zaspat prázdnou inventuru");
			alert.setContentText("Opravdu chcete smazat aktuální stav na skladě a zaspat prázdnou inventuru?");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.setAlwaysOnTop(true);

			Optional < ButtonType > result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				kuchyne.getAktualniSklad().getSeznamSurovinSkladu().clear();
				kuchyne.getAktualniSklad().setSeznamSurovin(seznamInventura);

				setChanged();
				notifyObservers();

				((Node)(event.getSource())).getScene().getWindow().hide();
			}
		} else {

			kuchyne.getAktualniSklad().getSeznamSurovinSkladu().clear();
			kuchyne.getAktualniSklad().setSeznamSurovin(seznamInventura);

			setChanged();
			notifyObservers();

			((Node)(event.getSource())).getScene().getWindow().hide();
		}
	}
}