package leagueoflegend.application.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import leagueoflegend.application.controller.detailcontroller.IDetailController;
import leagueoflegend.model.GeneralInfo;
import java.io.IOException;

public class RelatedDataLoaderController<T extends GeneralInfo>{
    T data;

    public RelatedDataLoaderController(T data) {
        this.data = data;
    }

    public void loadData(String path, MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            IDetailController<T> controller = loader.getController();
            controller.setData(data);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
