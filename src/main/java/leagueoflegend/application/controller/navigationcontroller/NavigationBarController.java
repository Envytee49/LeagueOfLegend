package leagueoflegend.application.controller.navigationcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import leagueoflegend.application.controller.detailcontroller.champion.ChampionDetailController;
import leagueoflegend.application.controller.screencontroller.mainscreen.MainScreenController;

import java.io.IOException;


public class NavigationBarController {

    public void switchScene(ActionEvent event, String path) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.centerOnScreen();
            ChampionDetailController.stopVideo();
            MainScreenController.stopRoleVideo();
            MainScreenController.stopGameplayVideo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void switchToChamp(ActionEvent event)  {
        switchScene(event, "/view/allscreen/champion/championScreen.fxml");
    }
    @FXML
    void switchToHome(ActionEvent event) {
        switchScene(event, "/view/allscreen/mainscreen/mainScreen.fxml");
    }

    @FXML
    void switchToRegion(ActionEvent event) {
        switchScene(event, "/view/allscreen/region/regionScreen.fxml");
    }

//    @FXML
//    void switchToSpecies(ActionEvent event) {
//        switchScene(event, "/view/allscreen/species/speciesScreen.fxml");
//    }
}
