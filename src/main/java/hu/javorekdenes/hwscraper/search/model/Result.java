package hu.javorekdenes.hwscraper.search.model;

public class Result {
    private String id;
    private String name;
    private String price;
    private String dateString;
    private String url;

    public Result() {
    }

    public Result(String id, String name, String price, String dateString, String url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.dateString = dateString;
        this.url = url;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDateString() {
        return this.dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Result [id=" + id + ", name=" + name + "]";
    }
}
