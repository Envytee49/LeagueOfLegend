package leagueoflegend.executiondata.championdata;

import com.google.gson.reflect.TypeToken;
import leagueoflegend.executiondata.Data;
import leagueoflegend.helper.JsonIO;
import leagueoflegend.model.champion.Champion;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ChampionData {
    private static final Type TOKEN = new TypeToken<ArrayList<Champion>>() {}.getType();
    private static final String SOURCE_PATH = "src/main/resources/repository/champion/champion.json";
    private static Data<Champion> championData = new Data<>();
    private static int numOfChamps;
    public static void loadJson(){
        ArrayList<Champion> data = (ArrayList<Champion>) new JsonIO<Champion>(TOKEN).loadJson(SOURCE_PATH);
        championData.setData(data);
        numOfChamps = data.size();
    }

    public static Data<Champion> getChampionData() {
        return championData;
    }
    public static int getNumOfChamps() {
        return numOfChamps;
    }
}
