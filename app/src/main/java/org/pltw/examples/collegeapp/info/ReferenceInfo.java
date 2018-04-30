package org.pltw.examples.collegeapp.info;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Ref;

/**
 * Created by AL313011 on 4/16/2018.
 */
public class ReferenceInfo extends BasicInfo {
    private String companyName = "";
    private String emailAddress = "";
    private String relation = "";
    private String phoneNumber = "";
    private int volunteerHours = 0;

    public ReferenceInfo(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public ReferenceInfo(JSONObject jsonObject) {
        super(jsonObject);
        try {
            companyName = jsonObject.getString("companyname");
            emailAddress = jsonObject.getString("emailaddress");
            relation = jsonObject.getString("relation");
            phoneNumber = jsonObject.getString("phonenumber");
            volunteerHours = jsonObject.getInt("volunteerhours");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getVolunteerHours() {
        return volunteerHours;
    }

    public void setVolunteerHours(int volunteerHours) {
        this.volunteerHours = volunteerHours;
    }

    @Override
    public JSONObject serializeJSON() {
        JSONObject baseObject = super.serializeJSON();

        // Add reference-specific items
        try {
            baseObject.put("companyname", companyName);
            baseObject.put("emailaddress", emailAddress);
            baseObject.put("relation", relation);
            baseObject.put("phonenumber", phoneNumber);
            baseObject.put("volunteerhours", volunteerHours);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return baseObject;
    }
}
