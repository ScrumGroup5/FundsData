/**
 * 
 */
package fund.controller;

import java.io.IOException;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import org.controlsfx.dialog.Dialogs;

import fund.model.DataDownloader;

/**
 * @author Saint Scott
 *
 */
public class WelcomeController {

	private final String file_base = "/fxml/base.fxml";

	@FXML
	private Parent rootLayout;

	@FXML
	private Button goButton;

	@FXML
	private void initialize() {
		goButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Task<Boolean> task = new Task<Boolean>() {

					@Override
					protected void succeeded() {
						// TODO Auto-generated method stub

						FXMLLoader loader = new FXMLLoader(getClass()
								.getResource(file_base));

						try {
							Parent root = loader.load();
							rootLayout.getScene().setRoot(root);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					@Override
					protected Boolean call() throws Exception {
						// TODO Auto-generated method stub
						new DataDownloader().download();
						return true;
					}

				};

				Dialogs.create().owner(rootLayout).title("Progress Dialog")
						.masthead("Downloading Author 's information!")
						.showWorkerProgress(task);

				new Thread(task).start();
			}
		});
	}
}
