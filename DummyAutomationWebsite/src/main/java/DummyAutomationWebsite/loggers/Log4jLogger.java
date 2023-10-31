package DummyAutomationWebsite.loggers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jLogger implements ILogger {

	private Logger log4jLogger;
	
	public Log4jLogger(Class<?> clazz) {
		this.log4jLogger = LogManager.getLogger(clazz);
	}
	
	@Override
	public void trace(String message) {
		this.log4jLogger.trace(message);
	}

	@Override
	public void debug(String message) {
		this.log4jLogger.debug(message);
	}

	@Override
	public void info(String message) {
		this.log4jLogger.info(message);
	}

	@Override
	public void warn(String message) {
		this.log4jLogger.warn(message);
	}

	@Override
	public void error(String message) {
		this.log4jLogger.error(message);
	}

	@Override
	public void fatal(String message) {
		this.log4jLogger.fatal(message);
	}
}
