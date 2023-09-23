package leagueoflegend.application.controller.detailcontroller.region;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import leagueoflegend.application.controller.RelatedDataLoaderController;
import leagueoflegend.application.controller.detailcontroller.IDetailController;
import leagueoflegend.executiondata.championdata.ChampionData;
import leagueoflegend.model.champion.Champion;
import leagueoflegend.model.region.Region;
import java.util.Map;

public class RegionDetailController implements IDetailController<Region> {
    Region region;
    private String REGION_NAME;
    @FXML
    private Label description;

    @FXML
    private Label name;

    @FXML
    private ImageView symbol;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private Label regionText;

    @FXML
    private FlowPane relatedChampFlowPane;
    public void setRegionName() {
        REGION_NAME = region.getName();
    }
    public void setBackgroundImage() {
        String backgroundImagePath = "/image/Region/"+REGION_NAME+"/"+REGION_NAME+".jpg";
        backgroundImage.setImage(new Image(backgroundImagePath));
    }
    public void setSymbol() {
        String symbolPath = "/image/Region/"+REGION_NAME+"/"+"symbol.png";
        symbol.setImage(new Image(symbolPath));
    }
    public void setNameLabel() {
        name.setText(region.getName().toUpperCase());
    }
    public void setRegionText() {
        regionText.setText(region.getName().toUpperCase());
    }
    public void setDescription() {
        description.setText(region.getDescription());
        description.setMaxWidth(900);
        description.setWrapText(true);
    }
    public void setRelatedChampCss(ImageView championImage) {
        championImage.setPreserveRatio(true);
        championImage.setFitWidth(230);
        championImage.setFitHeight(400);
    }
    public void setRelatedChampOnMouseClick(ImageView championImage, Integer championID) {
        championImage.setOnMouseClicked(event -> {
            Champion relatedChampion = ChampionData.getChampionData().getById(championID);
            new RelatedDataLoaderController<>(relatedChampion).loadData("/view/detail/champion/championDetail.fxml",event);
        });
    }
    public ImageView getRelatedChampionImage(String championName, Integer championID) {
        String championImagePath = "/image/Champion/" + championName + "/" + championName + "-screen.png";
        ImageView championImage = new ImageView(new Image(championImagePath));
        setRelatedChampCss(championImage);
        setRelatedChampOnMouseClick(championImage, championID);
        return championImage;
    }
    public void setRelatedChampLabelCss(Label championLabel, ImageView championImage) {
        championLabel
                .setStyle("-fx-font-size:14px;" +
                        " -fx-font-family:serif;" +
                        "-fx-text-fill:#937341;" +
                        "-fx-border-color:#937341 transparent transparent transparent;");
        championLabel.setPadding(new Insets(10,0,10,0));
        championLabel.setPrefWidth(championImage.getFitWidth());
        championLabel.setAlignment(Pos.CENTER);
    }
    public Label getRelatedChampionLabel(String championName, ImageView championImage) {
        Label championLabel = new Label(championName.toUpperCase());
        setRelatedChampLabelCss(championLabel, championImage);
        return championLabel;
    }
    public void setChampionBoxCss(VBox championBox) {
        championBox.setId("region-related-champ-image");
        championBox.setAlignment(Pos.CENTER);
    }
    public VBox getChampionBox(ImageView championImage, Label championLabel ) {
        VBox championBox = new VBox(championImage, championLabel);
        setChampionBoxCss(championBox);
        return  championBox;
    }
    public void setFlowPaneCss() {
        relatedChampFlowPane.setPadding(new Insets(10));
        relatedChampFlowPane.setAlignment(Pos.CENTER);
    }
    public void setRelatedChampFlowPane() {
        Map<String, Integer> relatedChampions = region.getRelatedChampions();
        for(Map.Entry<String, Integer> entry : relatedChampions.entrySet()) {
            Integer championID = entry.getValue();
            boolean championExist = championID != 0;
            if(championExist) {
                String championName = entry.getKey();
                // Get champ info
                ImageView championImage = getRelatedChampionImage(championName, championID);
                Label championLabel = getRelatedChampionLabel(championName, championImage);
                // Adding name and image to vbox
                VBox championBox = getChampionBox(championImage, championLabel);
                // Add champion to flow-pane
                relatedChampFlowPane.getChildren().add(championBox);
                setFlowPaneCss();
            }
        }
    }
     public void setUp() {
        setRegionName();
        setRegionText();
        setDescription();
        setBackgroundImage();
        setNameLabel();
        setSymbol();
        setRelatedChampFlowPane();
    }
    public void setData(Region region) {
        this.region = region;
        setUp();
    }

}
