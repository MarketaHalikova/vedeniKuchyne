package com.github.marketahalikova.vedenikuchyne.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController extends GridPane implements Observer {

	@FXML
	private ListView < String > seznamPredkrmu;@FXML
	private ListView < String > seznamKrmu;@FXML
	private ListView < String > seznamZakrmu;@FXML
	private ListView < String > seznamSklad;@FXML
	private ListView < String > menuRecepty;@FXML
	private ListView < String > nakupniSeznam;

	@FXML
	private TabPane tabs;

	@FXML
	private Button upravaSuroviny;@FXML
	private Button smazaniSuroviny;

	@FXML
	private Alert chybaUlozeni;@FXML
	private Alert spravneUlozeni;

	private Kuchyne kuchyne;

	/**
	 * Metoda předává data kontroleru a nastavuje základní nastavení
	 * 
	 */
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

		upravaSuroviny.setDisable(true);
		smazaniSuroviny.setDisable(true);

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

		PridatReceptController c2 = loader. < PridatReceptController > getController();
		c2.inicializuj(kuchyne);
		c2.addObserver(this);

		Stage pridatReceptStage = new Stage();
		pridatReceptStage.setTitle("Přidat Recept");
		pridatReceptStage.setScene(new Scene(root));
		pridatReceptStage.initModality(Modality.APPLICATION_MODAL);
		pridatReceptStage.centerOnScreen();
		pridatReceptStage.setAlwaysOnTop(true);
		pridatReceptStage.show();
	}

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

		InventuraController c5 = loader. < InventuraController > getController();
		c5.inicializuj(kuchyne);
		c5.addObserver(this);

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

		NovaSurovinaController c4 = loader. < NovaSurovinaController > getController();
		c4.inicializuj(kuchyne);
		c4.addObserver(this);

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
		String vybrana = seznamSklad.getSelectionModel().getSelectedItem();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/UpravaSuroviny.fxml"));
		Parent root = loader.load();

		UpravaSurovinyController c3 = loader. < UpravaSurovinyController > getController();
		c3.nactiHodnoty(kuchyne, vybrana);
		c3.addObserver(this);

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

		UpravitReceptController c1 = loader. < UpravitReceptController > getController();
		c1.nactiHodnotu(vybrana, kuchyne, tabs);
		c1.addObserver(this);

		Stage UpravitReceptStage = new Stage();
		UpravitReceptStage.setTitle("Upravit Recept");
		UpravitReceptStage.setScene(new Scene(root));
		UpravitReceptStage.initModality(Modality.APPLICATION_MODAL);
		UpravitReceptStage.centerOnScreen();
		UpravitReceptStage.setAlwaysOnTop(true);
		UpravitReceptStage.show();

	}

	/**
	 * Metoda odkazuje na záložku Přidat recept
	 * 
	 */
	public void pridatRecept() {
		SingleSelectionModel < Tab > selectionModel = tabs.getSelectionModel();
		selectionModel.select(0);
	}

	/**
	 * Metoda odstraňuje surovinu ze skladu
	 * 
	 */
	public void odstranSurovinu() {
		String vybrana = seznamSklad.getSelectionModel().getSelectedItem();
		Surovina hledana = kuchyne.getAktualniSklad().najdiSurovinu(vybrana);
		kuchyne.getAktualniSklad().odstranSurovinu(hledana);

		// to do
	}

	/**
	 * Metoda odstraňuje recept z menu
	 * 
	 */
	public void odstranMenuRecept() {
		String vybrana = menuRecepty.getSelectionModel().getSelectedItem();
		Recept hledany = kuchyne.getAktualniMenu().najdiRecept(vybrana);
		kuchyne.getAktualniMenu().odstranRecept(hledany);

	}

	/**
	 * Metoda vrací aktuální nakupní seznam jako String
	 * 
	 */
	public List < String > porovnanySeznamMenu() {
		kuchyne.srovnaniSurovinMenuSeSkladem();
		return kuchyne.getAktualniNakupniSeznam().nakupniSeznamToString();

	}

	/**
	 * Metoda získá pole surovin jako String
	 * 
	 */
	public List < String > getSurovinyAsString(List < Surovina > listSurovin) {
		List < String > surovinyJakoString = new ArrayList < >();

		for (Surovina surovina: listSurovin) {
			String retezec = surovina.getNazev() + ", " + surovina.getMnozstvi() + ", " + surovina.getJednotka();
			surovinyJakoString.add(retezec);
		}

		return surovinyJakoString;
	}

	/**
	 * Metoda vygeneruje PDF dokument s obsahem nákupního listu
	 */
	@FXML
	public void exportNakup() throws DocumentException {

		String datum = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
		String cas = new SimpleDateFormat("dd.MM.yyyy-HH:mm:ss").format(new Date());

		ArrayList < String > nakupniList = new ArrayList < String > ();
		nakupniList.addAll(porovnanySeznamMenu());
		String nakupniList1 = nakupniList.toString().replaceAll("\\,", "\n");
		String nakupniList2 = nakupniList1.replaceAll("\\[", " ");
		String nakupniList3 = nakupniList2.replaceAll("\\]", "");

		Font nadpisFont = FontFactory.getFont(FontFactory.HELVETICA, 25, BaseColor.BLACK);
		Font nakupFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25, BaseColor.BLACK);
		Font miniFont = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.LIGHT_GRAY);

		Document nakup = new Document();

		try {
			PdfWriter.getInstance(nakup, new FileOutputStream("NakupniSeznam.pdf"));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(DocumentException e) {
			e.printStackTrace();
		}

		nakup.open();
		
		Paragraph malyNadpisMenu = new Paragraph("Tento dokument byl vygenerovaný aplikací MaKitch " + cas, miniFont);
		malyNadpisMenu.setAlignment(Element.ALIGN_RIGHT);
		nakup.add(malyNadpisMenu);
		nakup.add(new Paragraph("\n"));

		nakup.add(new Paragraph("Nákupní seznam ke dni " + datum + ":", nadpisFont));
		nakup.add(new Paragraph("\n"));
		nakup.add(new Paragraph(nakupniList3, nakupFont));

		nakup.close();

		File vygenerovanyNakup = new File("NakupniSeznam.pdf");
		try {
			Desktop.getDesktop().open(vygenerovanyNakup);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda vygeneruje PDF dokument s nabídkou aktuálního menu
	 */
	@FXML
	public void exportMenu() throws DocumentException {

		String datum = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
		String cas = new SimpleDateFormat("dd.MM.yyyy-HH:mm:ss").format(new Date());

		ArrayList < String > dostupneMenu = new ArrayList < String > ();
		dostupneMenu.addAll(kuchyne.getAktualniMenu().getNazvyReceptu());
		String dostupneMenu1 = dostupneMenu.toString().replaceAll("\\,", "\n");
		String dostupneMenu2 = dostupneMenu1.replaceAll("\\[", " ");
		String dostupneMenu3 = dostupneMenu2.replaceAll("\\]", "");

		Font nadpisFont = FontFactory.getFont(FontFactory.HELVETICA, 25, BaseColor.BLACK);
		Font nakupFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 28, BaseColor.BLACK);
		Font miniFont = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.LIGHT_GRAY);

		Document nakup = new Document();

		try {
			PdfWriter.getInstance(nakup, new FileOutputStream("Menu.pdf"));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(DocumentException e) {
			e.printStackTrace();
		}

		nakup.open();
		
		Paragraph malyNadpisMenu = new Paragraph("Tento dokument byl vygenerovaný aplikací MaKitch " + cas, miniFont);
		malyNadpisMenu.setAlignment(Element.ALIGN_RIGHT);
		nakup.add(malyNadpisMenu);
		nakup.add(new Paragraph("\n"));

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
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda nastavuje viditelnost buttonu
	 */
	@FXML
	public void viditelnostButtonu() {

		if (seznamSklad.getSelectionModel().isEmpty()) {
			upravaSuroviny.setDisable(true);
			smazaniSuroviny.setDisable(true);
		} else {
			upravaSuroviny.setDisable(false);
			smazaniSuroviny.setDisable(false);
		}
	}

	/**
	 * Metoda otevře okno O Vývojářích
	 */
	@FXML
	public void oVyvojarich() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/ONas.fxml"));
		Parent root = loader.load();

		Stage inventureStage = new Stage();
		inventureStage.setTitle("O Vývojářích");
		inventureStage.setScene(new Scene(root));
		inventureStage.initModality(Modality.APPLICATION_MODAL);
		inventureStage.centerOnScreen();
		inventureStage.setAlwaysOnTop(true);
		inventureStage.show();

	}

	/**
	 * Metoda, která volá metodu na uložení dat do souborů
	 * a upozornění pro uživatele, jestli se uložení zdařilo či nikoli
	 * 
	 */
	@FXML
	public void ulozZmeny() {
		try {
			kuchyne.ulozData();

			spravneUlozeni = new Alert(AlertType.INFORMATION);
			spravneUlozeni.setTitle("Uložení se zdařilo!");
			spravneUlozeni.setHeaderText(null);
			spravneUlozeni.setContentText("Data úspěšně uložena!");
			Stage stage = (Stage) spravneUlozeni.getDialogPane().getScene().getWindow();
			stage.setAlwaysOnTop(true);
			spravneUlozeni.showAndWait();
		} catch(Exception e) {
			chybaUlozeni = new Alert(AlertType.INFORMATION);
			chybaUlozeni.setTitle("Uložení se nezdařilo!");
			chybaUlozeni.setHeaderText(null);
			chybaUlozeni.setContentText("Omlouváme se, ale uložení dat se nezdařilo. Obraťte se, prosím, na vývojáře.");
			Stage stage = (Stage) chybaUlozeni.getDialogPane().getScene().getWindow();
			stage.setAlwaysOnTop(true);
			chybaUlozeni.showAndWait();
		}
	}

	/**
	 * Metoda otevření dokumentace aplikace na githubu v prohlížeči
	 * 
	 */
	@FXML
	public void odkazDokumentace() {
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.browse(new URI("https://github.com/MarketaHalikova/vedeniKuchyne/wiki"));
		} catch(IOException | URISyntaxException e2) {
			e2.printStackTrace();
		}
	}
	
	
	/**
	 * Metoda otevření HTML souboru nápovědy
	 * 
	 */
	@FXML
	public void napoveda() {
		Stage stage = new Stage();
        stage.setTitle("Nápověda");
        
        WebView view = new WebView();
        WebEngine napoveda = view.getEngine();

        napoveda.load(getClass().getResource("/napoveda.html").toExternalForm());

        stage.setScene(new Scene(view, 500, 500));
        stage.show();
	}
	
	

	@Override
	public void update(Observable arg0, Object arg1) {
		ObservableList < String > predkrmyList = FXCollections.observableArrayList();
		predkrmyList.addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("predkrm"));
		seznamPredkrmu.setItems(predkrmyList);
		ObservableList < String > krmyList = FXCollections.observableArrayList();
		krmyList.addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("krm"));
		seznamKrmu.setItems(krmyList);
		ObservableList < String > zakrmyList = FXCollections.observableArrayList();
		zakrmyList.addAll(kuchyne.getAktualniSeznamReceptu().getPodleKategorie("zakrm"));
		seznamZakrmu.setItems(zakrmyList);
		ObservableList < String > skladList = FXCollections.observableArrayList();
		skladList.addAll(kuchyne.getAktualniSklad().getSkladAsString().keySet());
		seznamSklad.setItems(skladList);
		ObservableList < String > menuList = FXCollections.observableArrayList();
		menuList.addAll(kuchyne.getAktualniMenu().getNazvyReceptu());
		menuRecepty.setItems(menuList);
		ObservableList < String > nakupList = FXCollections.observableArrayList();
		nakupList.addAll(porovnanySeznamMenu());
		nakupniSeznam.setItems(nakupList);

	}
}