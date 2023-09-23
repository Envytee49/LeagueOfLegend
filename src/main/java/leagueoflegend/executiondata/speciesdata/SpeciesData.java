package leagueoflegend.executiondata.speciesdata;
import com.google.gson.reflect.TypeToken;
import leagueoflegend.executiondata.Data;
import leagueoflegend.helper.JsonIO;
import leagueoflegend.model.species.Species;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SpeciesData {
    private static final Type TOKEN = new TypeToken<ArrayList<Species>>() {}.getType();
    private static final String SOURCE_PATH = "src/main/resources/repository/species/species.json";
    private static Data<Species> speciesData = new Data<>();
    private static int numOfSpecies;
    public static void loadJson(){
        ArrayList<Species> data = (ArrayList<Species>) new JsonIO<Species>(TOKEN).loadJson(SOURCE_PATH);
        speciesData.setData(data);
        numOfSpecies = data.size();
    }

    public static Data<Species> getSpeciesData() {
        return speciesData;
    }

    public static int getNumOfSpecies() {
        return numOfSpecies;
    }
}
