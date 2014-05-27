/**
 * 
 */
package fund.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;

/**
 * @author Saint Scott
 *
 */
public class InfoController extends AbstractFXController {

	@FXML
	private Parent rootLayout;

	/**
	 * 
	 */
	public InfoController() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fund.controller.AbstractFXController#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@FXML
	private void initialize() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fund.controller.AbstractFXController#getLayout()
	 */
	@Override
	public Parent getLayout() {
		// TODO Auto-generated method stub
		return rootLayout;
	}

}
