package leagueoflegend.application.controller.detailcontroller.champion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import leagueoflegend.application.controller.RelatedDataLoaderController;
import leagueoflegend.application.controller.detailcontroller.IDetailController;
import leagueoflegend.application.controller.navigationcontroller.NavigationBarController;
import leagueoflegend.executiondata.championdata.ChampionData;
import leagueoflegend.executiondata.regiondata.RegionData;
import leagueoflegend.model.champion.Ability;
import leagueoflegend.model.champion.Champion;
import leagueoflegend.model.region.Region;
import java.io.File;
import java.util.Map;

public class ChampionDetailController implements IDetailController<Champion> {
    private Champion champion;
    private String CHAMPION_NAME;
    private Ability passive;
    private Ability Q;
    private Ability W;
    private Ability E;
    private Ability R;
    @FXML
    private Label name;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label difficulty;
    @FXML
    private Label role;
    @FXML
    private ImageView mainScreenImage;
    @FXML
    private ImageView passiveImage;
    @FXML
    private ImageView qImage;
    @FXML
    private ImageView eImage;
    @FXML
    private ImageView wImage;
    @FXML
    private ImageView rImage;
    @FXML
    private Button passiveButton;
    @FXML
    private Button qButton;
    @FXML
    private Button wButton;
    @FXML
    private Button eButton;
    @FXML
    private Button rButton;
    private Button currentButton;
    private String CHAMPION_IMAGE;
    private String PASSIVE_IMAGE;
    private String Q_IMAGE;
    private String W_IMAGE ;
    private String E_IMAGE;
    private String R_IMAGE;

    @FXML
    private Label ability;
    @FXML
    private Label abilityName;
    @FXML
    private Label abilityDescription;
    @FXML
    private MediaView abilityVideo;

    private String PASSIVE_VIDEO;
    private String Q_VIDEO;
    private String W_VIDEO;
    private String E_VIDEO;
    private String R_VIDEO;
    @FXML
    private ImageView noDisplay;
    private static MediaPlayer pMediaPlayer;
    private static MediaPlayer qMediaPlayer;
    private static MediaPlayer wMediaPlayer;
    private static MediaPlayer eMediaPlayer;
    private static MediaPlayer rMediaPlayer;
    private static MediaPlayer currentMediaPlayer;
    private String imagePath = "/image/Champion/";
    private String videoPath = "src/main/resources/image/Champion/";

    @FXML
    private FlowPane relatedChampFlowPane;
    @FXML
    private FlowPane speciesFlowPane;
    @FXML
    private ImageView regionImage;
    @FXML
    private Label regionText;

    public  void  setChampionName() {
         CHAMPION_NAME  = champion.getName();
    }
    public void setAbility() {
        passive = champion.getAbilities().get(0);
        Q = champion.getAbilities().get(1);
        W = champion.getAbilities().get(2);
        E = champion.getAbilities().get(3);
        R = champion.getAbilities().get(4);
    }
    public void setImagePath() {
        imagePath = imagePath + CHAMPION_NAME + "/";
        CHAMPION_IMAGE = imagePath + CHAMPION_NAME + ".png";
        PASSIVE_IMAGE = imagePath + CHAMPION_NAME + "passive.png";
        Q_IMAGE = imagePath + CHAMPION_NAME + "Q.png";
        W_IMAGE = imagePath + CHAMPION_NAME + "W.png";
        E_IMAGE = imagePath + CHAMPION_NAME + "E.png";
        R_IMAGE = imagePath + CHAMPION_NAME + "R.png";
    }
    public void setVideoPath() {
        videoPath = videoPath + CHAMPION_NAME + "/";
        PASSIVE_VIDEO = videoPath + CHAMPION_NAME + "PASSIVE_VIDEO.mp4";
        Q_VIDEO = videoPath + CHAMPION_NAME + "Q_VIDEO.mp4";
        W_VIDEO = videoPath + CHAMPION_NAME + "W_VIDEO.mp4";
        E_VIDEO = videoPath + CHAMPION_NAME + "E_VIDEO.mp4";
        R_VIDEO = videoPath + CHAMPION_NAME + "R_VIDEO.mp4";
    }
    public void setMainScreenImage() {
        mainScreenImage.setImage(new Image(CHAMPION_IMAGE));
    }
    public void setChampionLabel() {
        name.setText(champion.getName().toUpperCase());
    }
    public void setChampionTitle() {
        title.setText(champion.getTitle().toUpperCase());
    }
    public void setChampionRole() {
        role.setText(champion.getRole().toUpperCase());
    }
    public void setChampionDifficulty() {
        difficulty.setText(champion.getDifficulty().toUpperCase());
    }
    public void setChampionDescription() {
        description.setMaxWidth(500);
        description.setWrapText(true);
        description.setText(champion.getDescription());
    }
    public void setAbilityText() {
        ability.setText(passive.getAbility());
        abilityName.setText(passive.getAbilityName());
        abilityDescription.setText(passive.getAbilityDescription());
        abilityDescription.setMaxWidth(400);
        abilityDescription.setWrapText(true);
    }
    public void setAbilityImage() {
        passiveImage.setImage(new Image(PASSIVE_IMAGE));
        qImage.setImage(new Image(Q_IMAGE));
        wImage.setImage(new Image(W_IMAGE));
        eImage.setImage(new Image(E_IMAGE));
        rImage.setImage(new Image(R_IMAGE));
    }
    public void preloadVideo() {
        pMediaPlayer = createMediaPlayerIfExists(PASSIVE_VIDEO);
        qMediaPlayer = createMediaPlayerIfExists(Q_VIDEO);
        wMediaPlayer = createMediaPlayerIfExists(W_VIDEO);
        eMediaPlayer = createMediaPlayerIfExists(E_VIDEO);
        rMediaPlayer = createMediaPlayerIfExists(R_VIDEO);
    }

