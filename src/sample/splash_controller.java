package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class splash_controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
   splash_screen();
    }
    @FXML
    private AnchorPane splash_pane;
    private void splash_screen(){

        new Thread(){
            public void run(){
                try {
                    Thread.sleep(2500);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Parent pane = FXMLLoader.load(getClass().getResource("face.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(pane);
                            stage.setScene(scene);
                            Image icon = new Image(getClass().getResourceAsStream("/icons/icon_logo.png"));
                            stage.getIcons().add(icon);
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.show();
                            stage.centerOnScreen();
                            splash_pane.getScene().getWindow().hide();
                            javafx.scene.media.AudioClip clip = new javafx.scene.media.AudioClip(this.getClass().getResource("/audio/camera.mp3").toString());
                            clip.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }.start();
    }
}
