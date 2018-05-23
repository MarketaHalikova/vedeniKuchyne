package com.github.marketahalikova.vedenikuchyne.ui;

import java.io.IOException;

import com.github.marketahalikova.vedenikuchyne.logika.Kuchyne;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina.Jednotka;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UpravitReceptController {

	private String vybrany;
	private Kuchyne kuchyne;

	@FXML
	private TextField receptNazev;
	@FXML
	private TextField postup;
	@FXML
	private ComboBox<String> kategorie;
	@FXML
	private ListView<String> seznamSurovin;
	@FXML
	private TextField surovinaNazev;
	@FXML
	private TextField mnozstvi;
	@FXML
	private ComboBox<Jednotka> jednotka;
	@FXML
	private Alert maloInfo;

	/**
	 * Metoda otevření okna nápověda jednotek
	 * 
	 * @throws IOException
	 */
	@FXML
	public void otevritNapoveda() throws IOException {

		// provizorní řešení

		StackPane newScene = new StackPane();
		Scene scene = new Scene(newScene, 300, 100);
		Stage provizorni = new Stage();
		provizorni.setScene(scene);
		provizorni.setAlwaysOnTop(true);
		provizorni.centerOnScreen();
		provizorni.show();

	}

	/**
	 * Metoda načte hodnotu vyběru (vybraný recept) z HomeControlleru.
	 * 
	 * @param vybrana
	 */
	public void nactiHodnotu(String vybrany, Kuchyne kuchyne) {
		this.vybrany = vybrany;
		this.kuchyne = kuchyne;

		// System.out.print(vybrany);
		// System.out.print(kuchyne.getAktualniSeznamReceptu().najdiRecept(vybrany).getNazev());

		vypisRecept();
	}

	/**
	 * Metoda vypíše do jednotlivých políček příslušné části receptu.
	 */
	public void vypisRecept() {
		receptNazev.setText(kuchyne.getAktualniSeznamReceptu().najdiRecept(vybrany).getNazev());
		postup.setText(kuchyne.getAktualniSeznamReceptu().najdiRecept(vybrany).getPostup());

		String kateg = kuchyne.getAktualniSeznamReceptu().najdiRecept(vybrany).getKategorie();

		if (kateg.equals("predkrm")) {
			kategorie.setValue("Předkrm");
		} else {
			if (kateg.equals("zakrm")) {
				kategorie.setValue("Zákrm");
			} else {
				kategorie.setValue("Krm");
			}

		}

		seznamSurovin.getItems().addAll(kuchyne.getAktualniSeznamReceptu().najdiRecept(vybrany).getSeznamJakoString());

	}

	/**
	 * Metoda přídá zadanou surovinu do seznamu surovin v receptu;
	 */
	public void pridejSurovinu() {
		if (!(surovinaNazev.getText().isEmpty() || mnozstvi.getText().isEmpty() || jednotka.getSelectionModel().isEmpty())) {
			seznamSurovin.getItems()
					.add(surovinaNazev.getText() + ", " + mnozstvi.getText() + ", " + jednotka.getSelectionModel().getSelectedItem());
			
			surovinaNazev.clear();
			mnozstvi.clear();
			jednotka.getSelectionModel().clearSelection();
		} else {
			maloInfo = new Alert(AlertType.INFORMATION);
			maloInfo.setTitle("Pozor!");
			maloInfo.setHeaderText(null);
			maloInfo.setContentText("U suroviny musí být zadaný název, množství i jednotka!");
			Stage stage = (Stage) maloInfo.getDialogPane().getScene().getWindow();
			stage.setAlwaysOnTop(true);
			maloInfo.showAndWait();
		}
	}

}
