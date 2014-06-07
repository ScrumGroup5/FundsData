/**
 * 
 */
package fund.controller;

import fund.view.ScreensContainer;

/**
 * @author Saint Scott
 * 
 */
public abstract class AbstractFXController {

	protected ScreensContainer myScreensContainer;

	/**
	 * 
	 */
	public abstract void init();

	/**
	 * @param screensContainer
	 */
	public void setScreenContainer(ScreensContainer screensContainer) {
		myScreensContainer = screensContainer;
	};

}
