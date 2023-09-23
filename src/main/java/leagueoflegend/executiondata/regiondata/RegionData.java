package leagueoflegend.executiondata.regiondata;

import com.google.gson.reflect.TypeToken;
import leagueoflegend.executiondata.Data;
import leagueoflegend.helper.JsonIO;
import leagueoflegend.model.region.Region;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RegionData {
    private static final Type TOKEN = new TypeToken<ArrayList<Region>>() {}.getType();
    private static final String SOURCE_PATH = "src/main/resources/repository/region/region.json";
    private static Data<Region> regionData = new Data<>();
    private static int numOfRegion;
    public static void loadJson(){
        ArrayList<Region> data = (ArrayList<Region>) new JsonIO<Region>(TOKEN).loadJson(SOURCE_PATH);
        regionData.setData(data);
        numOfRegion = data.size();
    }

    public static Data<Region> getRegionData() {
        return regionData;
    }

    public static int getNumOfRegion() {
        return numOfRegion;
    }
}
