package org.pltw.examples.collegeapp.info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentInfo extends BasicInfo {
    protected String previousSchoolName = "";
    protected int previousStartYear = 0, previousEndYear = 0;
    protected String major = "", minor = "";
    protected boolean isTransfer = false;
    protected ArrayList<String> achievements = new ArrayList<>();
    protected double gpa = 0.0, financialAid = 0.0;

    public StudentInfo(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public StudentInfo(JSONObject jsonObject) {
        super(jsonObject);
        try {
            previousSchoolName = jsonObject.getString("previousschoolname");
            previousStartYear = jsonObject.getInt("previousstartyear");
            previousEndYear = jsonObject.getInt("previousendyear");
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

    public int getPreviousStartYear() {
        return previousStartYear;
    }

    public void setPreviousStartYear(int previousStartYear) {
        this.previousStartYear = previousStartYear;
    }

    public int getPreviousEndYear() {
        return previousEndYear;
    }

    public void setPreviousEndYear(int previousEndDate) {
        this.previousEndYear = previousEndDate;
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

    public void addAchievement(String achievement) {
        this.achievements.add(achievement);
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

    /**
     * Return all of the data from this class as an {@link ArrayList}
     * @return the array form of this class
     */
    public ArrayList<String> getAsArray() {
        String[] safeData = {
                getFullName(),
                birthdate,
                String.valueOf(isTransfer),
                major,
                minor,
                String.valueOf(gpa),
                String.valueOf(financialAid),
                previousSchoolName,
                String.valueOf(previousStartYear),
                String.valueOf(previousEndYear)
        };

        ArrayList<String> data = new ArrayList<>();
        data.addAll(Arrays.asList(safeData));

        if (!isTransfer) {
            data.set(2, "N/A");
        }
        else {
            data.set(2, "Yes");
        }

        for (int i = 0; i < 3; i++) {
            if (achievements.size() < i)
                achievements.add(achievements.get(i));
            else
                achievements.add("");
        }

        for (String achievement : achievements) {
            data.add(achievement);
        }

        return data;
    }

    @Override
    public JSONObject serializeJSON() {
        JSONObject baseObject = super.serializeJSON();

        // Add student-specific information
        try {
            baseObject.put("previousschoolname", previousSchoolName);
            baseObject.put("previousstartyear", previousStartYear);
            baseObject.put("previousendyear", previousEndYear);
            baseObject.put("major", major);
            baseObject.put("minor", minor);
            baseObject.put("istransfer", isTransfer);
            baseObject.put("achievements", new JSONArray(achievements));
            baseObject.put("gpa", gpa);
            baseObject.put("financialaid", financialAid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return baseObject;
    }
}
