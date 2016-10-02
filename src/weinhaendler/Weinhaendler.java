package weinhaendler;

import org.apache.log4j.Logger;

import gui.GuiKunde;

public class Weinhaendler {

	/**
	 * @param args
	 */
	
	private static Logger logger = Logger.getLogger(Weinhaendler.class);

	
	public static void main(String[] args) {
		logger.info("Application started");
		new GuiKunde();
	}

}
