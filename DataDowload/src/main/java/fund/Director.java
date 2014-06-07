/**
 * 
 */
package fund;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Saint Scott
 *
 */
public class Director extends Application {

	private Stage primaryStage;
	public static final String ID_BASE = "rootlayout";
	public static final String file_base = "/fxml/base.fxml";
	public static final String ID_WELCOME = "welcome";
	public static final String file_welcome = "/fxml/Welcome.fxml";

	/**
	 * 
	 */
	public Director() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Agile Fund");

		FXMLLoader loader = new FXMLLoader(
				Director.class.getResource(file_welcome));
		Parent rootLayout = loader.load();

		// Group root = new Group();
		// root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
