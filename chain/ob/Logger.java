package chain.ob;

import java.util.Arrays;
import java.util.EnumSet;

public abstract class Logger extends ChainOfResponsability<Message> {
	public static final Logger CONSOLE_LOGGER = new Logger(LogLevel.all()) {

		@Override
		protected void acceptContentMessage(String content) {
			System.err.println("Displaying to console: " + content);

		}
	};
	public static final Logger EMAIL_LOGGER = new Logger(LogLevel.FUNCTIONAL_MESSAGE, LogLevel.FUNCTIONAL_ERROR) {

		@Override
		protected void acceptContentMessage(String content) {
			System.err.println("Sending eMail: " + content);

		}
	};
	public static final Logger FILE_LOGGER = new Logger(LogLevel.WARNING, LogLevel.ERROR) {

		@Override
		protected void acceptContentMessage(String content) {
			System.err.println("Writing file: " + content);

		}
	};
	public static final Logger DUMMY_LOGGER = new Logger(LogLevel.all()) {

		@Override
		protected void acceptContentMessage(String content) {
			System.out.println("Dummy: " + content);

		}
	};

	private final EnumSet<LogLevel> logLevels;

	protected Logger(LogLevel... logLevels) {
		this.logLevels = EnumSet.copyOf(Arrays.asList(logLevels));
	}

	@Override
	public void accept(Message msg) {
		LogLevel logLevel = msg.getLogLevel();
		if (logLevels.contains(logLevel)) {
			acceptContentMessage(msg.getContent());
		}
	}

	protected abstract void acceptContentMessage(String content);

}
