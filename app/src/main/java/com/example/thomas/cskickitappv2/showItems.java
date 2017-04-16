//Abrahams, Thomas
//Kick it app.
//With help from many tutorials (Lynda, Stackoverflow), it was a fun experience. Android is alright.
package com.example.thomas.cskickitappv2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class showItems extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private String event1Address;
    private String name;
    private final Vector<String> theAddresses = new Vector<String>();
    private final String TAG = "CREATE_EVENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = mAuthSingleton.Instance().getmAuth();
        //This will hold our plans.

        setContentView(R.layout.activity_show_items);
        readData();
        mapButtonFunction();
        goingBack();
        btnChat();
    }
    public void btnChat()
    {
        Button chat = (Button)findViewById(R.id.btnChat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatButton();
            }
        });
    }
    public void chatButton()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your chat name:");

        final EditText chatNameField = new EditText(this);

        builder.setView(chatNameField);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = chatNameField.getText().toString();
                if(name != null)
                {
                    Intent intent = new Intent(showItems.this, chat.class);
                    intent.putExtra("userName", name);
                    startActivity(intent);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        builder.show();
    }
    public void readData()
    {
        Button findCodeBtn = (Button)findViewById(R.id.buttSearch);
        EditText searchTxt = (EditText)findViewById(R.id.etSearchText);

        findCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {



                FirebaseDatabase theDataBase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = theDataBase.getReference();
                //mDatabaseRef  = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("plans").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        final List<Plan> allThePlans = new ArrayList<Plan>();
                        //TODO Update the contents of fragments on data retrieval
                        //Plan myPlan = dataSnapshot.getValue(Plan.class);
                        //Get all of the children at this level

                        Iterable<DataSnapshot> planChildren = dataSnapshot.getChildren();
                        for (DataSnapshot child : planChildren) {
                            Plan myNewPlan = child.getValue(Plan.class);
                            allThePlans.add(myNewPlan);

                        }
                        /*
                        ArrayList<Plan> plans = new ArrayList<>();
                        for(DataSnapshot dataPlans : planChildren)
                        {
                            Plan myPlan = dataPlans.getValue(Plan.class);

                        }
                        */
                        if (allThePlans.size() > 0) {
                            EditText searchTxt = (EditText)findViewById(R.id.etSearchText);
                            EditText emailTxt = (EditText)findViewById(R.id.etEmailTxt);
                            String code = searchTxt.getText().toString();
                            String email = emailTxt.getText().toString();
                            int index = 0;
                            int foundIndex = 0;
                            boolean found = false;
                            for(index = 0; (index < allThePlans.size())&&!found; index++)
                            {
                                if((code.equals(allThePlans.get(index).getCode())) &&
                                        (email.equals(allThePlans.get(index).getEmail())))

                                {
                                    found = true;
                                    foundIndex = index;
                                }
                            }
                            if(found) {
                                Plan thePlan = allThePlans.get(foundIndex);
                                DefaultPlan defaultPlan  = new DefaultPlan();
                                defaultPlan.setPlanDefault(thePlan);    //Sets the plan
                                /*
                                EditText event1 = (EditText) findViewById(R.id.etEvent1);
                                EditText event2 = (EditText) findViewById(R.id.etEvent2);
                                EditText event3 = (EditText) findViewById(R.id.etEvent3);
                                //EditText event4 = (EditText) findViewById(R.id.etEvent4);
                                */
                                TextView event1 = (TextView) findViewById(R.id.tvEventNum1);
                                TextView event2 = (TextView) findViewById(R.id.tvEventNum2);
                                TextView event3 = (TextView) findViewById(R.id.tvEventNum3);
                                //TextView event4 = (TextView) findViewById(R.id.tvEventNum4);
                                event1Address = thePlan.Event0.getEventAddress();
                                theAddresses.add(thePlan.Event0.getEventAddress());
                                event1.setText(thePlan.Event0.getEventName() + "\n" +
                                        thePlan.Event0.getEventAddress() + "\n" +
                                        thePlan.Event0.getCategory());

                                theAddresses.add(thePlan.Event1.getEventAddress());
                                event2.setText(thePlan.Event1.getEventName() + "\n" +
                                        thePlan.Event1.getEventAddress() + "\n" +
                                        thePlan.Event1.getCategory());
                                theAddresses.add(thePlan.Event2.getEventAddress());
                                event3.setText(thePlan.Event2.getEventName() + "\n" +
                                        thePlan.Event2.getEventAddress() + "\n" +
                                        thePlan.Event2.getCategory());
                                /*
                                event4.setText(thePlan.Event3.getEventName() + "\n" +
                                        thePlan.Event3.getEventAddress() + "\n" +
                                        thePlan.Event3.getCategory());
                                */
                            }
                            else
                            {
                                Toast.makeText(showItems.this, "No fun here!", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                        else
                        {
                            Toast.makeText(showItems.this, "Nothing matches that query", Toast.LENGTH_SHORT)
                                    .show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(showItems.this, "Account creation failed", Toast.LENGTH_SHORT)
                                .show();

                    }
                });

            }
        });
    }
    public void mapButtonFunction()
    {

        Button map1Button = (Button)findViewById(R.id.btnMapEvent1);
        map1Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                TextView eventMapBtn1 = (TextView)findViewById(R.id.tvEventNum1);
                String theAddress = event1Address;
                if(!event1Address.isEmpty())
                    sendToActionIntent(theAddress);


            }
        });

        Button map2Button = (Button)findViewById(R.id.btnMapEvent2);
        map2Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                TextView eventMapBtn1 = (TextView)findViewById(R.id.tvEvent2);
                String theAddress = theAddresses.get(1);
                if(!theAddress.isEmpty())
                    sendToActionIntent(theAddress);


            }
        });
        Button map3Button = (Button)findViewById(R.id.btnMapEvent3);
        map3Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                TextView eventMapBtn1 = (TextView)findViewById(R.id.tvEvent2);
                String theAddress = theAddresses.get(2);
                if(!theAddress.isEmpty())
                    sendToActionIntent(theAddress);


            }
        });
    }
    public void sendToActionIntent(String address)
    {
        StringBuilder uri = new StringBuilder("geo:0,0?q=");
        uri.append(address);
        uri.append("?z=10");

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri.toString()));
        startActivity(intent);


    }
    public void goingBack()
    {
        Button back = (Button)findViewById(R.id.btnBackToMain);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(showItems.this, Main_Menu.class);
                startActivity(myIntent);
            }
        });
    }
}


