package org.pltw.examples.collegeapp.info;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
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

public class CollegeAppDatabase {
    public static ArrayList<BasicInfo> guardians = new ArrayList<>();
    public static ArrayList<BasicInfo> siblings = new ArrayList<>();
    public static ArrayList<ReferenceInfo> references = new ArrayList<>();
    public static StudentInfo student;
    public static final String FILENAME = "database.json";
    private static final String TAG = "CollegeAppDatabase";

    /**
     * Checks to see if a database file exists. If yes, fill in our data.
     * If no, create a default one.
     */
    public static void init(Context c) {
        JSONObject persistent = readPersistent(c);
        if (persistent == null)
            return;

        try {
            // Guardians
            JSONArray jsonGuardians = persistent.getJSONArray("guardians");
            for (int i = 0; i < jsonGuardians.length(); i++) {
                guardians.add(new BasicInfo(jsonGuardians.getJSONObject(i)));
            }

            // Siblings
            JSONArray jsonSiblings = persistent.getJSONArray("siblings");
            for (int i = 0; i < jsonSiblings.length(); i++) {
                siblings.add(new BasicInfo(jsonSiblings.getJSONObject(i)));
            }

            // References
            JSONArray jsonReferences = persistent.getJSONArray("references");
            for (int i = 0; i < jsonReferences.length(); i++) {
                references.add(new ReferenceInfo(jsonReferences.getJSONObject(i)));
            }

            JSONObject studentJSONObject = persistent.getJSONObject("student");
            if (studentJSONObject != JSONObject.NULL)
                student = new StudentInfo(studentJSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Log stuff
        Log.i(TAG, "Number of guardians: " + guardians.size());
        Log.i(TAG, "Number of siblings: " + siblings.size());
        Log.i(TAG, "Number of references: " + references.size());
        Log.i(TAG, "Student exists: " + String.valueOf(student != null));
    }

    /**
     * Return a JSONObject containing the data stored on this device.
     * @param c {@link Context} of the sender
     * @return Persistent JSON data
     */
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

    /**
     * Set the student of the database
     * @param studentInfo The student to be set
     * @param c The context of the application
     */
    public static void setStudent(StudentInfo studentInfo, Context c) {
        student = studentInfo;
        writeNewFile(c);
    }

    /**
     * Add a guardian to the database
     * @param guardianInfo The guardian to be added
     * @param c The context of the application
     */
    public static void addGuardian(BasicInfo guardianInfo, Context c) {
        guardians.add(guardianInfo);
        writeNewFile(c);
    }

    /**
     * Add a sibling to the database
     * @param siblingInfo The sibling to be added
     * @param c The context of the application
     */
    public static void addSibling(BasicInfo siblingInfo, Context c) {
        siblings.add(siblingInfo);
        writeNewFile(c);
    }

    /**
     * Add a reference to the database
     * @param referenceInfo The reference to be added
     * @param c The context of the application
     */
    public static void addReference(ReferenceInfo referenceInfo, Context c) {
        references.add(referenceInfo);
        writeNewFile(c);
    }

    /**
     * Write an empty JSON file
     * @param c The context of the application
     */
    private static void writeFirstFile(Context c) {
        JSONObject defaultObject = new JSONObject();

        try {
            defaultObject.put("student", JSONObject.NULL);
            defaultObject.put("guardians", new JSONArray());
            defaultObject.put("siblings", new JSONArray());
            defaultObject.put("references", new JSONArray());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            OutputStreamWriter writer = new OutputStreamWriter(c.openFileOutput(FILENAME, Context.MODE_PRIVATE));
            writer.write(defaultObject.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Turn the student, guardians, siblings and references into one JSON object
     * @return The JSON object representing this database
     * @throws JSONException If the data cannot be converted into JSON
     */
    private static JSONObject serializeDatabase() throws JSONException {
        JSONObject allData = new JSONObject();

        if (student != null)
            allData.put("student", student.serializeJSON());
        else
            allData.put("student", JSONObject.NULL);

        // Serialize guardians
        JSONArray jsonGuardians = new JSONArray();
        for (BasicInfo guardian : guardians) {
            jsonGuardians.put(guardian.serializeJSON());
        }

        allData.put("guardians", jsonGuardians);

        // Serialize siblings
        JSONArray jsonSiblings = new JSONArray();
        for (BasicInfo sibling : siblings) {
            jsonSiblings.put(sibling.serializeJSON());
        }

        allData.put("siblings", jsonSiblings);

        // Serialize siblings
        JSONArray jsonReferences = new JSONArray();
        for (ReferenceInfo reference : references) {
            jsonReferences.put(reference.serializeJSON());
        }

        allData.put("references", jsonReferences);

        return allData;
    }

    /**
     * Write the serialized database to a file
     * @param c The context of the application
     */
    private static void writeNewFile(Context c) {
        try {
            JSONObject allData = serializeDatabase();
            OutputStreamWriter writer = new OutputStreamWriter(c.openFileOutput(FILENAME, Context.MODE_PRIVATE));
            writer.write(allData.toString());
            writer.close();
            Log.i("CollegeAppDatabase", "Wrote database to " + c.getFilesDir().getAbsolutePath());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
}
