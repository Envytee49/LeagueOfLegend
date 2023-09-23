package leagueoflegend.application.controller.detailcontroller.species;

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
import leagueoflegend.model.species.Species;
import java.util.Map;

public class SpeciesDetailController implements IDetailController<Species> {
    private Species species;
    private String SPECIES_NAME;
    public void setSpeciesName() {
        SPECIES_NAME = species.getName();
    }
    public void setBackgroundImage() {
        String backgroundImagePath = "/image/Species/"+SPECIES_NAME+"/"+"logo.png";
        backgroundImage.setImage(new Image(backgroundImagePath));
    }
    @FXML
    private ImageView backgroundImage;

    @FXML
    private Label description;

    @FXML
    private Label name;

    @FXML
    private Label speciesText;

    @FXML
    private FlowPane relatedChampFlowPane;

    public void setNameLabel() {
        name.setText(species.getName().toUpperCase());
    }
    public void setSpeciesText() {
        speciesText.setText(species.getName().toUpperCase());
    }
    public void setDescription() {
        description.setText(species.getDescription());
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
        Map<String, Integer> relatedChampions = species.getRelatedChampions();
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
    @Override
    public void setData(Species data) {
        this.species = data;
        setUp();
    }
    @Override
    public void setUp() {
        setSpeciesName();
        setSpeciesText();
        setDescription();
        setBackgroundImage();
        setNameLabel();
        setRelatedChampFlowPane();
    }
}
