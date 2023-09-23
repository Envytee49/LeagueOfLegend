package leagueoflegend.crawl.champion;

import leagueoflegend.model.champion.Ability;
import leagueoflegend.model.champion.Champion;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;


public class ChampionCrawler  {

    public String getTitle(IChampionScraper championScraper){
        return championScraper.getTitle();
    }
    public String getName(IChampionScraper championScraper){
        return championScraper.getName();
    }
    public String getRole(IChampionScraper championScraper){
        return championScraper.getRole();
    }
    public String getDifficulty(IChampionScraper championScraper){
        return  championScraper.getDifficulty();
    }
    public String getDescription(IChampionScraper championScraper){
        return championScraper.getDescription();
    }
    public String getImageURL(IChampionScraper championScraper){
        return championScraper.getImageURL();
    }
    public ArrayList<Ability> getAbility(IChampionScraper championScraper){
        return championScraper.getAbility();
    }
    public ArrayList<String> getSpecies(IChampionScraper championScraper){
        return championScraper.getSpecies();
    }
    public ArrayList<String> getRegion(IChampionScraper championScraper){
        return championScraper.getRegion();
    }
    public ArrayList<String> getRelatedChamps(IChampionScraper championScraper){
        return championScraper.getRelatedChamps();
    }
    public ArrayList<Champion> crawlChampions(){
        ArrayList<Champion> championsList = new ArrayList<>();
        try {
            ArrayList<String> lolURL = ChampionURLCrawler.getChampionsURLFromLOL();
            ArrayList<String> fandomURL = ChampionURLCrawler.getChampionsURLFromFandom();
            ArrayList<String> lolImage = ChampionURLCrawler.getChampionImageFromLOL();
            for(int i = 0; i < lolURL.size(); i++) {
                Document lolPage = Jsoup.connect(lolURL.get(i)).timeout(0).get();
                Document fandomPage = Jsoup.connect(fandomURL.get(i)).timeout(0).get();

                IChampionScraper lolScraper = new LolScraper(lolPage);
                IChampionScraper fandomScraper = new LolFandomScraper(fandomPage);

                String title = getTitle(lolScraper);
                String name = getName(lolScraper);
                String description = getDescription(fandomScraper);
                String role = getRole(lolScraper);
                String difficulty = getDifficulty(lolScraper);
                String imageURL = getImageURL(lolScraper);
                String mainScreenImage = lolImage.get(i);
                ArrayList<String> species = getSpecies(fandomScraper);
                ArrayList<Ability> abilities = getAbility(lolScraper);
                ArrayList<String> relatedChampions = getRelatedChamps(fandomScraper);
                ArrayList<String> relatedRegion = getRegion(fandomScraper);

                championsList.add(
                                new Champion(
                                name,
                                description,
                                relatedChampions,
                                imageURL,
                                mainScreenImage,
                                title,
                                role,
                                difficulty,
                                species,
                                abilities,
                                relatedRegion));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return championsList;
    }

}
