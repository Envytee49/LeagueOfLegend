package leagueoflegend.datasaver;

import com.google.gson.reflect.TypeToken;
import leagueoflegend.crawl.champion.ChampionCrawler;
import leagueoflegend.crawl.region.RegionCrawler;
import leagueoflegend.crawl.species.SpeciesCrawler;
import leagueoflegend.executiondata.championdata.ChampionData;
import leagueoflegend.executiondata.regiondata.RegionData;
import leagueoflegend.helper.JsonIO;
import leagueoflegend.model.champion.Champion;
import leagueoflegend.model.region.Region;
import leagueoflegend.model.species.Species;

import java.util.ArrayList;
import java.util.List;

public class Main {
    // File path
    private static String path = "src/main/resources/repository/";
    private static String championFilePath = path + "champion/champion.json";
    private static String speciesFilePath = path + "species/species.json";
    private static String regionFilePath = path + "region/region.json";

    // Data list
    public static List<Champion> championList;
    public static List<Species> speciesList;
    public static List<Region> regionList;
    public static void saveChampion() {
        JsonIO<Champion> championJsonWriter = new JsonIO<>(new TypeToken<ArrayList<Champion>>() {}.getType());
        championJsonWriter.writeJson(championList, championFilePath);
    }
    public static  void saveRegion() {
        JsonIO<leagueoflegend.model.region.Region> regionJsonWriter = new JsonIO<>(new TypeToken<ArrayList<Region>>() {}.getType());
        regionJsonWriter.writeJson(regionList, regionFilePath);
    }
    public static void saveSpecies() {
        JsonIO<Species> speciesJsonWriter = new JsonIO<>(new TypeToken<ArrayList<Species>>() {}.getType());
        speciesJsonWriter.writeJson(speciesList, speciesFilePath);
    }
    public static void main(String[] args) {
            // Start crawling
            championList =  new ChampionCrawler().crawlChampions();
            speciesList = new SpeciesCrawler().crawlSpecies();
            regionList = new RegionCrawler().crawlRegions();

            // Load from json file
//            ChampionData.loadJson();
//            RegionData.loadJson();
//            championList = ChampionData.getChampionData().getData();
//            regionList = RegionData.getRegionData().getData();
            // Specify the id of objects
            DataLinker.linkChampListAnd(regionList, championList);
            DataLinker.linkChampListAnd(speciesList, championList);
            DataLinker.linkChampToChamp(championList);
            DataLinker.linkSubAndParentSpecies(speciesList);

            // Save to json file
            saveChampion();
            saveSpecies();
            saveRegion();

    }

}
