package classes;

import java.util.Date;

public class FlightAttendant extends Person {
    //singleton pattern
    private volatile static FlightAttendant SINGLE_INSTANCE;
    private FlightAttendant() {}
    public static FlightAttendant getInstance() {
        if(SINGLE_INSTANCE==null)
            SINGLE_INSTANCE = new FlightAttendant();
        return SINGLE_INSTANCE;
    }

private int FlightAttendant_id;

    public int getFlightAttendant_id() {
        return FlightAttendant_id;
    }

    public void setFlightAttendant_id(int flightAttendant_id) {
        FlightAttendant_id = flightAttendant_id;
    }


    public float store_user(int aperson_id,int aFlightAttendantid, String aFirst_name, String alast_name, String amiddle_name,
                           String agender, String aAddress1,
                           String aAddress2 , String aemail, String anationality, Date abirthdate, long aNationalID, int aNumber)
    {
        setPerson_id(aperson_id);
        FlightAttendant_id = aFlightAttendantid;
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
        return 0;
    }
}
