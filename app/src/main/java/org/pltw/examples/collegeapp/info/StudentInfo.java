package org.pltw.examples.collegeapp.info;

import java.util.ArrayList;

/**
 * Created by AL313011 on 4/16/2018.
 */
public class StudentInfo extends BasicInfo {
    private String previousSchoolName = "", startDate = "", endDate = "";
    private String major = "", minor = "";
    private boolean isTransfer = false;
    private ArrayList<String>schoolAchievements = new ArrayList<>();
    private ArrayList<ReferenceInfo>references = new ArrayList<>();
    private double gpaOverall = 0.0, financialAidAmo = 0.0;

    public StudentInfo(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public String getPreviousSchoolName() {
        return previousSchoolName;
    }

    public void setPreviousSchoolName(String previousSchoolName) {
        this.previousSchoolName = previousSchoolName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public boolean isTransfer() {
        return isTransfer;
    }

    public void setTransfer(boolean transfer) {
        isTransfer = transfer;
    }

    public ArrayList<String> getSchoolAchievements() {
        return schoolAchievements;
    }

    public void setSchoolAchievements(ArrayList<String> schoolAchievements) {
        this.schoolAchievements = schoolAchievements;
    }

    public ArrayList<ReferenceInfo> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<ReferenceInfo> references) {
        this.references = references;
    }

    public double getGpaOverall() {
        return gpaOverall;
    }

    public void setGpaOverall(double gpaOverall) {
        this.gpaOverall = gpaOverall;
    }

    public double getFinancialAidAmo() {
        return financialAidAmo;
    }

    public void setFinancialAidAmo(double financialAidAmo) {
        this.financialAidAmo = financialAidAmo;
    }

}
