package classes;

import java.util.Date;

public abstract class Person {
    //attributes
    private int person_id;

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }


    private String Address1;
    private String Address2;
    private int Number;
    private long NationalID;
    private Date Birthdate;
    private String First_name;
    private String last_name;
    private String middle_name;
    private String gender;
    private String email;
    private String nationality;



    //getters and setters
    public long getNationalID() {
        return NationalID;
    }

    public void setNationalID(long nationalID) {
        NationalID = nationalID;
    }
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public Date getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(Date birthdate) {
        Birthdate = birthdate;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }
    //get user


    public void store_user(int apilot_id, String aFirst_name, String alast_name, String amiddle_name,
                                    String agender, String aAddress1,
                                    String aAddress2, String aemail, String anationality, Date abirthdate,
                           long aNationalID, int aNumber)
    {

    }
}
