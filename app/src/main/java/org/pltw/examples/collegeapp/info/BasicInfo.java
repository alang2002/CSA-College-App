package org.pltw.examples.collegeapp.info;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class BasicInfo {
    protected String firstName = "", lastName  = "";
    protected String occupation = "";
    protected String birthdate = "January/1/1999";
    protected int age = 0;

    public String getFamilyRelation() {
        return familyRelation;
    }

    public void setFamilyRelation(String familyRelation) {
        this.familyRelation = familyRelation;
    }

    protected String familyRelation = "";

    public BasicInfo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Instantiate this class from a json object
     * @param jsonObject the object containing BasicInfo data
     */
    public BasicInfo(JSONObject jsonObject) {
        try {
            firstName = jsonObject.getString("firstname");
            lastName = jsonObject.getString("lastname");
            occupation = jsonObject.getString("occupation");
            birthdate = jsonObject.getString("birthdate");
            age = jsonObject.getInt("age");
            familyRelation = jsonObject.getString("familyrelation");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
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


    public ArrayList<String> getAsArray() {
        String[] safeData = {
                getFullName(),
                birthdate,
                occupation,
                String.valueOf(age),
                familyRelation
        };

        ArrayList<String> data = new ArrayList<>();
        data.addAll(Arrays.asList(safeData));
        return data;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " - " + birthdate + ", " + age;
    }

    /**
     * Return this object in the form of JSON
     * @return this object in JSON
     */
    public JSONObject serializeJSON() {
        JSONObject object = new JSONObject();
        try {
            object.put("firstname", firstName);
            object.put("lastname", lastName);
            object.put("occupation", occupation);
            object.put("birthdate", birthdate);
            object.put("age", age);
            object.put("familyrelation", familyRelation);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }


}
