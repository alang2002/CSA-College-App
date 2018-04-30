package org.pltw.examples.collegeapp.info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by AL313011 on 4/16/2018.
 */
public class StudentInfo extends BasicInfo {
    private String previousSchoolName = "", previousStartDate = "", previousEndDate = "";
    private String major = "", minor = "";
    private boolean isTransfer = false;
    private ArrayList<String> achievements = new ArrayList<>();
    private double gpa = 0.0, financialAid = 0.0;

    public StudentInfo(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public StudentInfo(JSONObject jsonObject) {
        super(jsonObject);
        try {
            previousSchoolName = jsonObject.getString("previousschoolname");
            previousStartDate = jsonObject.getString("previousstartdate");
            previousEndDate = jsonObject.getString("previousenddate");
            major = jsonObject.getString("major");
            minor = jsonObject.getString("minor");
            isTransfer = jsonObject.getBoolean("istransfer");
            for (int i = 0; i < jsonObject.getJSONArray("achievements").length(); i++) {
                achievements.add(jsonObject.getJSONArray("achievements").getString(i));
            }
            gpa = jsonObject.getDouble("gpa");
            financialAid = jsonObject.getDouble("financialaid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getPreviousSchoolName() {
        return previousSchoolName;
    }

    public void setPreviousSchoolName(String previousSchoolName) {
        this.previousSchoolName = previousSchoolName;
    }

    public String getPreviousStartDate() {
        return previousStartDate;
    }

    public void setPreviousStartDate(String previousStartDate) {
        this.previousStartDate = previousStartDate;
    }

    public String getPreviousEndDate() {
        return previousEndDate;
    }

    public void setPreviousEndDate(String previousEndDate) {
        this.previousEndDate = previousEndDate;
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

    public ArrayList<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(ArrayList<String> achievements) {
        this.achievements = achievements;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getFinancialAid() {
        return financialAid;
    }

    public void setFinancialAid(double financialAid) {
        this.financialAid = financialAid;
    }

    @Override
    public JSONObject serializeJSON() {
        JSONObject baseObject = super.serializeJSON();

        // Add student-specific information
        try {
            baseObject.put("previousschoolname", previousSchoolName);
            baseObject.put("previousstartdate", previousStartDate);
            baseObject.put("previousenddate", previousEndDate);
            baseObject.put("major", major);
            baseObject.put("minor", minor);
            baseObject.put("istransfer", isTransfer);
            baseObject.put("achievements", new JSONArray(achievements));
            baseObject.put("gpa", gpa);
            baseObject.put("financialAid", financialAid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return baseObject;
    }
}
