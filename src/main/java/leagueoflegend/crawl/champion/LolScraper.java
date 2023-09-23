package leagueoflegend.crawl.champion;

import leagueoflegend.crawl.DataScraper;
import leagueoflegend.model.champion.Ability;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import leagueoflegend.helper.CrawlHelper;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;


public class LolScraper extends DataScraper implements IChampionScraper {
    public LolScraper(Document championPage) {
        super(championPage);
    }

    public String getTitle() {
        String titleCssQuery = "span.style__Intro-sc-1h71ys8-2";
        return getPage().selectFirst(titleCssQuery).text();
    }

    public String getName() {
        String nameCssQuery = "span[data-testid='overview:title']";
        return getPage().selectFirst(nameCssQuery).text();
    }

    public String getRole() {
        String roleCssQuery = "div[data-testid='overview:role']";
        return getPage().selectFirst(roleCssQuery).text();
    }

    public String getDifficulty() {
        String difficultyCssQuery = "div[data-testid='overview:difficulty']";
        return getPage().selectFirst(difficultyCssQuery).text();
    }

    public String getDescription() {
        String descriptionCssQuery = "p[data-testid='overview:description']";
        return getPage().selectFirst(descriptionCssQuery).text();
    }
    public String getImageURL() {
        return getPage()
                .selectFirst("div[data-testid='overview:backgroundimage'] > img")
                .attr("src");
    }
    public ArrayList<Ability> getAbility() {
        int finalAbilityIndex = CrawlHelper.getLastIndexOfAbility(getPage());

        ArrayList<Ability> abilities = new ArrayList<>();
        System.out.println(getPage().selectFirst("span[data-testid='overview:title']").text());
        for(int i = 0 ; i <= finalAbilityIndex; i++){
            String abilityCssQuery = "h6[data-testid='abilities:ability:position-"+ i + "']";
            String abilityNameCssQuery = "h5[data-testid='abilities:name-"+ i + "']";
            String abilityDescriptionCssQuery = "p[data-testid='abilities:description-"+ i + "']";
            String imageURLCssQuery = "button[data-testid='abilities:selector-" + i + "']> span > span > img";
            String videoURLCssQuery = "div > video[data-index='"+ i +"'] > source:nth-child(2)";

            String ability = getPage().selectFirst(abilityCssQuery).text();
            String abilityName = getPage().selectFirst(abilityNameCssQuery).text();
            String abilityDescription = getPage().selectFirst(abilityDescriptionCssQuery).text();
            String imageURL = getPage().selectFirst(imageURLCssQuery).attr("src");

            String videoURL = "";
            if(getPage().selectFirst(videoURLCssQuery) != null)
                videoURL = getPage().selectFirst(videoURLCssQuery).attr("src");

            abilities.add(
                new Ability(
                    ability,
                    abilityName,
                    abilityDescription,
                    imageURL,
                    videoURL)
                );
        }
        return  abilities;
    }

    @Override
    public ArrayList<String> getSpecies() {
        return null;
    }

    @Override
    public ArrayList<String> getRegion() {
        return null;
    }

    @Override
    public ArrayList<String> getRelatedChamps() {
        return null;
    }



}
