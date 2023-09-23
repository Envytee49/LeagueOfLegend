package leagueoflegend.crawl.region;

import leagueoflegend.model.region.Region;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class RegionCrawler {

    public String getName(IRegionScraper regionPage) {
        return regionPage.getName();
    }

    public String getDescription(IRegionScraper regionPage) {
        return regionPage.getDescription();
    }

    public String getImageURL(IRegionScraper regionPage) {
        return regionPage.getImageURL();
    }

    public ArrayList<String> getRelatedChamps(IRegionScraper regionPage) {
        return regionPage.getRelatedChamps();
    }

    public ArrayList<String> getDemonym(IRegionScraper regionPage) {
        return regionPage.getDemonym();
    }

    public ArrayList<String> getEstablishmentHistory(IRegionScraper regionPage) {
        return regionPage.getEstablishmentHistory();
    }

    public ArrayList<Region> crawlRegions() {
        ArrayList<Region> regionsList = new ArrayList<>();

        try {
            ArrayList<String> fandomURL = RegionURLCrawler.getRegionFromFandom();

            for(int i = 0; i < fandomURL.size(); i++) {

                Document fandomPage = Jsoup.connect(fandomURL.get(i)).get();

                IRegionScraper fandomScraper = new LolFandomScraper(fandomPage);

                String name = getName(fandomScraper);
                String description = getDescription(fandomScraper);
                ArrayList<String> relatedChampions =getRelatedChamps(fandomScraper);
                String imageURL = getImageURL(fandomScraper);
                ArrayList<String> demonym = getDemonym(fandomScraper);
                ArrayList<String> establishmentHistory = getEstablishmentHistory(fandomScraper);

                regionsList.add(
                                new Region(
                                name,
                                description,
                                relatedChampions,
                                imageURL,
                                demonym,
                                establishmentHistory
                ));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return regionsList;
    }
}
