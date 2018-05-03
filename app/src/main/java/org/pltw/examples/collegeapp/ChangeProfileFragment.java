package org.pltw.examples.collegeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import org.pltw.examples.collegeapp.info.StudentInfo;

import java.util.Locale;

public class ChangeProfileFragment extends Fragment {
    EditText firstNameEditText, lastNameEditText;
    Spinner daySpinner, monthSpinner, yearSpinner;
    EditText ageEditText;

    Switch transferStudentSwitch;
    EditText majorEditText, minorEditText;
    EditText gpaEditText, financialAidEditText;
    EditText previousSchoolNameEditText;
    EditText previousStartYearEditText, previousEndYearEditText;
    EditText[] achievementEditTexts = new EditText[3];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_change_profile, container, false);

        firstNameEditText = (EditText)rootView.findViewById(R.id.editText_first_name);
        lastNameEditText = (EditText)rootView.findViewById(R.id.editText_last_name);
        daySpinner = (Spinner)rootView.findViewById(R.id.spinner_day);
        monthSpinner = (Spinner)rootView.findViewById(R.id.spinner_month);
        yearSpinner = (Spinner)rootView.findViewById(R.id.spinner_year);
        ageEditText = (EditText)rootView.findViewById(R.id.editText_age);

        transferStudentSwitch = (Switch)rootView.findViewById(R.id.switch_transfer_student);
        majorEditText = (EditText)rootView.findViewById(R.id.editText_major);
        minorEditText = (EditText)rootView.findViewById(R.id.editText_minor);
        gpaEditText = (EditText)rootView.findViewById(R.id.editText_gpa);
        financialAidEditText = (EditText)rootView.findViewById(R.id.editText_financial_aid);
        previousSchoolNameEditText = (EditText)rootView.findViewById(R.id.editText_previous_school_name);
        previousStartYearEditText = (EditText)rootView.findViewById(R.id.editText_start_year);
        previousEndYearEditText = (EditText)rootView.findViewById(R.id.editText_end_year);

        achievementEditTexts[0] = (EditText)rootView.findViewById(R.id.editText_achievement_1);
        achievementEditTexts[1] = (EditText)rootView.findViewById(R.id.editText_achievement_2);
        achievementEditTexts[2] = (EditText)rootView.findViewById(R.id.editText_achievement_3);

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

        (rootView.findViewById(R.id.button_set_student)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        return rootView;
    }

    private void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    private void submit() {
        String firstName = firstNameEditText.getText().toString();
        if (firstName.isEmpty()) {
            showError("Please enter first name");
            return;
        }

        String lastName = lastNameEditText.getText().toString();
        if (lastName.isEmpty()) {
            showError("Please enter last name");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageEditText.getText().toString());
        } catch (NumberFormatException e) {
            showError("Please enter a valid age");
            return;
        }

        String major = majorEditText.getText().toString();
        if (major.isEmpty()) {
            showError("Please enter a major");
            return;
        }

        double gpa;
        try {
            gpa = Double.parseDouble(gpaEditText.getText().toString());
        } catch (NumberFormatException e) {
            showError("Please enter a valid GPA");
            return;
        }

        double financialAid;
        try {
            financialAid = Double.parseDouble(financialAidEditText.getText().toString());
        } catch (NumberFormatException e) {
            financialAid = 0;
        }

        String previousSchoolName = previousSchoolNameEditText.getText().toString();
        int startYear, endYear;
        try {
            startYear = Integer.parseInt(previousStartYearEditText.getText().toString());
            endYear = Integer.parseInt(previousEndYearEditText.getText().toString());
        } catch (NumberFormatException e) {
            if (previousSchoolName.isEmpty()) {
                startYear = 0;
                endYear = 0;
            } else {
                showError("Please enter a start and end year for your school");
                return;
            }
        }

        String minor = minorEditText.getText().toString();

        String month = monthSpinner.getSelectedItem().toString();
        int day = Integer.parseInt(daySpinner.getSelectedItem().toString());
        int year = Integer.parseInt(yearSpinner.getSelectedItem().toString());

        boolean isTransfer = transferStudentSwitch.isChecked();

        StudentInfo student = new StudentInfo(firstName, lastName);
        student.setBirthdate(String.format(Locale.US, "%s/%d/%d", month, day, year));
        student.setAge(age);
        student.setTransfer(isTransfer);
        student.setMajor(major);
        student.setMinor(minor);
        student.setGpa(gpa);
        student.setFinancialAid(financialAid);
        student.setPreviousSchoolName(previousSchoolName);
        student.setPreviousStartYear(startYear);
        student.setPreviousEndYear(endYear);

        for (EditText achievementEditText : achievementEditTexts) {
            String achievement = achievementEditText.getText().toString();
            if (achievement.isEmpty())
                continue;

            student.addAchievement(achievement);
        }

        CollegeAppDatabase.setStudent(student, getContext());

        Toast.makeText(getContext(), "Setting student...", Toast.LENGTH_SHORT).show();

        clearElements();
    }

    private String[] getMonths() {
        return new String[]{"January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};
    }
    private String[] getDays() {
        return new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
                "30", "31"};
    }

    private String[] getYears() {
        String[] years = new String[103];

        for (int i = 0; i < 103; i++) {
            years[i] = String.valueOf(2002 - i);
        }
        return years;
    }

    private void clearElements() {
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        monthSpinner.setSelection(0);
        daySpinner.setSelection(0);
        yearSpinner.setSelection(0);
        ageEditText.setText("");
        transferStudentSwitch.setChecked(false);
        majorEditText.setText("");
        minorEditText.setText("");
        gpaEditText.setText("");
        financialAidEditText.setText("");
        previousSchoolNameEditText.setText("");
        previousStartYearEditText.setText("");
        previousEndYearEditText.setText("");
        for (EditText achievementEditText : achievementEditTexts) {
            achievementEditText.setText("");
        }
    }
}
