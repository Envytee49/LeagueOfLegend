package leagueoflegend.crawl.champion;

import leagueoflegend.crawl.IScraper;
import leagueoflegend.model.champion.Ability;

import java.util.ArrayList;

public interface IChampionScraper extends IScraper {
    public String getTitle();
    public String getRole();
    public String getDifficulty();
    public ArrayList<Ability> getAbility();
    public ArrayList<String> getSpecies();
    public ArrayList<String> getRegion();
}
