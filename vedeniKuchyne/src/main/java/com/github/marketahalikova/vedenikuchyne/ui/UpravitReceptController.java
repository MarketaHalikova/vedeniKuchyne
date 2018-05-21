package com.github.marketahalikova.vedenikuchyne.ui;

import java.io.IOException;

import com.github.marketahalikova.vedenikuchyne.logika.Kuchyne;

import javafx.fxml.FXML;
import javafx.scene.Scene;
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

}
