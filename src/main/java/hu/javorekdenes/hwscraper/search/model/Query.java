package hu.javorekdenes.hwscraper.search.model;

public class Query {
    private final String url;

    public Query(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
