package com.github.marketahalikova.vedenikuchyne.ui;

import java.util.List;

import java.util.Observable;
import java.util.Observer;

import com.github.marketahalikova.vedenikuchyne.logika.Kuchyne;
import com.github.marketahalikova.vedenikuchyne.logika.Recept;
import com.github.marketahalikova.vedenikuchyne.logika.SeznamReceptu;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

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
	private TabPane tabs;

	private Kuchyne kuchyne;

	public void inicializuj(Kuchyne kuchyne) {
		this.kuchyne = kuchyne;

		seznamZakrmu.getItems().addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("zakrm"));
		seznamKrmu.getItems().addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("krm"));
		seznamPredkrmu.getItems().addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("predkrm"));
		seznamSklad.getItems().addAll(kuchyne.getAktualniSklad().getSkladAsString().keySet());
		menuRecepty.getItems().addAll(kuchyne.getAktualniMenu().getNazvyReceptu());

		kuchyne.getAktualniSeznamReceptu().addObserver(this);
		kuchyne.getAktualniSklad().addObserver(this);
		kuchyne.getAktualniMenu().addObserver(this);

		
	}

	public void novyRecept() {
		// to do
		kuchyne.getAktualniSeznamReceptu().vlozitRecept(new Recept("Smažáček", "Hmmm...dezertíček", "zakrm"));
	}
	
	public void pridatRecept() {
		SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
		selectionModel.select(0);
	}
	
	public void odstranSurovinu() {
		String vybrana = seznamSklad.getSelectionModel().getSelectedItem();
		Surovina hledana = kuchyne.getAktualniSklad().najdiSurovinu(vybrana);
		kuchyne.getAktualniSklad().odstranSurovinu(hledana);
		
		//to do
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
		

	}

}
