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

import org.pltw.examples.collegeapp.info.CollegeAppDatabase;
import org.pltw.examples.collegeapp.info.ReferenceInfo;

public class AddReferenceFragment extends Fragment {
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
        View rootView = inflater.inflate(R.layout.fragment_change_references, container, false);

        // Setup view elements
        firstNameEditText = (EditText) rootView.findViewById(R.id.editText_firstName);
        lastNameEditText = (EditText) rootView.findViewById(R.id.editText_lastName);
        occupationEditText = (EditText) rootView.findViewById(R.id.editText_occupation);
        ageEditText = (EditText) rootView.findViewById(R.id.editText_age);
        companyName = (EditText) rootView.findViewById(R.id.editText_companyName);
        emailAddress = (EditText) rootView.findViewById(R.id.editText_emailAddress);
        relationship = (EditText) rootView.findViewById(R.id.editText_relationship);
        phoneNumber = (EditText) rootView.findViewById(R.id.editText_phoneNumber);
        volunteerHours = (EditText) rootView.findViewById(R.id.editText_volunteerHours);

        Button button = (Button)rootView.findViewById(R.id.button_addReference);
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

        String compName = companyName.getText().toString();

        String emailAdd = emailAddress.getText().toString();

        String relation = relationship.getText().toString();

        int volunHours = Integer.valueOf(volunteerHours.getText().toString());

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

        if (compName.isEmpty()) {
            Toast.makeText(getContext(), "Please enter company name for reference", Toast.LENGTH_LONG).show();
        }

        if (emailAdd.isEmpty()) {
            Toast.makeText(getContext(), "Please enter email address for reference", Toast.LENGTH_LONG).show();
        }

        if (relation.isEmpty()) {
            Toast.makeText(getContext(), "Please enter their relationship to you", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(getContext(), "Adding reference to database...", Toast.LENGTH_SHORT).show();

        ReferenceInfo reference = new ReferenceInfo(firstName, lastName);
        reference.setOccupation(occupation);
        reference.setAge(age);
        reference.setCompanyName(compName);
        reference.setEmailAddress(emailAdd);
        reference.setPhoneNumber(phoneNum);
        reference.setRelation(relation);
        try {
            reference.setVolunteerHours(volunHours);
        }
        catch (NullPointerException e) {
            reference.setVolunteerHours(0);
        }

        CollegeAppDatabase.addReference(reference, getContext());

        clearElements();
    }

    private void clearElements() {
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        occupationEditText.setText("");
        ageEditText.setText("");
        phoneNumber.setText("");
        companyName.setText("");
        emailAddress.setText("");
        relationship.setText("");
        volunteerHours.setText("");
    }
}
