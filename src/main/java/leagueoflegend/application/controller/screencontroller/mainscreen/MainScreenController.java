package leagueoflegend.application.controller.screencontroller.mainscreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML
    private ImageView roleImage;
    @FXML
    private MediaView roleVideo;
    private MediaPlayer assassinMediaPlayer;
    private MediaPlayer fighterMediaPlayer;
    private MediaPlayer mageMediaPlayer;
    private MediaPlayer marksmanMediaPlayer;
    private MediaPlayer supportMediaPlayer;
    private MediaPlayer tankMediaPlayer;
    private static MediaPlayer currentRoleMediaPlayer;

    private MediaPlayer riftMediaPlayer;
    private MediaPlayer aramMediaPlayer;
    private MediaPlayer tftMediaPlayer;
    private static MediaPlayer currentGameplayMediaPlayer;
    @FXML
    private Button srButton;
    @FXML
    private Button arButton;
    @FXML
    private Button tftButton;
    @FXML
    private MediaView gameplayVideo;
    @FXML
    private AnchorPane textPane;
    private String ASSASSIN_PATH = "src/main/resources/video/assasin.mp4";
    private String FIGHTER_PATH = "src/main/resources/video/fighter.mp4";
    private String MAGE_PATH = "src/main/resources/video/mage.mp4";
    private String MARKSMAN_PATH = "src/main/resources/video/marksman.mp4";
    private String SUPPORT_PATH = "src/main/resources/video/support.mp4";
    private String TANK_PATH = "src/main/resources/video/tank.mp4";
    private String RIFT_PATH = "src/main/resources/video/summonersrift.mp4";
    private String ARAM_PATH = "src/main/resources/video/howlingabyss.mp4";
    private String TFT_PATH = "src/main/resources/video/teamfighttactics.mp4";

    void preloadVideo() {
        assassinMediaPlayer = createMediaPlayerIfExists(ASSASSIN_PATH);
        fighterMediaPlayer = createMediaPlayerIfExists(FIGHTER_PATH);
        mageMediaPlayer = createMediaPlayerIfExists(MAGE_PATH);
        marksmanMediaPlayer = createMediaPlayerIfExists(MARKSMAN_PATH);
        supportMediaPlayer = createMediaPlayerIfExists(SUPPORT_PATH);
        tankMediaPlayer = createMediaPlayerIfExists(TANK_PATH);

        riftMediaPlayer = createMediaPlayerIfExists(RIFT_PATH);
        aramMediaPlayer = createMediaPlayerIfExists(ARAM_PATH);
        tftMediaPlayer = createMediaPlayerIfExists(TFT_PATH);
    }
    private MediaPlayer createMediaPlayerIfExists(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return new MediaPlayer(new Media(file.toURI().toString()));
        } else {
            return null;
        }
    }
    protected void playRoleVideo(MediaPlayer mediaPlayer) {
        stopRoleVideo();
        roleVideo.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
        currentRoleMediaPlayer = mediaPlayer;
    }
    void playGameplayVideo(MediaPlayer mediaPlayer) {
        stopGameplayVideo();
        gameplayVideo.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
        currentGameplayMediaPlayer = mediaPlayer;
    }
    void switchTextPane(String path) throws IOException {
        if(!textPane.getChildren().isEmpty())
            textPane.getChildren().remove(0);
        Node text = FXMLLoader.load(getClass().getResource(path));
        textPane.getChildren().add(text);
    }
    @FXML
    void switchAssasin(ActionEvent event) {
        roleImage.setImage(new Image("/image/mainscreen/assasin.png"));
        playRoleVideo(assassinMediaPlayer);
    }
    @FXML
    void switchFighter(ActionEvent event) {
        roleImage.setImage(new Image("/image/mainscreen/fighter.png"));
        playRoleVideo(fighterMediaPlayer);
    }
    @FXML
    void switchMage(ActionEvent event) {
        roleImage.setImage(new Image("/image/mainscreen/mage.png"));
        playRoleVideo(mageMediaPlayer);
    }
    @FXML
    void switchMarksmen(ActionEvent event) {
        roleImage.setImage(new Image("/image/mainscreen/marksman.png"));
        playRoleVideo(marksmanMediaPlayer);
    }
    @FXML
    void switchSupport(ActionEvent event) {
        roleImage.setImage(new Image("/image/mainscreen/support.png"));
        playRoleVideo(supportMediaPlayer);
    }
    @FXML
    void switchTank(ActionEvent event) {
        roleImage.setImage(new Image("/image/mainscreen/tank.png"));
        playRoleVideo(tankMediaPlayer);
    }
    @FXML
    void switchToAR(ActionEvent event) throws IOException {
        playGameplayVideo(aramMediaPlayer);
        switchTextPane("/view/allscreen/mainscreen/aramText.fxml");
    }
    @FXML
    void switchToSR(ActionEvent event) throws IOException {
        playGameplayVideo(riftMediaPlayer);
        switchTextPane("/view/allscreen/mainscreen/riftText.fxml");
    }
    @FXML
    void switchToTFT(ActionEvent event) throws IOException {
        playGameplayVideo(tftMediaPlayer);
        switchTextPane("/view/allscreen/mainscreen/tftText.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        preloadVideo();
        // Set initial graphics
        roleImage.setImage(new Image("/image/mainscreen/assasin.png"));
        playGameplayVideo(riftMediaPlayer);
        playRoleVideo(assassinMediaPlayer);
        try {
            textPane.getChildren()
                    .add(FXMLLoader
                    .load(getClass().getResource("/view/allscreen/mainscreen/riftText.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // CSS button padding
        Insets insets = new Insets(3,3,3,3);
        srButton.setPadding(insets);
        arButton.setPadding(insets);
        tftButton.setPadding(insets);
    }
    public static void stopRoleVideo() {
        if (currentRoleMediaPlayer != null) {
            currentRoleMediaPlayer.stop();
        }
    }
    public static void stopGameplayVideo() {
        if(currentGameplayMediaPlayer != null) {
            currentGameplayMediaPlayer.stop();
        }
    }

}
