package hu.javorekdenes.hwscraper.gcloud;

public class GCloudAuthException extends Exception {
	private static final String MSG = "Cannot connect to Google Cloud due to authentication issue.";

	public GCloudAuthException() {
		super(MSG);
	}

	public GCloudAuthException(String message) {
		super(MSG + "See: " + message);
	}

	public GCloudAuthException(Throwable cause) {
		super(MSG, cause);
	}

	public GCloudAuthException(String message, Throwable cause) {
		super(MSG + "See: " + message, cause);
	}
}
