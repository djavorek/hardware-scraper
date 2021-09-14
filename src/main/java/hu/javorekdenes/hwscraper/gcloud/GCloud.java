package hu.javorekdenes.hwscraper.gcloud;

import java.io.IOException;
import java.util.logging.Logger;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

public class GCloud {
	private static final Logger logger = Logger.getLogger(GCloud.class.getName());
	private String projectId;

	private Firestore firestoreDB;

	public GCloud(String projectId) {
		this.projectId = projectId;
	}

	public Firestore getFirestoreDB() throws GCloudAuthException {
		if (this.firestoreDB != null) {
			return this.firestoreDB;
		}

		FirestoreOptions firestoreOptions = null;
		try {
			firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder().setProjectId(this.projectId)
					.setCredentials(GoogleCredentials.getApplicationDefault()).build();
		} catch (IOException e) {
			logger.severe("Cannot find application default service key.");
			throw new GCloudAuthException(e);
		}

		this.firestoreDB = firestoreOptions.getService();
		return this.firestoreDB;
	}
}
