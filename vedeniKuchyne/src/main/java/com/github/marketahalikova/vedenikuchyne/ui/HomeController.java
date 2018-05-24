package com.github.marketahalikova.vedenikuchyne.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.github.marketahalikova.vedenikuchyne.logika.Kuchyne;
import com.github.marketahalikova.vedenikuchyne.logika.Recept;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
		nakupniSeznam.getItems().addAll(porovnanySeznamMenu());

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
		c1.addObserver(this);

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


	public List<String> porovnanySeznamMenu() {
		kuchyne.srovnaniSurovinMenuSeSkladem();
		return kuchyne.getAktualniNakupniSeznam().nakupniSeznamToString();

	}

	public List<String> getSurovinyAsString(List<Surovina> listSurovin) {
		List<String> surovinyJakoString = new ArrayList<>();

		for (Surovina surovina : listSurovin) {
			String retezec = surovina.getNazev() + ", " + surovina.getMnozstvi() + ", " + surovina.getJednotka();
			surovinyJakoString.add(retezec);
		}

		return surovinyJakoString;
	}
	
	
	/*
	 * Metoda vygeneruje PDF dokument s obsahem nákupního listu
	 */
	@FXML
	public void exportNakup() throws DocumentException {

		String datum = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		ArrayList<String> nakupniList = new ArrayList<String>();
		nakupniList.addAll(porovnanySeznamMenu());
		String nakupniList1 = nakupniList.toString().replaceAll("\\,", "\n");
		String nakupniList2 = nakupniList1.replaceAll("\\[", "");
		String nakupniList3 = nakupniList2.replaceAll("\\]", "");
		
		Font nadpisFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 22, BaseColor.DARK_GRAY);
		Font nakupFont = FontFactory.getFont(FontFactory.TIMES, 20, BaseColor.BLACK);
		
		Document nakup = new Document();
		
		try {
			PdfWriter.getInstance(nakup, new FileOutputStream("NakupniSeznam.pdf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		 
		nakup.open();
		
		nakup.add(new Paragraph("Nákupní seznam ke dni " + datum + ":", nadpisFont));
		nakup.add(new Paragraph("\n"));
		nakup.add(new Paragraph(nakupniList3, nakupFont));

		nakup.close();
		
		File vygenerovanyNakup = new File("NakupniSeznam.pdf");
        try {
			Desktop.getDesktop().open(vygenerovanyNakup);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Metoda vygeneruje PDF dokument s nabídkou aktuálního menu
	 */
	@FXML
	public void exportMenu() throws DocumentException {

		String datum = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		ArrayList<String> dostupneMenu = new ArrayList<String>();
		dostupneMenu.addAll(kuchyne.getAktualniMenu().getNazvyReceptu());
		String dostupneMenu1 = dostupneMenu.toString().replaceAll("\\,", "\n");
		String dostupneMenu2 = dostupneMenu1.replaceAll("\\[", "");
		String dostupneMenu3 = dostupneMenu2.replaceAll("\\]", "");
		
		Font nadpisFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 22, BaseColor.DARK_GRAY);
		Font nakupFont = FontFactory.getFont(FontFactory.TIMES, 22, BaseColor.BLACK);
		
		Document nakup = new Document();
		
		try {
			PdfWriter.getInstance(nakup, new FileOutputStream("Menu.pdf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		 
		nakup.open();
		
		nakup.add(new Paragraph("Menu ke dni " + datum + ":", nadpisFont));
		nakup.add(new Paragraph("\n"));
		nakup.add(new Paragraph("\n"));
		Paragraph polozkyMenu = new Paragraph(dostupneMenu3, nakupFont);
		polozkyMenu.setAlignment(Element.ALIGN_CENTER);
		nakup.add(polozkyMenu);

		nakup.close();
		
		File vygenerovaneMenu = new File("Menu.pdf");
        try {
			Desktop.getDesktop().open(vygenerovaneMenu);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		nakupList.addAll(porovnanySeznamMenu());
		nakupniSeznam.setItems(nakupList);

	}
}
