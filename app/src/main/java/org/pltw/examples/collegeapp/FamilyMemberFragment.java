package org.pltw.examples.collegeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;
import org.pltw.examples.collegeapp.info.BasicInfo;

import java.util.Arrays;
import java.util.Locale;

/**
 * Created by AL313011 on 4/13/2018.
 */
public class FamilyMemberFragment extends Fragment{
    EditText firstNameEditText, lastNameEditText;
    Spinner daySpinner, monthSpinner, yearSpinner;
    EditText occupationEditText;
    EditText ageEditText;
    RadioButton guardian, sibling;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_family_member, container, false);

        // for every child view
        // if view instance of edittext - store text in dictionary
        // if view instance of spinner - store selected value in dictionary

        // Setup view elements
        firstNameEditText = (EditText) rootView.findViewById(R.id.editText_first_name);
        lastNameEditText = (EditText) rootView.findViewById(R.id.editText_last_name);
        daySpinner = (Spinner) rootView.findViewById(R.id.spinner_day);
        monthSpinner = (Spinner) rootView.findViewById(R.id.spinner_month);
        yearSpinner = (Spinner) rootView.findViewById(R.id.spinner_year);
        occupationEditText = (EditText) rootView.findViewById(R.id.editText_occupation);
        ageEditText = (EditText) rootView.findViewById(R.id.editText_age);
        guardian = (RadioButton) rootView.findViewById(R.id.radioButton_guardian);
        sibling = (RadioButton) rootView.findViewById(R.id.radioButton_sibling);

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_list_item_1, getMonths());
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_list_item_1, getDays());
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, getYears());
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        Button button = (Button)rootView.findViewById(R.id.button_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFamilyMember();
            }
        });

        return rootView;
    }



    private String[] getMonths() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August",
        "September", "October", "November", "December"};
        return months;
    }
    private String[] getDays() {
        String[] days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
        "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
        "30", "31"};
        return days;
    }
    private String[] getYears() {
        String[] years = new String[103];

        for (int i = 0; i < 103; i++) {
            years[i] = String.valueOf(2002 - i);
        }
        return years;
    }

    public void addFamilyMember() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();

        int day = Integer.valueOf((String) daySpinner.getSelectedItem());
        String month = (String) monthSpinner.getSelectedItem();
        int year = Integer.valueOf((String) yearSpinner.getSelectedItem());

        String occupation = occupationEditText.getText().toString();

        boolean isGuardian = guardian.isSelected();
        boolean isSibling = sibling.isSelected();

        int age;
        try {
            age = Integer.valueOf(ageEditText.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Please enter your age", Toast.LENGTH_LONG).show();
            return;
        }

        if (firstName.isEmpty() || lastName.isEmpty()) {
            Toast.makeText(getContext(), "Please enter your name", Toast.LENGTH_LONG).show();
            return;
        }

        // Add family member to database
        Toast.makeText(getContext(), "Adding family member to database...", Toast.LENGTH_SHORT).show();

        BasicInfo familyMember = new BasicInfo(firstName, lastName);
        familyMember.setFirstName("Hello");

        String birthdate = String.format(Locale.US, "%s/%d/%d", month, day, year);
        familyMember.setBirthdate(birthdate);

        familyMember.setOccupation(occupation);
        familyMember.setAge(age);

        if (isGuardian)
            CollegeAppDatabase.addGuardian(familyMember);
        else
            CollegeAppDatabase.addSibling(familyMember);

        System.out.println(familyMember);
        //clearElements();
    }

    private void clearElements() {
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        monthSpinner.setSelection(0, true);
        daySpinner.setSelection(0, true);
        yearSpinner.setSelection(0, true);
        occupationEditText.setText("");
        ageEditText.setText("");
    }
}
