package leagueoflegend.application.controller.screencontroller.species;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import leagueoflegend.application.controller.RelatedDataLoaderController;
import leagueoflegend.executiondata.speciesdata.SpeciesData;
import leagueoflegend.model.species.Species;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SpeciesScreenController implements Initializable {
    private static List<Species> species = SpeciesData.getSpeciesData().getData();
    private static String speciesPath = "/view/detail/species/speciesDetail.fxml";
    @FXML
    private FlowPane speciesFlowpane;
    public void  setImageCss(ImageView speciesImage) {
        speciesImage.setPreserveRatio(false);
        speciesImage.setFitWidth(300);
        speciesImage.setFitHeight(200);
        AnchorPane.setTopAnchor(speciesImage, 0.0);
        AnchorPane.setBottomAnchor(speciesImage, 0.0);
        AnchorPane.setLeftAnchor(speciesImage, 0.0);
        AnchorPane.setRightAnchor(speciesImage, 0.0);
    }
    public ImageView getImage(String path) {
        ImageView regionImage = new ImageView(new Image(path));
        setImageCss(regionImage);
        return regionImage;
    }
    public void setSpeciesLabelCss(Label regionText) {
        regionText.setPadding(new Insets(10,0,10,0));
        regionText.setStyle("-fx-text-fill:#f0e6d2;-fx-font-size:24px;-fx-font-weight:bold;-fx-font-family:serif");
    }
    public Label getSpeciesLabel(String regionName) {
        Label speciesText = new Label(regionName.toUpperCase());
        setSpeciesLabelCss(speciesText);
        return speciesText;
    }
    public void setSpeciesLabelHBoxCss(HBox hBox) {
        hBox.setStyle("-fx-background-color:black");
        hBox.setAlignment(Pos.CENTER);
        AnchorPane.setBottomAnchor(hBox, 0.0); // Align at the bottom
        AnchorPane.setLeftAnchor(hBox, 0.0); // Align at the center
        AnchorPane.setRightAnchor(hBox, 0.0); // Align at the center
    }
    public HBox getSpeciesLabelHBox(Label regionText) {
        HBox hBox = new HBox(regionText);
        setSpeciesLabelHBoxCss(hBox);
        return hBox;
    }
    public void setSpeciesPaneCss(AnchorPane anchorPane) {
        anchorPane.setPrefWidth(300);
        anchorPane.setPrefHeight(100);
        anchorPane.setId("regionPane");
    }
    public void setSpeciesPaneOnMouseClick(AnchorPane anchorPane, Species specie) {
        anchorPane.setOnMouseClicked(event -> new RelatedDataLoaderController<>(specie).loadData(speciesPath, event));
    }
    public AnchorPane getSpeciesPane(ImageView regionImage, HBox hBox) {
        AnchorPane anchorPane = new AnchorPane();
        setSpeciesPaneCss(anchorPane);
        // Set image into anchor-pane
        anchorPane.getChildren().add(regionImage);
        // Set HBox into anchor-pane
        anchorPane.getChildren().add(hBox);
        return anchorPane;
    }
    public void setFlowPaneCss() {
        speciesFlowpane.setPadding(new Insets(20));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        species.sort((r1, r2) -> r1.getName().compareToIgnoreCase(r2.getName()));
        for(int i = 0; i < species.size(); i++) {

            Species specie = species.get(i);
            String specieName = specie.getName();
            String path = "/image/Species/"+specieName+"/"+"logo.png";

            ImageView specieImage = getImage(path);

            Label specieText = getSpeciesLabel(specieName);

            HBox hBox = getSpeciesLabelHBox(specieText);

            // Set anchor-pane
            AnchorPane anchorPane = getSpeciesPane(specieImage, hBox);
            setSpeciesPaneOnMouseClick(anchorPane, specie);

            // Set flow-pane
            speciesFlowpane.getChildren().add(anchorPane);
            setFlowPaneCss();
        }
    }
}
