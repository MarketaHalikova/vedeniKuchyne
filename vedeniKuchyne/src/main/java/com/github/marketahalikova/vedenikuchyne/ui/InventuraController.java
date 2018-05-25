package com.github.marketahalikova.vedenikuchyne.ui;

import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InventuraController extends Observable {
	
	private Kuchyne kuchyne;
	private List<Surovina> seznamInventura;
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
	
	public void inicializuj(Kuchyne kuchyne) {
		this.kuchyne = kuchyne;
		seznamInventura = new ArrayList<>();
	}
	
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

			Surovina surovina = new Surovina (nazevSuroviny.getText(), Jednotka.valueOf(jedn), mnoz);
			seznamInventura.add(surovina);
			
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
	
	public void zadejInventuru(ActionEvent event) {
		kuchyne.getAktualniSklad().getSeznamSurovinSkladu().clear();
		kuchyne.getAktualniSklad().setSeznamSurovin(seznamInventura);
		
		setChanged();
		notifyObservers();
		
		((Node) (event.getSource())).getScene().getWindow().hide();
	}
}
