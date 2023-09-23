package leagueoflegend.datasaver;

import leagueoflegend.executiondata.regiondata.RegionData;
import leagueoflegend.executiondata.speciesdata.SpeciesData;
import leagueoflegend.model.region.Region;
import leagueoflegend.model.species.Species;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SpeciesMediaSaver {
    public static void main(String[] args) throws IOException {
        SpeciesData.loadJson();
        List<Species> species = SpeciesData.getSpeciesData().getData();
        for(int i = 0; i < species.size(); i++) {

                // Save region logo
                String path = "D:\\Code\\Java\\League of Legends\\LOLApplication\\src\\main\\resources\\image\\Species\\"+ species.get(i).getName()+"\\";
                InputStream championScreen = new URL(species.get(i).getImageURL()).openStream();
                Files.copy(championScreen, Paths.get(path + "logo.png"));
            System.out.println(species.get(i).getName());
        }
    }
}
