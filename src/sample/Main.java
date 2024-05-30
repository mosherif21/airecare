package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent pane = FXMLLoader.load(getClass().getResource("splash_screen.fxml"));
        Stage stage = new Stage();
        double width = 650;
        double height = 400;
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        final Scene scene = new Scene(pane, width, height);
        stage.setScene(scene);
        Image icon = new Image(getClass().getResourceAsStream("/icons/icon_logo.png"));
        stage.getIcons().add(icon);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.centerOnScreen();
        javafx.scene.media.AudioClip welcome = new javafx.scene.media.AudioClip(this.getClass().getResource("/audio/welcome.mp3").toString());
         welcome.play();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
