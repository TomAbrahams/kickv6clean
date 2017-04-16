//Abrahams, Thomas
//Kick it app.
//With help from many tutorials (Lynda, Stackoverflow), it was a fun experience. Android is alright.
package com.example.thomas.cskickitappv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thomas.cskickitappv2.DataPlanAdapters.DataPlanAdapter;
import com.example.thomas.cskickitappv2.DataPlanAdapters.DataPlanAdapterListView;
import com.example.thomas.cskickitappv2.database.DataSource;

import java.util.List;

public class LoadPlans extends AppCompatActivity {

    DataSource myDataSource;
    List<Plan> listPlansDB;
    List<String> planNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_plans);

        goingBack();


        try {

            myDataSource = new DataSource(this);
            myDataSource.open();

            Toast.makeText(this, "Database acquired!", Toast.LENGTH_SHORT).show();
            //It's time to add the items.
            listPlansDB = myDataSource.getAllPlans();
//            for (Plan plan : listPlansDB ){
//                planNames.add(plan.getPlanName());
//            }
            String myData = listPlansDB.get(0).getPlanName();
            DataPlanAdapter adapter = new DataPlanAdapter(this, listPlansDB);
            //Collections.sort(planNames);
            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(
            //        this, android.R.layout.simple_list_item_1,planNames);
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvPlans);
            recyclerView.setAdapter(adapter);



        }
        catch (Exception e) {
            Toast.makeText(this, "Database not acquired!", Toast.LENGTH_SHORT).show();
        }


    }



    @Override
    protected void onPause() {
        //Handles database leaks.
        super.onPause();
        myDataSource.close();
    }
    @Override
    protected void onResume() {
        super.onResume();
        myDataSource.open();
    }
    public void goingBack()
    {
        Button back = (Button)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoadPlans.this, Main_Menu.class);
                startActivity(myIntent);
            }
        });
    }
}
