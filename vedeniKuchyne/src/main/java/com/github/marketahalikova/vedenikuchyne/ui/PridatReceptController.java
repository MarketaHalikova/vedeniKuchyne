package com.github.marketahalikova.vedenikuchyne.ui;

import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Kontroler, který zprostředkovává komunikaci mezi logikou a oknem Upravit
 * recept.
 * 
 * @author Markéta Halíková, Johanna Švugerová, Martin Weisser
 *
 */
public class PridatReceptController extends Observable {

	private Kuchyne kuchyne;
	private List<Surovina> listNovychSurReceptu;

	@FXML
	private ComboBox<Jednotka> jednotka;
	@FXML
	private ComboBox<String> kategorie;
	@FXML
	private TextField nazev;
	@FXML
	private TextField postup;
	@FXML
	private ListView<String> seznamSurovin;
	@FXML
	private TextField nazevSuroviny;
	@FXML
	private TextField mnozstvi;
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
		listNovychSurReceptu = new ArrayList<>();
		listNovychSurReceptu.clear();
	}

	/**
	 * Metoda po přidání zadaného receptu (dle vyplněných polí) do seznamu receptů.
	 * Pokud nejsou všechny potřebné položky (pole) vyplněné, recept není uložený a vypíše se alert.
	 * 
	 * @param event
	 */
	public void pridatRecpetBtn(ActionEvent event) {
		if (!(postup.getText().isEmpty() || nazev.getText().isEmpty() || kategorie.getSelectionModel().isEmpty())) {
			String kat = "" + kategorie.getSelectionModel().getSelectedItem();
			String newKat = null;
			switch (kat) {
			case "Krm":
				newKat = "krm";
				break;
			case "Předkrm":
				newKat = "predkrm";
				break;
			case "Zákrm":
				newKat = "zakrm";
			}

			Recept novy = new Recept(nazev.getText(), postup.getText(), newKat, listNovychSurReceptu);
			kuchyne.getAktualniSeznamReceptu().vlozitRecept(novy);

			setChanged();
			notifyObservers();

			((Node) (event.getSource())).getScene().getWindow().hide();
		} else {
			maloInfo = new Alert(AlertType.INFORMATION);
			maloInfo.setTitle("Pozor!");
			maloInfo.setHeaderText(null);
			maloInfo.setContentText(
					"U suroviny musí být zadaný název, množství i jednotka a musí být vybraná kategorie! Množství musé být zadané jako celé nebo desetinné číslo!");
			Stage stage = (Stage) maloInfo.getDialogPane().getScene().getWindow();
			stage.setAlwaysOnTop(true);
			maloInfo.showAndWait();
		}

	}

	/**
	 * Metoda přidá zadanou surovinu do seznamu surovin v receptu a vypíše ji do
	 * pole seznamu surovin
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

		if (!(nazevSuroviny.getText().isEmpty() || mnozstvi.getText().isEmpty()
				|| jednotka.getSelectionModel().isEmpty()
				|| !mnozstvi.getText().matches(regexDecimal + "|" + regexInteger))) {

			seznamSurovin.getItems().add(nazevSuroviny.getText() + ", " + mnozstvi.getText() + ", "
					+ jednotka.getSelectionModel().getSelectedItem());

			nazevSuroviny.clear();
			mnozstvi.clear();
			jednotka.getSelectionModel().clearSelection();

			// pridani suroviny do novyho seznamu surovin receptu
			listNovychSurReceptu.add(new Surovina(nazev, Jednotka.valueOf(jedn), mnoz));

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

	/**
	 * Metoda přídá zadanou surovinu do seznamu surovin v receptu;
	 */
	public void smazSurovinu() {
		String vybranaSurovina = seznamSurovin.getSelectionModel().getSelectedItem();
		String nazev = vybranaSurovina.split("\\,")[0];
		Double mnozstvi = Double.parseDouble(vybranaSurovina.split("\\,")[1].trim());
		String jednotka = "" + vybranaSurovina.split("\\,")[2].trim();
		Surovina surovinaKeSmazani = new Surovina(nazev, Jednotka.valueOf(jednotka), mnozstvi);

		listNovychSurReceptu.remove(surovinaKeSmazani);
		seznamSurovin.getItems().remove(seznamSurovin.getSelectionModel().getSelectedIndex());
	}
}
