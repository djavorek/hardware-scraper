package hu.javorekdenes.hwscraper.store.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import hu.javorekdenes.hwscraper.search.model.Result;
import hu.javorekdenes.hwscraper.store.Store;
import hu.javorekdenes.hwscraper.store.StoreException;

public class FireStore implements Store {
	private static final Logger logger = Logger.getLogger(FireStore.class.getName());

	private Firestore firestore;

	public FireStore(Firestore firestore) {
		this.firestore = firestore;
	}

	@Override
	public void saveBatch(String collectionName, List<Result> resultsToSave) throws StoreException {
		CollectionReference collection = this.firestore.collection(collectionName);
		List<ApiFuture<WriteResult>> createFutures = new ArrayList<>();

		for (Result result : resultsToSave) {
			DocumentReference document = collection.document(result.getId());
			createFutures.add(document.create(result));
		}

		for (ApiFuture<WriteResult> future : createFutures) {
			try {
				future.get();
				logger.info("New document saved successfully.");
			} catch (InterruptedException e) {
				logger.warning("Save ran into interuption");
				throw new StoreException(e);
			} catch (ExecutionException e) {
				// logger.warning("Execution problem, probably document already exists: " + e.getMessage());
			}
		}

		logger.info("Result save finished.");
	}
}
