package com.github.marketahalikova.vedenikuchyne.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import com.github.marketahalikova.vedenikuchyne.logika.Kuchyne;
import com.github.marketahalikova.vedenikuchyne.logika.Recept;
import com.github.marketahalikova.vedenikuchyne.logika.SeznamReceptu;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController extends GridPane implements Observer {

	@FXML
	private ListView<String> seznamPredkrmu;
	@FXML
	private ListView<String> seznamKrmu;
	@FXML
	private ListView<String> seznamZakrmu;
	@FXML
	private ListView<String> seznamSklad;
	@FXML
	private ListView<String> menuRecepty;
	@FXML
	private ListView<String> nakupniSeznam;

	@FXML
	private TabPane tabs;

	private Kuchyne kuchyne;

	public void inicializuj(Kuchyne kuchyne) {
		this.kuchyne = kuchyne;

		seznamZakrmu.getItems().addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("zakrm"));
		seznamKrmu.getItems().addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("krm"));
		seznamPredkrmu.getItems().addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("predkrm"));
		seznamSklad.getItems().addAll(kuchyne.getAktualniSklad().getSkladAsString().keySet());
		menuRecepty.getItems().addAll(kuchyne.getAktualniMenu().getNazvyReceptu());
		nakupniSeznam.getItems().addAll(porovnanySeznam());

		kuchyne.getAktualniSeznamReceptu().addObserver(this);
		kuchyne.getAktualniSklad().addObserver(this);
		kuchyne.getAktualniMenu().addObserver(this);

	}

	/**
	 * Metoda otevření nového okna Přidat recept
	 * 
	 * @throws IOException
	 */
	@FXML
	public void otevritPridatRecept() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/PridatRecept.fxml"));
		Parent root = loader.load();

		Stage pridatReceptStage = new Stage();
		pridatReceptStage.setTitle("Přidat Recept");
		pridatReceptStage.setScene(new Scene(root));
		pridatReceptStage.initModality(Modality.APPLICATION_MODAL);
		pridatReceptStage.centerOnScreen();
		pridatReceptStage.setAlwaysOnTop(true);
		pridatReceptStage.show();
	}

	/*
	 * public void novyRecept() { // to do
	 * kuchyne.getAktualniSeznamReceptu().vlozitRecept(new Recept("Smažáček",
	 * "Hmmm...dezertíček", "zakrm")); }
	 */

	/**
	 * Metoda otevření nového okna Inventura
	 * 
	 * @throws IOException
	 */
	@FXML
	public void otevritInventura() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Inventura.fxml"));
		Parent root = loader.load();

		Stage inventureStage = new Stage();
		inventureStage.setTitle("Inventura");
		inventureStage.setScene(new Scene(root));
		inventureStage.initModality(Modality.APPLICATION_MODAL);
		inventureStage.centerOnScreen();
		inventureStage.setAlwaysOnTop(true);
		inventureStage.show();
	}

	/**
	 * Metoda otevření nového okna Nova surovina
	 * 
	 * @throws IOException
	 */
	@FXML
	public void otevritNovaSurovina() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/NovaSurovina.fxml"));
		Parent root = loader.load();

		Stage NovaSurovinaStage = new Stage();
		NovaSurovinaStage.setTitle("Přidat Surovinu");
		NovaSurovinaStage.setScene(new Scene(root));
		NovaSurovinaStage.initModality(Modality.APPLICATION_MODAL);
		NovaSurovinaStage.centerOnScreen();
		NovaSurovinaStage.setAlwaysOnTop(true);
		NovaSurovinaStage.show();
	}

	/**
	 * Metoda otevření nového okna Uprava surovina
	 * 
	 * @throws IOException
	 */
	@FXML
	public void otevritUpravaSuroviny() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/UpravaSuroviny.fxml"));
		Parent root = loader.load();

		Stage UpravaSurovinyStage = new Stage();
		UpravaSurovinyStage.setTitle("Upravit Surovinu");
		UpravaSurovinyStage.setScene(new Scene(root));
		UpravaSurovinyStage.initModality(Modality.APPLICATION_MODAL);
		UpravaSurovinyStage.centerOnScreen();
		UpravaSurovinyStage.setAlwaysOnTop(true);
		UpravaSurovinyStage.show();
	}

	/**
	 * Metoda otevření nového okna Upravit Recept
	 * 
	 * @throws IOException
	 */
	@FXML
	public void otevritUpravitRecept() throws IOException {
		String vybranaPredkrm = null;
		String vybranaKrm = null;
		String vybranaZakrm = null;
		String vybrana = null;

		vybranaPredkrm = seznamPredkrmu.getSelectionModel().getSelectedItem();
		vybranaKrm = seznamKrmu.getSelectionModel().getSelectedItem();
		vybranaZakrm = seznamZakrmu.getSelectionModel().getSelectedItem();

		if (vybranaPredkrm != null) {
			vybrana = vybranaPredkrm;
		} else {
			if (vybranaKrm != null) {
				vybrana = vybranaKrm;
			} else {
				vybrana = vybranaZakrm;
			}

		}
		seznamPredkrmu.getSelectionModel().clearSelection();
		seznamKrmu.getSelectionModel().clearSelection();
		seznamZakrmu.getSelectionModel().clearSelection();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/UpravitRecept.fxml"));
		Parent root = loader.load();
		
		UpravitReceptController c1 = loader.<UpravitReceptController>getController();
		c1.nactiHodnotu(vybrana, kuchyne);
		

		Stage UpravitReceptStage = new Stage();
		UpravitReceptStage.setTitle("Upravit Recept");
		UpravitReceptStage.setScene(new Scene(root));
		UpravitReceptStage.initModality(Modality.APPLICATION_MODAL);
		UpravitReceptStage.centerOnScreen();
		UpravitReceptStage.setAlwaysOnTop(true);
		UpravitReceptStage.show();

		
	}


	public void pridatRecept() {
		SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
		selectionModel.select(0);
	}

	public void odstranSurovinu() {
		String vybrana = seznamSklad.getSelectionModel().getSelectedItem();
		Surovina hledana = kuchyne.getAktualniSklad().najdiSurovinu(vybrana);
		kuchyne.getAktualniSklad().odstranSurovinu(hledana);

		// to do
	}

	public void odstranMenuRecept() {
		String vybrana = menuRecepty.getSelectionModel().getSelectedItem();
		Recept hledany = kuchyne.getAktualniMenu().najdiRecept(vybrana);
		kuchyne.getAktualniMenu().odstranRecept(hledany);

	}

	//// budu dodelavat/////////////////////////////////////////////////////
	public List<String> porovnanySeznam() {

		List<Surovina> listSkladSurovin = kuchyne.getAktualniSklad().getSeznamSurovinSkladu();
		List<Recept> listMenuReceptu = kuchyne.getAktualniMenu().getSeznamReceptu();
		List<Surovina> listMenuSurovin = new ArrayList<>();

		int len = listMenuReceptu.size();
		for (int i = 0; i < len; i++) {
			listMenuSurovin.addAll(listMenuReceptu.get(i).getSeznamSurovinReceptu());
			System.out.println(listMenuSurovin);
		}

		return getSurovinyAsString(listMenuSurovin);

	}

	public List<String> getSurovinyAsString(List<Surovina> listSurovin) {
		List<String> surovinyJakoString = new ArrayList<>();

		for (Surovina surovina : listSurovin) {
			String retezec = surovina.getNazev() + ", " + surovina.getMnozstvi() + ", " + surovina.getJednotka();
			surovinyJakoString.add(retezec);
		}

		return surovinyJakoString;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		ObservableList<String> predkrmyList = FXCollections.observableArrayList();
		predkrmyList.addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("predkrm"));
		seznamPredkrmu.setItems(predkrmyList);
		ObservableList<String> krmyList = FXCollections.observableArrayList();
		krmyList.addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("krm"));
		seznamKrmu.setItems(krmyList);
		ObservableList<String> zakrmyList = FXCollections.observableArrayList();
		zakrmyList.addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("zakrm"));
		seznamZakrmu.setItems(zakrmyList);
		ObservableList<String> skladList = FXCollections.observableArrayList();
		skladList.addAll(kuchyne.getAktualniSklad().getSkladAsString().keySet());
		seznamSklad.setItems(skladList);
		ObservableList<String> menuList = FXCollections.observableArrayList();
		menuList.addAll(kuchyne.getAktualniMenu().getNazvyReceptu());
		menuRecepty.setItems(menuList);
		ObservableList<String> nakupList = FXCollections.observableArrayList();
		nakupList.addAll(porovnanySeznam());
		nakupniSeznam.setItems(nakupList);
	}
}
