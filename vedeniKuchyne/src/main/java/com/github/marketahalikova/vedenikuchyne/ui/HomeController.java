package com.github.marketahalikova.vedenikuchyne.ui;

import java.util.List;

import com.github.marketahalikova.vedenikuchyne.logika.Kuchyne;
import com.github.marketahalikova.vedenikuchyne.logika.Recept;
import com.github.marketahalikova.vedenikuchyne.logika.SeznamReceptu;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;


public class HomeController extends GridPane {
	
	@FXML private ListView<String> seznamPredkrmu;
	@FXML private ListView<String> seznamKrmu;
	@FXML private ListView<String> seznamZakrmu;
	
	private Kuchyne kuchyne;
	
	public void inicializuj(Kuchyne kuchyne) {
		this.kuchyne = kuchyne;
		
		
		seznamZakrmu.getItems().addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("zakrm"));
		seznamKrmu.getItems().addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("krm"));
		seznamPredkrmu.getItems().addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("predkrm"));
		
		
	}
	
	
	

}
