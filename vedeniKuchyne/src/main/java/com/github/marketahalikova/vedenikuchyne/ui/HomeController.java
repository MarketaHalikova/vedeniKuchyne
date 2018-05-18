package com.github.marketahalikova.vedenikuchyne.ui;

import java.util.List;

import java.util.Observable;
import java.util.Observer;

import com.github.marketahalikova.vedenikuchyne.logika.Kuchyne;
import com.github.marketahalikova.vedenikuchyne.logika.Recept;
import com.github.marketahalikova.vedenikuchyne.logika.SeznamReceptu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
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

	private Kuchyne kuchyne;

	public void inicializuj(Kuchyne kuchyne) {
		this.kuchyne = kuchyne;

		seznamZakrmu.getItems().addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("zakrm"));
		seznamKrmu.getItems().addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("krm"));
		seznamPredkrmu.getItems().addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("predkrm"));
		seznamSklad.getItems().addAll(kuchyne.getAktualniSklad().getSkladAsString().values());

		kuchyne.getAktualniSeznamReceptu().addObserver(this);

	}

	public void pridejRecept() {
		// to do
		kuchyne.getAktualniSeznamReceptu().vlozitRecept(new Recept("Smažáček", "Hmmm...dezertíček", "zakrm"));
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
		/*
		 * ObservableList<String> skladList = FXCollections.observableArrayList();
		 * skladList.addAll(kuchyne.getAktualniSklad().getSkladAsString().values());
		 * seznamSklad.setItems(skladList);
		 */

	}

}
