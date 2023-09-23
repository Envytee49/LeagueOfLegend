package leagueoflegend.application.controller.screencontroller.region;

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
import leagueoflegend.executiondata.regiondata.RegionData;
import leagueoflegend.model.region.Region;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegionScreenController implements Initializable {
    private static List<Region> regions = RegionData.getRegionData().getData();
    private static final String regionPath = "/view/detail/region/regionDetail.fxml";
    @FXML
    private FlowPane regionFlowpane;
    public void  setImageCss(ImageView regionImage) {
        regionImage.setPreserveRatio(true);
        regionImage.setFitWidth(300);
        AnchorPane.setTopAnchor(regionImage, 0.0);
        AnchorPane.setBottomAnchor(regionImage, 0.0);
        AnchorPane.setLeftAnchor(regionImage, 0.0);
        AnchorPane.setRightAnchor(regionImage, 0.0);
    }
    public ImageView getImage(String path) {
        ImageView regionImage = new ImageView(new Image(path));
        setImageCss(regionImage);
        return regionImage;
    }
    public void setRegionLabelCss(Label regionText) {
        regionText.setPadding(new Insets(10,0,10,0));
        regionText.setStyle("-fx-text-fill:#f0e6d2;-fx-font-size:24px;-fx-font-weight:bold;-fx-font-family:serif");
    }
    public Label getRegionLabel(String regionName) {
        Label regionText = new Label(regionName.toUpperCase());
        setRegionLabelCss(regionText);
        return regionText;
    }
    public void setRegionLabelHBoxCss(HBox hBox) {
        hBox.setStyle("-fx-background-color:black");
        hBox.setAlignment(Pos.CENTER);
        AnchorPane.setBottomAnchor(hBox, 0.0); // Align at the bottom
        AnchorPane.setLeftAnchor(hBox, 0.0); // Align at the center
        AnchorPane.setRightAnchor(hBox, 0.0); // Align at the center
    }
    public HBox getRegionLabelHBox(Label regionText) {
        HBox hBox = new HBox(regionText);
        setRegionLabelHBoxCss(hBox);
        return hBox;
    }
    public void setRegionPaneCss(AnchorPane anchorPane) {
        anchorPane.setPrefWidth(300);
        anchorPane.setPrefHeight(100);
        anchorPane.setId("regionPane");
    }
    public void setRegionPaneOnMouseClick(AnchorPane anchorPane, Region region) {
        anchorPane.setOnMouseClicked(event -> new RelatedDataLoaderController<>(region).loadData(regionPath, event));
    }
    public AnchorPane getRegionPane(ImageView regionImage, HBox hBox) {
        AnchorPane anchorPane = new AnchorPane();
        setRegionPaneCss(anchorPane);
        // Set image into anchor-pane
        anchorPane.getChildren().add(regionImage);
        // Set HBox into anchor-pane
        anchorPane.getChildren().add(hBox);
        return anchorPane;
    }
    public void setFlowPaneCss() {
        regionFlowpane.setPadding(new Insets(20));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        regions.sort((r1, r2) -> r1.getName().compareToIgnoreCase(r2.getName()));
        for(int i = 0; i < regions.size(); i++) {

            Region region = regions.get(i);
            String regionName = region.getName();
            String path = "/image/Region/"+regionName+"/"+regionName+".jpg";

            ImageView regionImage = getImage(path);

            Label regionText = getRegionLabel(regionName);

            HBox hBox = getRegionLabelHBox(regionText);

            // Set anchor-pane
            AnchorPane anchorPane = getRegionPane(regionImage, hBox);
            setRegionPaneOnMouseClick(anchorPane, region);

            // Set flow-pane
            regionFlowpane.getChildren().add(anchorPane);
            setFlowPaneCss();
        }
    }

}
