package leagueoflegend.crawl.species;

import leagueoflegend.model.GeneralInfo;
import leagueoflegend.model.species.Species;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

public class SpeciesCrawler {

    public String getName(ISpeciesScraper speciesPage) {
        return speciesPage.getName();
    }

    public String getDescription(ISpeciesScraper speciesPage) {
        return speciesPage.getDescription();
    }

    public String getImageURL(ISpeciesScraper speciesPage) {
        return speciesPage.getImageURL();
    }

    public ArrayList<String> getRelatedChamps(ISpeciesScraper speciesPage) {
        return speciesPage.getRelatedChamps();
    }

    public ArrayList<String> getTitle(ISpeciesScraper speciesPage) {
        return speciesPage.getTitle();
    }

    public ArrayList<String> getParentSpecies(ISpeciesScraper speciesPage) {
        return speciesPage.getParentSpecies();
    }

    public ArrayList<String> getSubSpecies(ISpeciesScraper speciesPage) {
        return speciesPage.getSubSpecies();
    }
    public  ArrayList<Species> crawlSpecies() {
        ArrayList<Species> speciesList = new ArrayList<>();

        try {
            ArrayList<String> fandomURL = SpeciesURLCrawler.getSpeciesFromFandom();
            for(int i = 0; i < fandomURL.size(); i++) {
//                if(i == 10) break;

                Document fandomPage = Jsoup.connect(fandomURL.get(i)).get();

                ISpeciesScraper fandomScraper = new LolFandomScraper(fandomPage);

                String name = getName(fandomScraper);
                String description = getDescription(fandomScraper);
                ArrayList<String> relatedChampions = getRelatedChamps(fandomScraper);
                String imageURL = getImageURL(fandomScraper);
                ArrayList<String> title = getTitle(fandomScraper);
                ArrayList<String> parentSpecies = getParentSpecies(fandomScraper);
                ArrayList<String> subSpecies = getSubSpecies(fandomScraper);

                Species species =   new Species(
                                    name,
                                    description,
                                    relatedChampions,
                                    imageURL,
                                    title,
                                    parentSpecies,
                                    subSpecies
                );
                if(!speciesList.contains(species))
                    speciesList.add(species);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return speciesList;
    }
}
