package com.github.marketahalikova.vedenikuchyne.ui;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.github.marketahalikova.vedenikuchyne.logika.Kuchyne;
import com.github.marketahalikova.vedenikuchyne.logika.Recept;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina.Jednotka;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class UpravitReceptController extends Observable{

	private String vybrany;
	private Kuchyne kuchyne;
	private List<Surovina> listNovychSurReceptu;

	@FXML
	private TextField receptNazev;
	@FXML
	private TextField postup;
	@FXML
	private ListView<String> chybejiciSuroviny;
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
	@FXML
	private Button smazatBtn;
	@FXML
	private Button menuBtn;

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

		this.listNovychSurReceptu = new ArrayList<>();
		listNovychSurReceptu.clear();
		listNovychSurReceptu.addAll(kuchyne.getAktualniSeznamReceptu().najdiRecept(vybrany).getSeznamSurovinReceptu());

		Tooltip tooltip = new Tooltip("Stiskněte ENTER aby se změna uložila");
		hackTooltipStartTiming(tooltip);
		postup.setTooltip(tooltip);
		receptNazev.setTooltip(tooltip);
		
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

		ObservableList<String> listChybejicich = FXCollections.observableArrayList();
		listChybejicich.addAll(
				kuchyne.srovnaniSurovinReceptuSeSkladem(kuchyne.getAktualniSeznamReceptu().najdiRecept(vybrany)));
		chybejiciSuroviny.setItems(listChybejicich);

	}

	/**
	 * Metoda přídá zadanou surovinu do seznamu surovin v receptu;
	 */
	public void pridejSurovinu() {

		String nazev = surovinaNazev.getText();
		String jedn = "" + jednotka.getSelectionModel().getSelectedItem();
		double mnoz = 0;
		String regexDecimal = "\\d{0,2}\\.\\d{1,2}";
		String regexInteger = "\\d+";

		if (!mnozstvi.getText().isEmpty() && mnozstvi.getText().matches(regexDecimal + "|" + regexInteger)) {
			mnoz = Double.parseDouble(mnozstvi.getText());
		}

		if (!(surovinaNazev.getText().isEmpty() || mnozstvi.getText().isEmpty()
				|| jednotka.getSelectionModel().isEmpty()
				|| !mnozstvi.getText().matches(regexDecimal + "|" + regexInteger))) {
			seznamSurovin.getItems().add(surovinaNazev.getText() + ", " + mnozstvi.getText() + ", "
					+ jednotka.getSelectionModel().getSelectedItem());

			surovinaNazev.clear();
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
		lzeJenUprvit();
	}

	public void smazSurovinu() {
		String vybranaSurovina = seznamSurovin.getSelectionModel().getSelectedItem();
		String nazev = vybranaSurovina.split("\\,")[0];
		Double mnozstvi = Double.parseDouble(vybranaSurovina.split("\\,")[1].trim());
		String jednotka = "" + vybranaSurovina.split("\\,")[2].trim();
		Surovina surovinaKeSmazani = new Surovina(nazev, Jednotka.valueOf(jednotka), mnozstvi);

		listNovychSurReceptu.remove(surovinaKeSmazani);
		seznamSurovin.getItems().remove(seznamSurovin.getSelectionModel().getSelectedIndex());
		lzeJenUprvit();
	}

	// pripojit
	public void upravitReceptBtn(ActionEvent event) {
		kuchyne.getAktualniSeznamReceptu().najdiRecept(vybrany).setSeznamSurovinReceptu(listNovychSurReceptu);


		((Node)(event.getSource())).getScene().getWindow().hide();
	}

	public void pridatReceptDoMenuBtn(ActionEvent event) {
		Recept receptNaMenu = kuchyne.getAktualniSeznamReceptu().najdiRecept(vybrany);
		receptNaMenu.setSeznamSurovinReceptu(listNovychSurReceptu);

		
		
		kuchyne.getAktualniMenu().vlozitRecept(receptNaMenu);
		setChanged();
		notifyObservers();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}

	public void smazatReceptBtn(ActionEvent event) {

		Recept receptKOdstraneni = kuchyne.getAktualniSeznamReceptu().najdiRecept(vybrany);
		kuchyne.getAktualniSeznamReceptu().getSeznamReceptu().remove(receptKOdstraneni);
		
		setChanged();
		notifyObservers();

		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	public void lzeJenUprvit(){
		smazatBtn.setDisable(true);
		menuBtn.setDisable(true);
	}
	
	
	/**
	 * Metoda zrychluje zobrazení tooltipu
	 * @param Tooltip
	 */
	public static void hackTooltipStartTiming(Tooltip tooltip) {
	    try {
	        Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
	        fieldBehavior.setAccessible(true);
	        Object objBehavior = fieldBehavior.get(tooltip);

	        Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
	        fieldTimer.setAccessible(true);
	        Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

	        objTimer.getKeyFrames().clear();
	        objTimer.getKeyFrames().add(new KeyFrame(new Duration(250)));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
