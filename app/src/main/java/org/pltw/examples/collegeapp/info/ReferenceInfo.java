package org.pltw.examples.collegeapp.info;

/**
 * Created by AL313011 on 4/16/2018.
 */
public class ReferenceInfo extends BasicInfo {
    private String companyName = "";
    private String emailAddress = "";
    private String relationshipWithApplicant = "";
    private String phoneNumber = "";
    private int volunteerHours = 0;

    public ReferenceInfo(String firstName, String lastName) {
        super(firstName, lastName);
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

    public String getRelationshipWithApplicant() {
        return relationshipWithApplicant;
    }

    public void setRelationshipWithApplicant(String relationshipWithApplicant) {
        this.relationshipWithApplicant = relationshipWithApplicant;
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

}
