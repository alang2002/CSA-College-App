package org.pltw.examples.collegeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewProfileFragment extends Fragment {
    TextView[] textViews = new TextView[13];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_view_profile, container, false);

        if (CollegeAppDatabase.student == null)
            return rootView;

        ArrayList<String> data = CollegeAppDatabase.student.getAsArray();

        textViews[0] = (TextView)rootView.findViewById(R.id.textView_first_and_last_name);
        textViews[1] = (TextView)rootView.findViewById(R.id.textView_date_of_birth);
        textViews[2] = (TextView)rootView.findViewById(R.id.textView_transfer_student);
        textViews[3] = (TextView)rootView.findViewById(R.id.textView_major);
        textViews[4] = (TextView)rootView.findViewById(R.id.textView_minor);
        textViews[5] = (TextView)rootView.findViewById(R.id.textView_gpa);
        textViews[6] = (TextView)rootView.findViewById(R.id.textView_financial_aid);
        textViews[7] = (TextView)rootView.findViewById(R.id.textView_previous_school_name);
        textViews[8] = (TextView)rootView.findViewById(R.id.textView_previous_start_year);
        textViews[9] = (TextView)rootView.findViewById(R.id.textView_previous_end_year);
        textViews[10] = (TextView)rootView.findViewById(R.id.textView_achievement_1);
        textViews[11] = (TextView)rootView.findViewById(R.id.textView_achievement_2);
        textViews[12] = (TextView)rootView.findViewById(R.id.textView_achievement_3);

        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setText(data.get(i));
        }

        return rootView;
    }
}
