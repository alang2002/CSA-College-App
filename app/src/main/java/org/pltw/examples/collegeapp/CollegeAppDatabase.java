package org.pltw.examples.collegeapp;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.pltw.examples.collegeapp.info.BasicInfo;
import org.pltw.examples.collegeapp.info.ReferenceInfo;
import org.pltw.examples.collegeapp.info.StudentInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by AL313011 on 4/18/2018.
 */
public class CollegeAppDatabase {
    public static ArrayList<BasicInfo> guardians = new ArrayList<>();
    public static ArrayList<BasicInfo> siblings = new ArrayList<>();
    public static ArrayList<ReferenceInfo> references = new ArrayList<>();
    public static StudentInfo student;
    public static final String FILENAME = "database.json";

    /**
     * TODO
     * Checks to see if a database file exists. If yes, fill in our data.
     * If no, create a default one.
     */
    public static void init(Context c) {
        JSONObject persistent = readPersistent(c);
        if (persistent == null)
            return;

        // TODO - Student

        /* TODO - Fill in arrays */
        // TODO - Guardians
        // TODO - Siblings
        // TODO - References
    }

    public static JSONObject readPersistent(Context c) {
        String response = "";

        try {
            InputStream inputStream = c.openFileInput(FILENAME);
            if (inputStream == null)
                return null;
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String receiveString;
            StringBuilder stringBuilder = new StringBuilder();
            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString);
            }

            inputStream.close();
            response = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            writeFirstFile(c);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addGuardian(BasicInfo guardianInfo) {
        guardians.add(guardianInfo);
        writeNewFile();
    }

    public static void addSibling(BasicInfo siblingInfo) {
        siblings.add(siblingInfo);
        writeNewFile();
    }

    public static void addReference(ReferenceInfo referenceInfo) {
        references.add(referenceInfo);
        writeNewFile();
    }

    private static void writeFirstFile(Context c) {
        JSONObject defaultObject = new JSONObject();

        try {
            OutputStreamWriter writer = new OutputStreamWriter(c.openFileOutput(FILENAME, Context.MODE_PRIVATE));
            writer.write(defaultObject.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeNewFile() {
        // TODO - Write new database file
    }
}
