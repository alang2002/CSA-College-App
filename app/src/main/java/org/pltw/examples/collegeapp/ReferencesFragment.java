package org.pltw.examples.collegeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReferencesFragment extends Fragment {
    EditText firstNameEditText, lastNameEditText;
    EditText occupationEditText;
    EditText ageEditText;
    EditText companyName;
    EditText emailAddress;
    EditText relationship;
    EditText phoneNumber;
    EditText volunteerHours;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_references, container, false);

        // Setup view elements
        firstNameEditText = (EditText) rootView.findViewById(R.id.editText_first_name);
        lastNameEditText = (EditText) rootView.findViewById(R.id.editText_last_name);
        occupationEditText = (EditText) rootView.findViewById(R.id.editText_occupation);
        ageEditText = (EditText) rootView.findViewById(R.id.editText_age);
        companyName = (EditText) rootView.findViewById(R.id.editText_companyName);
        emailAddress = (EditText) rootView.findViewById(R.id.editText_emailAddress);
        relationship = (EditText) rootView.findViewById(R.id.editText_relationship);
        phoneNumber = (EditText) rootView.findViewById(R.id.editText_phoneNumber);
        volunteerHours = (EditText) rootView.findViewById(R.id.editText_volunteerHours);

        Button button = (Button)rootView.findViewById(R.id.button_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReference();
            }
        });

        return rootView;
    }

    public void addReference() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();

        String occupation = occupationEditText.getText().toString();

        String phoneNum = phoneNumber.getText().toString();

        int age;
        try {
            age = Integer.valueOf(ageEditText.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Please enter your reference's age", Toast.LENGTH_LONG).show();
            return;
        }

        if (firstName.isEmpty() || lastName.isEmpty()) {
            Toast.makeText(getContext(), "Please enter your reference's name", Toast.LENGTH_LONG).show();
            return;
        }

        if (occupation.isEmpty()) {
            Toast.makeText(getContext(), "Please enter your reference's occupation", Toast.LENGTH_LONG).show();
            return;
        }

        if (phoneNum.isEmpty()) {
            Toast.makeText(getContext(), "Please enter a phone number", Toast.LENGTH_LONG).show();
        }

    }
}
