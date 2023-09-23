package leagueoflegend.crawl.champion;

import leagueoflegend.crawl.DataScraper;
import leagueoflegend.helper.CrawlHelper;
import leagueoflegend.model.champion.Ability;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class LolFandomScraper extends DataScraper implements IChampionScraper {

    public LolFandomScraper(Document championPage) {
        super(championPage);
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getRole() {
        return null;
    }

    @Override
    public String getDifficulty() {
        return null;
    }

    @Override
    public String getDescription() {
        Elements allTagsBeforeContentTable = getPage().selectFirst("div.toc").previousElementSiblings();
        Elements allPTags = allTagsBeforeContentTable.select("p");
        return allPTags.select("p").text();
    }

    @Override
    public String getImageURL() {
        return null;
    }

    @Override
    public ArrayList<Ability> getAbility() {
        return null;
    }

    public ArrayList<String> getSpecies() {
        return CrawlHelper.getData(getPage(),"Species","a:not(s a)");
    }
    public ArrayList<String> getRegion() {
        return CrawlHelper.getData(getPage(),"Region", "a:not(s a)");
    }

    public ArrayList<String> getRelatedChamps() {
        String divOfContentCssQuery =   "div[style='display:grid; grid-template-columns: 1fr 1fr 1fr;" +
                            " font-weight:400; text-align:center;'] a";

        Elements championsGrid = getPage().select(divOfContentCssQuery);

        ArrayList<String> relatedChamps = new ArrayList<>();

        for(Element element : championsGrid)
            relatedChamps.add(element.text());

        relatedChamps.removeIf(String::isEmpty);

        return relatedChamps;
    }

}
