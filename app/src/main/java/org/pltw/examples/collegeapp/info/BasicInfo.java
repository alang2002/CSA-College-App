package org.pltw.examples.collegeapp.info;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AL313011 on 4/16/2018.
 */
public class BasicInfo {
    private String firstName = "", lastName  = "";
    private String occupation = "";
    private String birthdate = "January/1/1999";
    private int age = 0;

    public BasicInfo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public BasicInfo(JSONObject jsonObject) {
        try {
            firstName = jsonObject.getString("firstname");
            lastName = jsonObject.getString("lastname");
            occupation = jsonObject.getString("occupation");
            birthdate = jsonObject.getString("birthdate");
            age = jsonObject.getInt("age");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " - " + birthdate + ", " + age;
    }

    public JSONObject serializeJSON() {
        JSONObject object = new JSONObject();
        try {
            object.put("firstname", firstName);
            object.put("lastname", lastName);
            object.put("occupation", occupation);
            object.put("birthdate", birthdate);
            object.put("age", age);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }


}
