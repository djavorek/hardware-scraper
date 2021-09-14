package hu.javorekdenes.hwscraper.search;

public class SearchException extends Exception {
	private static final String MSG = "Cannot execute search as the requested page makes it impossible.";

	public SearchException() {
		super(MSG);
	}

	public SearchException(String message) {
		super(MSG + "See: " + message);
	}

	public SearchException(Throwable cause) {
		super(MSG, cause);
	}

	public SearchException(String message, Throwable cause) {
		super(MSG + "See: " + message, cause);
	}
}
