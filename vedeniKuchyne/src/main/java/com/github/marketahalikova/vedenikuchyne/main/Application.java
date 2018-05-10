package com.github.marketahalikova.vedenikuchyne.main;

import com.github.marketahalikova.vedenikuchyne.ui.HomeController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

	/**
	 * Spouštěcí metoda pro aplikaci
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
            launch(args);
        }
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass()
		          .getResource("/Home.fxml"));
		Parent root = loader.load();

		HomeController controller = loader.getController();

		
      primaryStage.setTitle("MaKitch");
      primaryStage.setScene(new Scene(root));
      primaryStage.show();
		
	}

		
}