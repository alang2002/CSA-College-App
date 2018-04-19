package org.pltw.examples.collegeapp;

import org.pltw.examples.collegeapp.info.BasicInfo;
import org.pltw.examples.collegeapp.info.ReferenceInfo;
import org.pltw.examples.collegeapp.info.StudentInfo;

import java.util.ArrayList;

/**
 * Created by AL313011 on 4/18/2018.
 */
public class CollegeAppDatabase {
    public static ArrayList<BasicInfo> guardians = new ArrayList<>();
    public static ArrayList<BasicInfo> siblings = new ArrayList<>();
    public static ArrayList<ReferenceInfo> references = new ArrayList<>();
    public static StudentInfo student;

    public static void addGuardian(BasicInfo guardianInfo) {
        guardians.add(guardianInfo);

        // TODO - Save to database
    }

    public static void addSibling(BasicInfo siblingInfo) {
        siblings.add(siblingInfo);

        // TODO - Save to database
    }

    public static void addReference(ReferenceInfo referenceInfo) {

    }
}
