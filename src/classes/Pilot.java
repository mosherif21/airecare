package classes;

import java.util.Date;

public class Pilot extends Person{
    //singleton pattern
    private volatile static Pilot SINGLE_INSTANCE ;
    private Pilot() {}
    public static Pilot getInstance() {
        if(SINGLE_INSTANCE==null)
            SINGLE_INSTANCE = new Pilot();
        return SINGLE_INSTANCE;
    }

    private int pilot_id;

    public int getPilot_id() {
        return pilot_id;
    }

    public void setPilot_id(int pilot_id) {
        this.pilot_id = pilot_id;
    }


    public int store_user(int aperson_id,int apilot_id, String aFirst_name, String alast_name, String amiddle_name,
                           String agender, String aAddress1,
                           String aAddress2 , String aemail, String anationality, Date abirthdate, long aNationalID, int aNumber)
    {
        setPerson_id(aperson_id);
        pilot_id = apilot_id;
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
