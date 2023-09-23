package leagueoflegend.crawl.species;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class SpeciesURLCrawler {
    private static final String fandomURL = "https://leagueoflegends.fandom.com/wiki/Species";

    public static ArrayList<String> getSpeciesFromFandom() throws IOException {
        Document allSpeciesPage = Jsoup.connect(fandomURL).get();

        Element tableOfSpecies = allSpeciesPage.selectFirst("table.article-table");

        Elements aTagsOfSpecies = tableOfSpecies.select("td a");

        ArrayList<String> speciesURL = new ArrayList<>();

        for(Element specieURl : aTagsOfSpecies) {
            if(specieURl.text().equals("Unnamed Species")) continue;
            String speciesLink = "https://leagueoflegends.fandom.com" + specieURl.attr("href");
            if(!speciesURL.contains(speciesLink))
                speciesURL.add(speciesLink);
        }

        return speciesURL;
    }

}
