package com.github.marketahalikova.vedenikuchyne.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UpravitReceptController {
	
	/**
	 * Metoda otevření okna nápověda jednotek
	 * @throws IOException
	 */
	@FXML public void otevritNapoveda() throws IOException {
		
		//provizorní řešení
		
		StackPane newScene = new StackPane();
		Scene scene = new Scene(newScene, 300, 100);
        Stage provizorni = new Stage();
        provizorni.setScene(scene);
        provizorni.setAlwaysOnTop(true);
        provizorni.centerOnScreen();
        provizorni.show();
        
    }

}
