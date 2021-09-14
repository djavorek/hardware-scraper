package hu.javorekdenes.hwscraper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import com.google.cloud.functions.BackgroundFunction;
import com.google.cloud.functions.Context;
import com.google.events.cloud.pubsub.v1.Message;

import hu.javorekdenes.hwscraper.gcloud.GCloud;
import hu.javorekdenes.hwscraper.gcloud.GCloudAuthException;
import hu.javorekdenes.hwscraper.search.Search;
import hu.javorekdenes.hwscraper.search.SearchException;
import hu.javorekdenes.hwscraper.search.impl.SearchHardverapro;
import hu.javorekdenes.hwscraper.search.model.Query;
import hu.javorekdenes.hwscraper.search.model.Result;
import hu.javorekdenes.hwscraper.store.Store;
import hu.javorekdenes.hwscraper.store.StoreException;
import hu.javorekdenes.hwscraper.store.impl.FireStore;

public class Function implements BackgroundFunction<Message> {
    private static final Logger logger = Logger.getLogger(Function.class.getName());

    @Override
    public void accept(Message message, Context context) throws GCloudAuthException {
        if (message == null || message.getData() == null) {
            logger.severe("No message specified. Exiting");
            return;
        }

        GCloud cloud = new GCloud("hardverapro-notifier");

        String url = new String(Base64.getDecoder().decode(message.getData().getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8);
        logger.info(String.format("Querying url: %s", url));

        Search search = new SearchHardverapro(new Query(url));
        List<Result> results;

        try {
            results = search.execute();
        } catch (SearchException e) {
            logger.severe("Search was unsuccessful, aborting: " + e);
            return;
        }

        Store firestore = new FireStore(cloud.getFirestoreDB());
        try {
            firestore.saveBatch("videocards", results);
        } catch (StoreException e) {
            logger.severe("Could not save results: " + e);
            return;
        }
    }
}