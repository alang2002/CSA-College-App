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

import org.pltw.examples.collegeapp.info.CollegeAppDatabase;
import org.pltw.examples.collegeapp.info.ReferenceInfo;

import java.util.ArrayList;

public class ViewReferencesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_view_references, container, false);

        if (CollegeAppDatabase.references.size() == 0)
            return rootView;

        ExpandableListView expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView_references);
        expandableListView.setAdapter(new OurExpandableAdapterReferences(getContext()));

        return rootView;
    }
}


class OurExpandableAdapterReferences extends BaseExpandableListAdapter {
    private Context senderContext;
    private ArrayList<ReferenceInfo> groups = new ArrayList<>();

    public OurExpandableAdapterReferences(Context context) {
        senderContext = context;
        groups.addAll(CollegeAppDatabase.references);
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
            convertView = inflater.inflate(R.layout.list_items_references, null);
        }

        ReferenceInfo info = (ReferenceInfo) getChild(groupPosition, childPosition);

        ((TextView)convertView.findViewById(R.id.textView_references_name)).setText(info.getFullName());
        ((TextView)convertView.findViewById(R.id.textView_references_age)).setText(String.valueOf(info.getAge()));
        ((TextView)convertView.findViewById(R.id.textView_references_occupation)).setText(info.getOccupation());
        ((TextView)convertView.findViewById(R.id.textView_references_company)).setText(info.getCompanyName());
        ((TextView)convertView.findViewById(R.id.textView_references_phone)).setText(info.getPhoneNumber());
        ((TextView)convertView.findViewById(R.id.textView_references_email)).setText(info.getEmailAddress());
        ((TextView)convertView.findViewById(R.id.textView_references_relationship)).setText(info.getRelation());
        ((TextView)convertView.findViewById(R.id.textView_volunteer_hours)).setText(String.valueOf(info.getVolunteerHours()));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
