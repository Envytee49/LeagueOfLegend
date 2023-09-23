package leagueoflegend.crawl.region;

import leagueoflegend.crawl.IScraper;

import java.util.ArrayList;

public interface IRegionScraper extends IScraper {
    public ArrayList<String> getDemonym();
    public ArrayList<String> getEstablishmentHistory();
}
