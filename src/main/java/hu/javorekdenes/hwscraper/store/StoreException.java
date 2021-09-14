package hu.javorekdenes.hwscraper.store;

public class StoreException extends Exception {
	private static final String MSG = "Cannot save results to database";

	public StoreException() {
		super(MSG);
	}

	public StoreException(String message) {
		super(MSG + "See: " + message);
	}

	public StoreException(Throwable cause) {
		super(MSG, cause);
	}

	public StoreException(String message, Throwable cause) {
		super(MSG + "See: " + message, cause);
	}
}
