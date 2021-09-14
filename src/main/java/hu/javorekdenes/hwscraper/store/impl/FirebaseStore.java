package hu.javorekdenes.hwscraper.store.impl;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.firestore.WriteResult;

import hu.javorekdenes.hwscraper.search.model.Result;
import hu.javorekdenes.hwscraper.store.Store;
import hu.javorekdenes.hwscraper.store.StoreException;

public class FirebaseStore implements Store {
	private static final Logger logger = Logger.getLogger(FirebaseStore.class.getName());

	private Firestore firestore;

	public FirebaseStore(Firestore firestore) {
		this.firestore = firestore;
	}

	@Override
	public void saveBatch(String collectionName, List<Result> resultsToSave) throws StoreException {
		CollectionReference collection = this.firestore.collection(collectionName);

		WriteBatch batch = this.firestore.batch();

		for (Result result : resultsToSave) {
			DocumentReference document = collection.document(result.getId());
			batch.create(document, result);
		}

		ApiFuture<List<WriteResult>> batchFuture = batch.commit();

		try {
			batchFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new StoreException(e);
		}

		logger.info("All results saved.");
	}
}
