package sample;


import classes.Flight;
import classes.Passenger;
import com.mgnt.utils.TimeUtils;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.awt.*;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class passenger_home_controller implements Initializable {

    @FXML
    Button btn_exit;
  @FXML
    Button btn_toilet;
  @FXML
    Button btn_flight;
  @FXML
    Button btn_customs;
  @FXML
    Button btn_back;
  @FXML
    Button btn_checkin;
  @FXML
    Button btn_food;
  @FXML
    Button btn_claim;
  @FXML
  Pane pn_main;
  @FXML
  Pane pn_form_change;
@FXML
    GridPane grid_home;
@FXML
    AnchorPane pane;
@FXML
        Label clock;

    DropShadow gl=new DropShadow();

    Label lbl=new Label();

    javafx.scene.image.ImageView imageview =new javafx.scene.image.ImageView();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //date and time label update
        final DateFormat format = DateFormat.getInstance();
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler()
        {@Override
        public void handle(Event event) {
            final Calendar cal = Calendar.getInstance();
            clock.setText(format.format(cal.getTime()));
        }

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        exit();
      Flight();
      customs();
      back();
      food();
      toilet();
      checkin();
      claim();
      btn_back.setVisible(false);
      btn_customs.setEffect(gl);
      btn_exit.setEffect(gl);
      btn_flight.setEffect(gl);
      btn_checkin.setEffect(gl);
      btn_claim.setEffect(gl);
      btn_food.setEffect(gl);
      btn_toilet.setEffect(gl);
      imageview.setVisible(false);
      pane.getChildren().add(lbl);
      pane.getChildren().add(imageview);



    }
    private void loadform(String form,int flight)  {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(getClass().getResource(form+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Node> parentChildren = ((Pane)pn_main.getParent()).getChildren();
        parentChildren.set(parentChildren.indexOf(pn_main), newLoadedPane);
        pn_main=newLoadedPane;

            pn_form_change.setLayoutX(30.00);
            pn_form_change.setLayoutY(100.00);


        btn_exit.setVisible(false);
}



    private void playtrack(String name){
        javafx.scene.media.AudioClip clip= new javafx.scene.media.AudioClip(this.getClass().getResource("/audio/"+name+".mp3").toString());
       clip.setVolume(0.08);
        clip.play();
    }

    //the function delays for the map to load then click on aircare then delay then click on destination
    private void click_dest1(int x,int y,int x1,int y1){

        new Thread(){
            public void run(){
                try {
                    TimeUtils.sleepFor(3,TimeUnit.SECONDS);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            imageview.setVisible(true);
                            Robot ROB;
                            ROB = new Robot();
                            ROB.mouseMove(0,0);
                            ROB.delay(1);
                            ROB.mouseMove(x,y);
                           // TimeUtils.sleepFor(100,TimeUnit.MILLISECONDS);
                            ROB.delay(1);
                            ROB.mousePress(InputEvent.BUTTON1_MASK);
                            ROB.mouseRelease(InputEvent.BUTTON1_MASK);
                            ROB.delay(1);
                            int x= (int) MouseInfo.getPointerInfo().getLocation().getX();
                            int y= (int) MouseInfo.getPointerInfo().getLocation().getY();
                            imageview.setLayoutX(x-255);
                            imageview.setLayoutY(y-290) ;
                            javafx.scene.image.Image i = new javafx.scene.image.Image(new File("src/icons/pickupicon3.png").toURI().toString());
                            imageview.setImage(i);
                            imageview.setScaleX(0.07);
                            imageview.setScaleY(0.07);
                            ROB.mouseMove(2000,2000);
                            new Thread(){
                                public void run(){
                                    try {
                                        TimeUtils.sleepFor(2,TimeUnit.SECONDS);
                                    }
                                    catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                           try {
                                                Robot ROB2 = new Robot();
                                                ROB2.mouseMove(0,0);
                                               ROB2.delay(2);
                                                ROB2.mouseMove(x1,y1);
                                                ROB2.delay(2);
                                                int p=InputEvent.BUTTON1_MASK;
                                                ROB2.mousePress(p);
                                                ROB2.mouseRelease(p);
                                               ROB2.delay(2);
                                                int a= (int) MouseInfo.getPointerInfo().getLocation().getX();
                                               int b= (int) MouseInfo.getPointerInfo().getLocation().getY();
                                                imageview.setLayoutX(a-260);
                                                imageview.setLayoutY(b-290) ;
                                                Image i = new Image(new File("src/icons/pickupicon2.png").toURI().toString());
                                                imageview.setImage(i);
                                                imageview.setScaleX(0.07);
                                                imageview.setScaleY(0.07);
                                                btn_back.setVisible(true);
                                                ROB2.mouseMove((int) Screen.getPrimary().getVisualBounds().getWidth()/2,
                                                        (int) Screen.getPrimary().getVisualBounds().getHeight()/2);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }

                            }.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }.start();


    }

private void Flight(){

btn_flight.setOnAction(event -> {
    playtrack("flight");
    loadform("Flight",1);

Flight f=Flight.getInstance();
String gate=f.getGate().trim();
int x=0,y=0;
        if(gate.equals("gate1")){
            x=940;
            y=608;
        }

        click_dest1(552,525,x,y);

});
}
private void food(){
btn_food.setOnAction(event -> {
  playtrack("food");
    loadform("general_map",0);
    click_dest1(353,318,553,318);


});
}

private void toilet(){
btn_toilet.setOnAction(event -> {
    loadform("general_map",0);
    click_dest1(353,318,493,188);

});
}

private void claim(){
btn_claim.setOnAction(event -> {
playtrack("baggage");
    loadform("general_map",0);
    click_dest1(353,318,423,488);


});
}
private void checkin(){
btn_checkin.setOnAction(event -> {
playtrack("checkin");
   loadform("general_map",0);
    click_dest1(353,318,553,318);

});
}

private void customs(){
        btn_customs.setOnAction(event -> {
            playtrack("customs");
            loadform("general_map",0);
            click_dest1(353,318,363,408);

        });
    }


    //btn_back
private void back(){
btn_back.setOnAction(event -> {


    try {
       imageview.setVisible(false);
        FadeTransition F=new FadeTransition();
        F.setDuration(Duration.millis(500));
        F.setNode(pn_main);
        F.setFromValue(1);
        F.setToValue(0);
        F.setOnFinished((ActionEvent )->{
            btn_back.setVisible(false);
            pn_form_change.setLayoutX(257.00);
            pn_form_change.setLayoutY(282.00);
            Pane newLoadedPane =new Pane();
            newLoadedPane.getChildren().add(grid_home);
            List<Node> parentChildren = ((Pane)pn_main.getParent()).getChildren();
            parentChildren.set(parentChildren.indexOf(pn_main), newLoadedPane);
            pn_main = newLoadedPane;
            btn_exit.setVisible(true);
        });
        F.play();

    } catch (Exception e) {
        e.printStackTrace();
    }
});
}

//btn_exit
    private void exit(){

      btn_exit.setOnAction  (event ->  {
            javafx.scene.media.AudioClip exit= new javafx.scene.media.AudioClip(this.getClass().getResource("/audio/thankyou.mp3").toString());
            exit.play();
            new Thread(){
                public void run(){
                    try {
                        Thread.sleep(2500);
                        FadeTransition F=new FadeTransition();
                        F.setDuration(Duration.millis(500));
                        F.setNode(pn_main);
                        F.setFromValue(1);
                        F.setToValue(0);
                        F.play();
                        Thread.sleep(500);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Platform.exit();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }.start();
        });

    }


}
