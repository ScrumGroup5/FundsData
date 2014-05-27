/**
 * 
 */
package fund.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import fund.view.ScreensContainer;

/**
 * @author Saint Scott
 *
 */
public class RootController {

	private ScreensContainer mainContainer;
	public static final String ID_INFORMATION = "info-screen";
	public static final String file_info = "/fxml/information.fxml";

	@FXML
	private Button infoButton;
	@FXML
	private BorderPane rootLayout;

	/**
	 * 
	 */
	public RootController() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	private void initialize() {
		mainContainer = new ScreensContainer();
		mainContainer.loadScreen(RootController.ID_INFORMATION,
				RootController.file_info);
		mainContainer.setScreen(ID_INFORMATION);
		rootLayout.setCenter(mainContainer);
	}

	@FXML
	private void handleInfo(ActionEvent event) {
		mainContainer.setScreen(ID_INFORMATION);
	}

}
