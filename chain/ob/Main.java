package chain.ob;

public class Main {
	public static void main(String[] args) {
		ChainOfResponsability<Message> logger = //
				new ChainOfResponsability.Helper<>(Logger.CONSOLE_LOGGER) //
						.appendNext(Logger.EMAIL_LOGGER) //
						.appendNext(Logger.FILE_LOGGER) //
						.appendNext(Logger.DUMMY_LOGGER) //
						.build();

		// Handled by consoleLogger since the console has a LogLevel of all
		logger.acceptAndPass(new Message("Entering function ProcessOrder().", LogLevel.DEBUG));
		logger.acceptAndPass(new Message("Order record retrieved.", LogLevel.INFO));

		// Handled by consoleLogger and emailLogger since emailLogger implements
		// Functional_Error & Functional_Message
		logger.acceptAndPass(
				new Message("Unable to Process Order ORD1 Dated D1 For Customer C1.", LogLevel.FUNCTIONAL_ERROR));
		logger.acceptAndPass(new Message("Order Dispatched.", LogLevel.FUNCTIONAL_MESSAGE));

		// Handled by consoleLogger and fileLogger since fileLogger implements Warning &
		// Error
		logger.acceptAndPass(new Message("Customer Address details missing in Branch DataBase.", LogLevel.WARNING));
		logger.acceptAndPass(new Message("Customer Address details missing in Organization DataBase.", LogLevel.ERROR));

	}

}