    private MediaPlayer createMediaPlayerIfExists(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return new MediaPlayer(new Media(file.toURI().toString()));
        } else {
            return null;
        }
    }
    public void setAbilityVideo() {
        playVideo(pMediaPlayer);
    }
    public void setInitialAbility() {
        setAbilityText();
        setAbilityImage();
        setAbilityVideo();
    }
    public void setCircleStyle(Circle circleImage) {
        circleImage.setId("imageCircle");
        circleImage.setStroke(Color.web("#c4b998"));
        circleImage.setStrokeWidth(2);
    }
    public void setImageOnClickEvent(Circle circleImage, Integer id) {
        circleImage.setOnMouseClicked(mouseEvent -> {
            stopVideo();
            Champion relatedChampion = ChampionData.getChampionData().getById(id);
            new RelatedDataLoaderController<>(relatedChampion).loadData("/view/detail/champion/championDetail.fxml",mouseEvent);
        });
    }
    public Circle getCircleImage(String championName, Integer id) {
        Circle circleImage = new Circle(65);
        String path = "/image/Champion/" + championName+"/"+championName+ "-screen.png";
        circleImage.setFill(new ImagePattern(new Image(path)));
        setCircleStyle(circleImage);
        setImageOnClickEvent(circleImage, id);
        return  circleImage;
    }
    public void setRelatedChampLabelStyle(Label championText) {
        championText.setPadding(new Insets(10));
        championText.setStyle("-fx-text-fill:#c4b998;-fx-font-size:16px;");
        championText.setMaxWidth(Double.MAX_VALUE);
        championText.setAlignment(Pos.CENTER);
    }
    public Label getRelatedChampLabel(String championName) {
        Label championText = new Label(championName.toUpperCase());
        setRelatedChampLabelStyle(championText);
        return championText;
    }
    public void setRelatedChampFlowPane() {
        Map<String, Integer> relatedChamps = champion.getRelatedChampions();
        for (Map.Entry<String, Integer> entry : relatedChamps.entrySet()) {
            Integer championID = entry.getValue();
            boolean championExist = championID != 0;
            if (championExist) {
                String championName = entry.getKey();
                Circle circleImage = getCircleImage(championName, championID);
                Label championText = getRelatedChampLabel(championName);
                VBox vBox = new VBox(circleImage, championText);
                vBox.setAlignment(Pos.CENTER);
                relatedChampFlowPane.getChildren().add(vBox);
                relatedChampFlowPane.setAlignment(Pos.CENTER);
            }
        }
    }
    public void setRegionImage() {
        String regionName = champion.getRelatedRegion().entrySet().iterator().next().getKey();
        Integer regionID = champion.getRelatedRegion().entrySet().iterator().next().getValue();
        String regionPath = "/image/Region/";
            regionImage.setImage(new Image(regionPath + regionName + "/logo.png"));
            regionText.setText(regionName.toUpperCase());
            regionImage.setOnMouseClicked(event -> {
                Region region = RegionData.getRegionData().getById(regionID);
                new RelatedDataLoaderController<>(region).loadData("/view/detail/region/regionDetail.fxml", event);
                stopVideo();
            });
//        }
    }
    public void setSpeciesFlowPane() {
        Map<String, Integer> species = champion.getSpecies();
        for (Map.Entry<String, Integer> entry : species.entrySet()) {
            Text text = new Text(entry.getKey());
            text.setFill(Color.web("#c4b998"));
            speciesFlowPane.getChildren().add(text);
            speciesFlowPane.setAlignment(Pos.CENTER);
        }
    }
    public void setUp() {
        noDisplay.setVisible(false);
        setChampionName();
        setAbility();
        setImagePath();
        setVideoPath();
        preloadVideo();
        setMainScreenImage();
        setChampionLabel();
        setChampionTitle();
        setChampionRole();
        setChampionDifficulty();
        setChampionDescription();
        setInitialAbility();
        setRelatedChampFlowPane();
        setRegionImage();
        setSpeciesFlowPane();
    }

    public void setData(Champion champ) {
        this.champion = champ;
        setUp();
    }

    void switchAbility(Ability abilityType) {
        ability.setText(abilityType.getAbility());
        abilityName.setText(abilityType.getAbilityName());
        abilityDescription.setText(abilityType.getAbilityDescription());
        abilityDescription.setMaxWidth(400);
        abilityDescription.setWrapText(true);
    }
    void playVideo(MediaPlayer mediaPlayer) {
        if(mediaPlayer != null) {
            stopVideo();
            noDisplay.setVisible(false);
            abilityVideo.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            });
        }else {
            stopVideo();
            noDisplay.setVisible(true);
        }
        currentMediaPlayer = mediaPlayer;
    }

    @FXML
    void switchPassive(ActionEvent event) {
        switchAbility(passive);
        playVideo(pMediaPlayer);
    }

    @FXML
    void switchQ(ActionEvent event) {
        switchAbility(Q);
        playVideo(qMediaPlayer);
    }

    @FXML
    void switchW(ActionEvent event) {
        switchAbility(W);
        playVideo(wMediaPlayer);
    }
    @FXML
    void switchE(ActionEvent event) {
        switchAbility(E);
        playVideo(eMediaPlayer);
    }
    @FXML
    void switchR(ActionEvent event) {
        switchAbility(R);
        playVideo(rMediaPlayer);
    }
    public static void stopVideo() {
        if (currentMediaPlayer != null) {
            currentMediaPlayer.stop();
        }
    }
}
