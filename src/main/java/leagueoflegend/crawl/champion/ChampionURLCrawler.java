package leagueoflegend.crawl.champion;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public abstract class ChampionURLCrawler {
    private static final String lolURL = "https://www.leagueoflegends.com/en-us/champions/";
    private static final String fandomURL = "https://leagueoflegends.fandom.com/wiki/List_of_champions";

    public static ArrayList<String> getChampionsURLFromLOL() throws IOException {
        Document allChampionPage = Jsoup.connect(lolURL).get();
        String aTagOfChampion = "a.style__Wrapper-sc-n3ovyt-0";
        Elements aTagsOfChampionsURL = allChampionPage.select(aTagOfChampion);
        ArrayList<String> championsURL = new ArrayList<>();
        for (Element championURL : aTagsOfChampionsURL) {
            championsURL.add("https://www.leagueoflegends.com"+championURL.attr("href"));
        }
        championsURL.remove("https://www.leagueoflegends.com/en-us/champions/briar/");
        return championsURL;
    }

    public static ArrayList<String> getChampionsURLFromFandom() throws IOException {
        Document allChampionPage = Jsoup.connect(fandomURL).timeout(0).get();
        Element tableOfChampions = allChampionPage.selectFirst("table.article-table");
        Elements aTagsOfChampionsURL = tableOfChampions.select("tr > td:nth-of-type(1)");
        ArrayList<String> championsURL = new ArrayList<>();
        for(Element element : aTagsOfChampionsURL){
            championsURL.add("https://leagueoflegends.fandom.com/wiki/" + element.attr("data-sort-value"));
        }
        return championsURL;
    }
    public static ArrayList<String> getChampionImageFromLOL() throws IOException {
        Document allChampionPage = Jsoup.connect(lolURL).timeout(0).get();
        String imageTagOfChampion = "div.style__List-sc-13btjky-2.IorQY img[style=\"object-fit:cover\"][class=\"style__NoScriptImg-sc-g183su-0 style__Img-sc-g183su-1 gXSlUo cHnjvq\"]";
        Elements imageTagsOfChampions = allChampionPage.select(imageTagOfChampion);
        ArrayList<String> championsImage = new ArrayList<>();
        for (Element championImage: imageTagsOfChampions) {
            championsImage.add(championImage.attr("src"));
        }
        championsImage.remove("https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt8d315d0ddcc546e6/64ffcd579e7ba67795caac6e/RiotX_ChampionList_briar_v2.jpg?quality=90&width=250");
        return championsImage;
    }

}
