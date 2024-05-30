package classes;

import java.util.Date;

public class Passenger extends Person{
    //singleton pattern
    private volatile static Passenger SINGLE_INSTANCE ;
    private Passenger() {}
    public static Passenger getInstance() {
        if(SINGLE_INSTANCE == null){
      SINGLE_INSTANCE=new Passenger();
        }
        return SINGLE_INSTANCE;
    }

    private String seat;
    private String Class;
    private int passenger_id;

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String geClass() {
        return Class;
    }

    public void setClass(String aClass) {
        Class = aClass;
    }


    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public double store_user(int aperson_id,int apassenger_id, String aFirst_name, String alast_name, String amiddle_name,
                           String agender, String aAddress1,
                           String aAddress2 , String aemail, String anationality, Date abirthdate, long aNationalID,
                           int aNumber, String aseat,String aclass)
    {
        setPerson_id(aperson_id);
        passenger_id = apassenger_id;
        setSeat(aseat);

        setFirst_name(aFirst_name);
        setLast_name(alast_name);
        setMiddle_name(amiddle_name);
        setGender(agender);
        setEmail(aemail);
        setNumber(aNumber);
        setNationality(anationality);
        setBirthdate(abirthdate);
        setNationalID(aNationalID);
        setAddress1(aAddress1);
        setAddress2(aAddress2);
        setClass(aclass);
        return 0;
    }
}
