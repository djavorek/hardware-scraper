package hu.javorekdenes.hwscraper.search.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hu.javorekdenes.hwscraper.search.Search;
import hu.javorekdenes.hwscraper.search.SearchException;
import hu.javorekdenes.hwscraper.search.model.Query;
import hu.javorekdenes.hwscraper.search.model.Result;

public class SearchHardverapro implements Search {
	private static final Logger logger = Logger.getLogger(Search.class.getName());

	private Query query;
	private String timestampOfQuery;

	public SearchHardverapro(Query query) {
		this.query = query;
		this.timestampOfQuery = new Timestamp(System.currentTimeMillis()).toString();
	}

	public List<Result> execute() throws SearchException {
		Document page = null;
		try {
			page = Jsoup.connect(query.getUrl()).cookie("prf_ls_uad", "time.d.200.normal") // Paging: Newest first, 200 item / page
					.get();
		} catch (IOException e) {
			throw new SearchException("Cannot load page on search url: " + query.getUrl(), e);
		}

		List<Result> results = new LinkedList<>();
		Element resultContainerElement = page.getElementById("center").getElementsByClass("uad-list").get(0);
		Elements resultElements = resultContainerElement.getElementsByAttribute("data-uadid");

		if (resultElements.size() == 0) {
			return results;
		}

		for (Element resultElement : resultElements) {
			Result result = new Result();

			Element itemTitle = resultElement.getElementsByClass("uad-title").get(0);
			Element link = itemTitle.getElementsByTag("a").get(0);

			result.setId(resultElement.attr("data-uadid"));
			result.setUrl(link.attr("href"));
			result.setName(link.text());
			result.setPrice(resultElement.getElementsByClass("uad-price").get(0).text());
			result.setDateString(timestampOfQuery);

			results.add(result);
		}

		logger.info("Search Done. Found: " + results.size());
		return results;
	}
}
