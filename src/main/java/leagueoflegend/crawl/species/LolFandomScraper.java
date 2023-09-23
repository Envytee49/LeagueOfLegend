package leagueoflegend.crawl.species;

import leagueoflegend.crawl.DataScraper;
import leagueoflegend.helper.CrawlHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

public class LolFandomScraper extends DataScraper implements ISpeciesScraper {
    public LolFandomScraper(Document speciesPage) {
        super(speciesPage);
    }

    @Override
    public String getName() {
        return getPage().selectFirst("h2[data-source='name']").text();
    }

    @Override
    public String getDescription() {

        Element dlTag = CrawlHelper.getDlTag(getPage());

        Element tableTag = CrawlHelper.getTableTag(getPage());

        if(tableTag != null) {
            return tableTag.text();
        }else if(dlTag != null) {
            return dlTag.text();
        }else {
            return CrawlHelper.getDescriptionWithNoDlAndTableTag(getPage());
        }
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
    public ArrayList<String> getTitle() {
        return CrawlHelper.getData(getPage(), "Designation","li:not(:has(s))");
    }

    @Override
    public ArrayList<String> getParentSpecies() {
        return CrawlHelper.getData(getPage(), "Parent Species","li:not(:has(s))");
    }

    @Override
    public ArrayList<String> getSubSpecies() {
        return CrawlHelper.getData(getPage(), "Subspecies","li:not(:has(s))");
    }

}
