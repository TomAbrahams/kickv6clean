//Abrahams, Thomas
//Kick it app.
//With help from many tutorials (Lynda, Stackoverflow), it was a fun experience. Android is alright.
package com.example.thomas.cskickitappv2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.thomas.cskickitappv2.database.DBAssistant;
import com.google.firebase.auth.FirebaseAuth;

public class Main_Menu extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);
        //TODO: Get a reference to the firebase auth object
        mAuth = FirebaseAuth.getInstance();
        clickingButtons();
        signOut();

    }
    private void clickingButtons(){
        Button createEvent = (Button)findViewById(R.id.btnCreateEvent);
        Button joinEvent = (Button)findViewById(R.id.btnJoinEvent);
        Button loadEvent = (Button)findViewById(R.id.btnLoadEvent);

        createEvent.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent myIntent = new Intent(Main_Menu.this, CurrentPlan.class);
                    //Bundle bundle = new Bundle();
                    //bundle.putInt("eventNum",theEventNumber);
                    //myIntent.putExtras(bundle);
                    startActivity(myIntent);


                }
            }
        );
        joinEvent.setOnClickListener( new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v)
                                                            {
                                                                Intent myIntent = new Intent(Main_Menu.this, showItems.class);
                                                                //Bundle bundle = new Bundle();
                                                                //bundle.putInt("eventNum",theEventNumber);
                                                                //myIntent.putExtras(bundle);
                                                                startActivity(myIntent);


                                                            }
                                                        }
        );
        loadEvent.setOnClickListener( new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v)
                                          {
                                              Intent myIntent = new Intent(Main_Menu.this, LoadPlans.class);
                                              //Bundle bundle = new Bundle();
                                              //bundle.putInt("eventNum",theEventNumber);
                                              //myIntent.putExtras(bundle);
                                              startActivity(myIntent);


                                          }
                                      }
        );

    }

    private void signOut() {

        Button signOutBtn = (Button) findViewById(R.id.btnLogout);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              mAuth.signOut();
                                              DefaultPlan myDefault = new DefaultPlan();
                                              myDefault.clearEvent1();
                                              myDefault.clearEvent2();
                                              myDefault.clearEvent3();
                                              Toast.makeText(Main_Menu.this, "Logged out", Toast.LENGTH_SHORT)
                                                      .show();

                                              Intent myIntent = new Intent(Main_Menu.this, sign_in.class);
                                              //Bundle bundle = new Bundle();
                                              //bundle.putInt("eventNum",theEventNumber);
                                              //myIntent.putExtras(bundle);
                                              startActivity(myIntent);


                                          }
                                      }
        );
    }
}
