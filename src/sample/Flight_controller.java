package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;

import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import classes.*;


public class Flight_controller implements Initializable {
    @FXML
    WebView webview;
    @FXML
    ImageView img_passenger;
    @FXML
    Label lbl_name;

    @FXML
    Label lbl_nationality;
    @FXML
    Label lbl_date;
    @FXML
    Label lbl_board_time;
    @FXML
    Label lbl_seat;
    @FXML
    Label lbl_seat1;
    @FXML
    Label lbl_flight_id;
    @FXML
    Label lbl_gate;
    @FXML
    Label lbl_from;
    @FXML
    Label lbl_to;
    @FXML
    Label lbl_has;
@FXML
    Label lbl_class1;
@FXML
    Label lbl_class;

    public void load_data(){
        Person p=null;

if(Get_user.getType()=="Passenger"){
    p= Passenger.getInstance();
    lbl_seat.setText(((Passenger)p).getSeat());
    lbl_class.setText(((Passenger)p).geClass());
  }

else if(Get_user.getType()=="Pilot"){
    p= Pilot.getInstance();
    lbl_seat1.setVisible(false);
    lbl_seat.setVisible(false);
    lbl_class.setVisible(false);
    lbl_class1.setVisible(false);
}
       else if(Get_user.getType()=="Attendant"){
            p= FlightAttendant.getInstance();
            lbl_seat1.setVisible(false);
            lbl_seat.setVisible(false);
             lbl_class.setVisible(false);
             lbl_class1.setVisible(false);
        }
Flight f= Flight.getInstance();

        lbl_name.setText(p.getFirst_name()+ " "+p.getLast_name());
       lbl_board_time.setText(f.getBoarding_time().substring(0,5));
       lbl_flight_id.setText(f.getFlight_id());
       lbl_date.setText(f.getDATE());
       lbl_gate.setText(f.getGate());
       lbl_from.setText(f.getDeparting_destination());
       lbl_to.setText(f.getArriving_destination());
       lbl_nationality.setText(p.getNationality());
       lbl_has.setText(f.isHasArrived());

    }
    public void load_user_image(){
        try {
            Statement st = databaseconnection.test_database();
            ResultSet rs=st.executeQuery("select image from aircare.dbo.Person where ID='"+ Get_user.getID()+"'");
            if (rs.next()) {
                InputStream is = rs.getBinaryStream("image");
                javafx.scene.image.Image image = new Image(is);
                img_passenger.setImage(image);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void map(){
        webview.getEngine().load("https://www.google.com/maps/d/embed?mid=1JkXxtlGkaW2N0FpALJLLpm2qH3n2Zk48");
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
load_data();
load_user_image();

        map();

    }
}
