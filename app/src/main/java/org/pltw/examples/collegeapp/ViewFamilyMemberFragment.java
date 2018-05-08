package org.pltw.examples.collegeapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import org.pltw.examples.collegeapp.info.BasicInfo;
import org.pltw.examples.collegeapp.info.CollegeAppDatabase;

import java.util.ArrayList;

public class ViewFamilyMemberFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_view_family_member, container, false);

        if (CollegeAppDatabase.siblings.size() == 0 && CollegeAppDatabase.guardians.size() == 0)
            return rootView;

        ExpandableListView expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);
        expandableListView.setAdapter(new OurExpandableAdapter(getContext()));

        return rootView;
    }
}

class OurExpandableAdapter extends BaseExpandableListAdapter {
    private Context senderContext;
    private ArrayList<BasicInfo> groups = new ArrayList<>();

    public OurExpandableAdapter(Context context) {
        senderContext = context;
        groups.addAll(CollegeAppDatabase.guardians);
        groups.addAll(CollegeAppDatabase.siblings);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition).getFullName();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)senderContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
        }

        TextView view = (TextView)convertView.findViewById(android.R.id.text1);
        view.setText((String)getGroup(groupPosition));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)senderContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_items_family_member, null);
        }

        BasicInfo info = (BasicInfo) getChild(groupPosition, childPosition);

        ((TextView)convertView.findViewById(R.id.textView_first_and_last)).setText(info.getFullName());
        ((TextView)convertView.findViewById(R.id.textView_date_of_birth)).setText(info.getBirthdate());
        ((TextView)convertView.findViewById(R.id.textView_occupation_view)).setText(info.getOccupation());
        ((TextView)convertView.findViewById(R.id.textView_age_view)).setText(String.valueOf(info.getAge()));
        ((TextView)convertView.findViewById(R.id.textView_relationship_view)).setText(info.getFamilyRelation());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}