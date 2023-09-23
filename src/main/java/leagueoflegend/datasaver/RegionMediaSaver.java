package leagueoflegend.datasaver;

import javafx.collections.ObservableList;
import leagueoflegend.executiondata.regiondata.RegionData;
import leagueoflegend.model.region.Region;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class RegionMediaSaver {
    public static void main(String[] args) {
        RegionData.loadJson();
        List<Region> regions = RegionData.getRegionData().getData();
        for(int i = 0; i < regions.size(); i++) {
            try {
                // Save region logo
                String path = "D:\\Code\\Java\\League of Legends\\LOLApplication\\src\\main\\resources\\image\\Region\\"+ regions.get(i).getName()+"\\";
                InputStream championScreen = new URL(regions.get(i).getImageURL()).openStream();
                Files.copy(championScreen, Paths.get(path + "logo.png"));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(regions.get(i).getName());
        }
    }
}
