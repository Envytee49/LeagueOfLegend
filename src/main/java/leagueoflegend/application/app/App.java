package leagueoflegend.application.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import leagueoflegend.executiondata.championdata.ChampionData;
import leagueoflegend.executiondata.speciesdata.SpeciesData;
import leagueoflegend.executiondata.regiondata.RegionData;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(App.class.getResource("/view/startPane.fxml"));

        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(getClass().getResource("/image/lolicon.png").toExternalForm()));
        stage.setTitle("League of Legends");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        ChampionData.loadJson();
        RegionData.loadJson();
        SpeciesData.loadJson();
    }

    public static void main(String[] args) {
        launch(args);
//        System.out.println(App.class.getResource("/startPane.fxml"));
    }

}
