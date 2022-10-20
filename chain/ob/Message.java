package chain.ob;

public class Message {
	private final String content;
	private final LogLevel logLevel;

	public Message(String message, LogLevel logLevel) {
		this.content = message;
		this.logLevel = logLevel;
	}

	public String getContent() {
		return content;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

}
