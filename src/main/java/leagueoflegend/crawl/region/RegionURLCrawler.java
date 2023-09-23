package leagueoflegend.crawl.region;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class RegionURLCrawler {
    public static final String fandomURL = "https://leagueoflegends.fandom.com/wiki/Category:Places";

    public static ArrayList<String> getRegionFromFandom() throws IOException {
        Document allRegionPage = Jsoup.connect(fandomURL).get();
        Elements aTagOfRegions = allRegionPage.select("div#gallery-0 div.lightbox-caption a");
        ArrayList<String> regionsURL = new ArrayList<>();
        for(Element regionURL : aTagOfRegions) {
            regionsURL.add("https://leagueoflegends.fandom.com"+regionURL.attr("href"));
        }

        return regionsURL;
    }
}
