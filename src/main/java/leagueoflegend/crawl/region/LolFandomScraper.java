package leagueoflegend.crawl.region;

import leagueoflegend.crawl.DataScraper;
import leagueoflegend.helper.CrawlHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class LolFandomScraper extends DataScraper implements IRegionScraper {
    public LolFandomScraper(Document regionPage) {
        super(regionPage);
    }

    @Override
    public String getName() {
        return getPage().selectFirst("h2[data-source='name']").text();
    }

    @Override
    public String getDescription() {
        return getPage().selectFirst("table[cellspacing='0'] + p").text();
    }

    @Override
    public String getImageURL() {
        return getPage().selectFirst("figure.pi-item img").attr("src");
    }

    @Override
    public ArrayList<String> getRelatedChamps() {
        return  CrawlHelper.getRelatedChamps(getPage());
    }


    @Override
    public ArrayList<String> getDemonym() {
        return CrawlHelper.getData(getPage(),"Demonym", "li:not(:has(s))");
    }

    @Override
    public ArrayList<String> getEstablishmentHistory() {
        return CrawlHelper.getData(getPage(), "Establishment history", "li:not(:has(s))");
    }

}
