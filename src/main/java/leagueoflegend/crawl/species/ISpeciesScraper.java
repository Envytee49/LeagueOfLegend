package leagueoflegend.crawl.species;

import leagueoflegend.crawl.IScraper;

import java.util.ArrayList;

public interface ISpeciesScraper extends IScraper {
    public ArrayList<String> getTitle();
    public ArrayList<String> getParentSpecies();
    public ArrayList<String> getSubSpecies();
}
