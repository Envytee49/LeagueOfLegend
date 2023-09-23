package leagueoflegend.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    @FXML
    public Button startButton;
    @FXML
    private MediaView intro;
    private MediaPlayer mediaPlayer;
    @FXML
    private void StartView(javafx.event.ActionEvent event) throws IOException {
        mediaPlayer.stop();
        Stage stage = (Stage) startButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/allscreen/mainscreen/mainScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Media media = new Media(new File("src/main/resources/video/intro-screen.mp4").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnReady(() -> intro.setMediaPlayer(mediaPlayer));
//        intro.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
    }
}
