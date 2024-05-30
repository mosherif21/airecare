package sample;


import classes.Flight;
import classes.FlightAttendant;
import classes.Passenger;
import classes.Pilot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Get_user {
    private static int ID;

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Get_user.ID = ID;
    }

    private static String type;

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Get_user.type = type;
    }

    public static String get_user_type(int person_id){
        String user_type = null;
        try {

            Statement st = databaseconnection.test_database();
            ResultSet rs = st.executeQuery("select * from aircare.dbo.Person,aircare.dbo.Pilot where Person_id=ID and ID='" + person_id + "'");
            while (rs.next()){
                if (rs.getRow() > 0)
                    user_type = "Pilot";
            }

            rs=st.executeQuery("select * from aircare.dbo.Person,aircare.dbo.Passenger where Person_id=ID and ID='"+person_id+"'");
            while (rs.next()){
                if (rs.getRow() > 0)
                    user_type="Passenger";
            }
            rs=st.executeQuery("select * from aircare.dbo.Person,aircare.dbo.FlightAttendant where Person_id=ID and ID='"+person_id+"'");
            while (rs.next()){
                if (rs.getRow() > 0)
                    user_type="Attendant";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user_type;
    }

    public static void load_user(){
        String type=get_user_type(Get_user.getID());
        try {
            Statement st=databaseconnection.test_database();
            if(type=="Passenger"){
                setType("Passenger");
                ResultSet rs=st.executeQuery("select * from aircare.dbo.Person,aircare.dbo.Passenger,aircare.dbo.Flight where Person_id=ID and ID='"+Get_user.ID+"'");
                Passenger p=Passenger.getInstance();
                while(rs.next()){

                    double n=p.store_user(Integer.parseInt(rs.getString("ID")),Integer.parseInt(rs.getString("Passenger_id")),rs.getString("First_name"),rs.getString("Last_name"),
                            rs.getString("Middle_name"),rs.getString("gender"),rs.getString("Address1"),rs.getString("Address2"),
                            rs.getString("Email"),rs.getString("Nationality"), new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("Birthdate")),
                            Long.parseLong(rs.getString("NationalID")), Integer.parseInt(rs.getString("number")),
                            rs.getString("seat"),rs.getString("Class"));
                   Flight f= Flight.getInstance();
                           f.setFlight_id( rs.getString("Flight_id_name"));
                    flight(rs.getString("Flight_id_name"));
                    //load in database that user is in airport by inserint person id and his flight id
                    Statement st1=databaseconnection.test_database();
                 st1.executeQuery("INSERT INTO aircare.dbo.checkedinsite VALUES ('"+   p.getPerson_id() +"', '"+Integer.parseInt(rs.getString("Flight_id")) +"')");

                }
            }
            else if(type=="Pilot"){
                setType("Pilot");
                ResultSet rs=st.executeQuery("select * from aircare.dbo.Person,aircare.dbo.Pilot,aircare.dbo.Flight where Person_id=ID and ID='"+Get_user.ID+"'");
                Pilot p=Pilot.getInstance();
                while(rs.next()){

                    int n=p.store_user(Integer.parseInt(rs.getString("ID")),Integer.parseInt(rs.getString("Pilot_id")),rs.getString("First_name"),rs.getString("Last_name"),
                            rs.getString("Middle_name"),rs.getString("gender"),rs.getString("Address1"),rs.getString("Address2"),
                            rs.getString("Email"),rs.getString("Nationality"), new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("Birthdate")),
                            Long.parseLong(rs.getString("NationalID")), Integer.parseInt(rs.getString("number"))
                    );

                    Flight f= Flight.getInstance();
                    f.setFlight_id( rs.getString("Flight_id_name"));
                    flight(rs.getString("Flight_id_name"));
                    //load in database that user is in airport by inserint person id and his flight id
                    rs=st.executeQuery("INSERT INTO aircare.dbo.checkedinsite VALUES ('"+   p.getPerson_id() +"', '"+f.getFlight_id()  +"'");

                }
            }
            else if(type=="Attendant"){
                setType("Attendant");
                ResultSet rs=st.executeQuery("select * from aircare.dbo.Person,aircare.dbo.FlightAttendant,aircare.dbo.Flight where Person_id=ID and ID='"+Get_user.ID+"'");
               FlightAttendant p=FlightAttendant.getInstance();
                while(rs.next()){

                    float n=p.store_user(Integer.parseInt(rs.getString("ID")),Integer.parseInt(rs.getString("Flightattendant_id")),rs.getString("First_name"),rs.getString("Last_name"),
                            rs.getString("Middle_name"),rs.getString("gender"),rs.getString("Address1"),rs.getString("Address2"),
                            rs.getString("Email"),rs.getString("Nationality"), new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("Birthdate")),
                            Long.parseLong(rs.getString("NationalID")), Integer.parseInt(rs.getString("number"))
                    );
                    flight(rs.getString("Flight_id_name"));
                    Flight f= Flight.getInstance();
                    f.setFlight_id( rs.getString("Flight_id_name"));
                    //load in database that user is in airport by inserint person id and his flight id
                    rs=st.executeQuery("INSERT INTO aircare.dbo.checkedinsite VALUES ('"+   p.getPerson_id() +"', '"+f.getFlight_id()  +"'");
                }
            }
            //load flight data
            Flight f= Flight.getInstance();
            ResultSet rs2=st.executeQuery("select * from aircare.dbo.Flight,aircare.dbo.Location where Location.Location_id=Flight.location_id and Flight_id_name ='"+ f.getFlight_id() +"'");
            while (rs2.next()){
                f.setAirplane_type(rs2.getString("airplane_type"));
                f.setArriving_destination(rs2.getString("arriving_destination"));
                f.setDATE(rs2.getString("DATE"));
                f.setFloor(Integer.parseInt(rs2.getString("Floor")));
                f.setHasArrived(rs2.getString("hasArrived"));
                f.setDeparting_destination(rs2.getString("departing_destination"));
                f.setGate(rs2.getString("Gate"));
                f.setLocation_Name(rs2.getString("Location_name"));
                f.setBoarding_time(rs2.getString("Boarding_time"));
            }


        } catch (SQLException | ParseException throwables) {

        }

    }
    public static void flight(String s){
        try{
            Statement st=databaseconnection.test_database();
        //load flight data
        Flight f= Flight.getInstance();
        ResultSet rs2=st.executeQuery("select * from aircare.dbo.Flight,aircare.dbo.Location where Location.Location_id=Flight.location_id and Flight_id_name ='"+ s+"'");
        while (rs2.next()){
            f.setAirplane_type(rs2.getString("airplane_type"));
            f.setArriving_destination(rs2.getString("arriving_destination"));
            f.setDATE(rs2.getString("DATE"));
            f.setFloor(Integer.parseInt(rs2.getString("Floor")));
            f.setHasArrived(rs2.getString("hasArrived"));
            f.setDeparting_destination(rs2.getString("departing_destination"));
            f.setGate(rs2.getString("Gate"));
            f.setLocation_Name(rs2.getString("Location_name"));
            f.setBoarding_time(rs2.getString("Boarding_time"));
        }


    } catch (SQLException throwables) {

    }
    }
}
