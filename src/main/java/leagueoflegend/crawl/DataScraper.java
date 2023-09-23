package leagueoflegend.crawl;

import org.jsoup.nodes.Document;

public abstract class DataScraper {
    private Document page;

    public DataScraper(Document page) {
        this.page = page;
    }

    public Document getPage() {
        return page;
    }

    public void setPage(Document page) {
        this.page = page;
    }
}
