package leagueoflegend.datasaver;

import javafx.collections.ObservableList;
import leagueoflegend.executiondata.championdata.ChampionData;
import leagueoflegend.model.champion.Champion;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ChampionMediaSaver {
    public static void main(String[] args) {

        ChampionData.loadJson();
        List<Champion> champions = ChampionData.getChampionData().getData();

        for(int i = 0; i < champions.size(); i++) {
            String path = "D:\\Code\\Java\\League of Legends\\LOLApplication\\src\\main\\resources\\image\\Champion\\"
                    + champions.get(i).getName();
            File f = new File(path);
            boolean bool = f.mkdir();
            if(bool){
                System.out.println("Folder is created successfully");
            }else{
                System.out.println("Error Found!");
            }
            try{
                // Save champion images
                InputStream championScreen = new URL(champions.get(i).getMainScreenImage()).openStream();
                Files.copy(championScreen, Paths.get(path+"\\"+champions.get(i).getName()+"-screen.png"));

                InputStream champion = new URL(champions.get(i).getImageURL()).openStream();
                Files.copy(champion, Paths.get(path+"\\"+champions.get(i).getName()+".png"));

                // Save champion ability images
                String passive = champions.get(i).getAbilities().get(0).getImageURL();
                String Q = champions.get(i).getAbilities().get(1).getImageURL();
                String W = champions.get(i).getAbilities().get(2).getImageURL();
                String E = champions.get(i).getAbilities().get(3).getImageURL();
                String R = champions.get(i).getAbilities().get(4).getImageURL();

                InputStream passiveImage = new URL(passive).openStream();
                Files.copy(passiveImage, Paths.get(path+"\\"+champions.get(i).getName()+"passive.png"));

                InputStream qImage = new URL(Q).openStream();
                Files.copy(qImage, Paths.get(path+"\\"+champions.get(i).getName()+"Q.png"));

                InputStream wImage = new URL(W).openStream();
                Files.copy(wImage, Paths.get(path+"\\"+champions.get(i).getName()+"W.png"));

                InputStream eImage = new URL(E).openStream();
                Files.copy(eImage, Paths.get(path+"\\"+champions.get(i).getName()+"E.png"));

                InputStream rImage = new URL(R).openStream();
                Files.copy(rImage, Paths.get(path+"\\"+champions.get(i).getName()+"R.png"));

                // Save champion ability videos
                String passiveVid = champions.get(i).getAbilities().get(0).getVideoURL();
                String QVID = champions.get(i).getAbilities().get(1).getVideoURL();
                String WVID = champions.get(i).getAbilities().get(2).getVideoURL();
                String EVID = champions.get(i).getAbilities().get(3).getVideoURL();
                String RVID = champions.get(i).getAbilities().get(4).getVideoURL();

                // Some champions does not have video description
                if(!passiveVid.isEmpty()) {
                    InputStream passiveVideo = new URL(passiveVid).openStream();
                    Files.copy(passiveVideo, Paths.get(path + "\\" + champions.get(i).getName() + "PASSIVE_VIDEO.mp4"));
                }else System.out.println(champions.get(i).getName() + "error");
                if(!QVID.isEmpty()) {
                    InputStream qVideo = new URL(QVID).openStream();
                    Files.copy(qVideo, Paths.get(path + "\\" + champions.get(i).getName() + "Q_VIDEO.mp4"));
                }else System.out.println(champions.get(i).getName() + "error");
                if(!WVID.isEmpty()) {
                    InputStream wVideo = new URL(WVID).openStream();
                    Files.copy(wVideo, Paths.get(path + "\\" + champions.get(i).getName() + "W_VIDEO.mp4"));
                }else System.out.println(champions.get(i).getName() + "error");
                if(!EVID.isEmpty()) {
                    InputStream eVideo = new URL(EVID).openStream();
                    Files.copy(eVideo, Paths.get(path + "\\" + champions.get(i).getName() + "E_VIDEO.mp4"));
                }else System.out.println(champions.get(i).getName() + "error");
                if(!RVID.isEmpty()) {
                    InputStream rVideo = new URL(RVID).openStream();
                    Files.copy(rVideo, Paths.get(path + "\\" + champions.get(i).getName() + "R_VIDEO.mp4"));
                }else System.out.println(champions.get(i).getName() + "error");

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
