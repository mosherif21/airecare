package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;


public class customs_controller implements Initializable {
    @FXML
    WebView web_view;

    public void map(){
        web_view.getEngine().load("https://www.google.com/maps/d/embed?mid=1JkXxtlGkaW2N0FpALJLLpm2qH3n2Zk48");


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        map();


    }
}
