package com.github.marketahalikova.vedenikuchyne.ui;

import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina.Jednotka;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InventuraController {
	
	@FXML
	private TextField nazevSuroviny;
	@FXML
	private TextField mnozstvi;
	@FXML
	private ComboBox<Jednotka> jednotka;
	@FXML
	private ListView<String> inventura;
	@FXML
	private Alert maloInfo;

	
	public void pridejSurovinu() {

		String nazev = nazevSuroviny.getText();
		String jedn = "" + jednotka.getSelectionModel().getSelectedItem();
		double mnoz = 0;
		String regexDecimal = "\\d{0,2}\\.\\d{1,2}";
		String regexInteger = "\\d+";

		if (!mnozstvi.getText().isEmpty() && mnozstvi.getText().matches(regexDecimal + "|" + regexInteger)) {
			mnoz = Double.parseDouble(mnozstvi.getText());
		}

		if (!(nazevSuroviny.getText().isEmpty() || mnozstvi.getText().isEmpty()
				|| jednotka.getSelectionModel().isEmpty()
				|| !mnozstvi.getText().matches(regexDecimal + "|" + regexInteger))) {

			inventura.getItems().add(nazevSuroviny.getText() + ", " + mnozstvi.getText() + ", "
					+ jednotka.getSelectionModel().getSelectedItem());

			nazevSuroviny.clear();
			mnozstvi.clear();
			jednotka.getSelectionModel().clearSelection();


		} else {
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
