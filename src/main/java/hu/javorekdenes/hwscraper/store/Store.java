package hu.javorekdenes.hwscraper.store;

import java.util.List;

import hu.javorekdenes.hwscraper.search.model.Result;

public interface Store {
	/**
	 * Saves the results as one batch. Only creates the ones, that were not existing yet, without update.
	 * @param collectionName Collection / Table / File name to save the results to
	 * @param resultsToSave
	 * @throws StoreException Thrown on any issue, that makes the save impossible.
	 */
	void saveBatch(String collectionName, List<Result> resultsToSave) throws StoreException;
}
