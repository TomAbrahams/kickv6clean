//Abrahams, Thomas
//Kick it app.
//With help from many tutorials (Lynda, Stackoverflow), it was a fun experience. Android is alright.
package com.example.thomas.cskickitappv2.DataPlanAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thomas.cskickitappv2.Plan;
import com.example.thomas.cskickitappv2.R;

import java.util.List;

/**
 * Created by Thomas on 4/1/2017.
 */

public class DataPlanAdapterListView extends ArrayAdapter<Plan> {

    List<Plan> myPlans;
    LayoutInflater myInflater;
    public DataPlanAdapterListView(Context context, List<Plan> objects) {
        super(context, R.layout.list_plan, objects);

        myPlans = objects;
        myInflater = LayoutInflater.from(context);


    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Reference a non- null view in each row.
        if (convertView == null) {
            convertView = myInflater.inflate(R.layout.list_plan,parent, false);

        }
        TextView tvName = (TextView) convertView.findViewById(R.id.itemNameText);

        Plan plan = myPlans.get(position);
        tvName.setText(plan.getPlanName());


        return convertView;
    }
}
