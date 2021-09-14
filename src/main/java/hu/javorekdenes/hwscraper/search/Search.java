package hu.javorekdenes.hwscraper.search;

import java.util.List;

import hu.javorekdenes.hwscraper.search.model.Result;

public interface Search {
    /**
     * Executes the query and returns all the result pojos, found on that page.
     * @return List of results, with all of their fields filled.
     * @throws SearchException Thrown on any issue, that makes the search impossible.
     */
    List<Result> execute() throws SearchException;
}
