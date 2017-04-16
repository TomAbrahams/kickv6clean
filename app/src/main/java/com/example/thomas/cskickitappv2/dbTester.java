//Abrahams, Thomas
//Kick it app.
//With help from many tutorials (Lynda, Stackoverflow), it was a fun experience. Android is alright.
package com.example.thomas.cskickitappv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class dbTester extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private String myEmail;
    private String myPass;
    private final String TAG = "FB_SIGNIN";
    private ArrayList<User> myUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        //This gives me the info passed
        final String myId = myIntent.getStringExtra("currentUser");
        myEmail = myIntent.getStringExtra("email");
        myPass = myIntent.getStringExtra("password");
        String theFireId = "none";

        //This gets the authorization.
        mAuth = mAuthSingleton.Instance().getmAuth();
        //TODO: Get a reference to the firebase auth object

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null)
                {
                    Toast.makeText(dbTester.this, "Sign in"+ user.getUid(), Toast.LENGTH_SHORT)
                            .show();
                    //theFireId= user.getUid();

                }
                else {
                    Toast.makeText(dbTester.this, "Signed out", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        };

        final String testId = theFireId;
        setContentView(R.layout.activity_db_tester);
        displayName();
        fireAddUser();
        //searchByName();
    }

    private void displayName()
    {
        TextView tvDisplay =  (TextView)findViewById(R.id.tvIdentity);
        String batman = mAuthSingleton.Instance().getmAuth().getCurrentUser().getUid();
        String name = mAuthSingleton.Instance().getmAuth().getCurrentUser().getEmail();
        tvDisplay.setText("The userID is:" + batman + " Email:" + name);
    }
    private void fireAddUser() {
        //MDatabase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //1. Get a reference to the button
        Button messageButton = (Button)findViewById(R.id.btnAdd);
        //Textboxes



        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                EditText nameEdit = (EditText)findViewById(R.id.etName);
                EditText gameEdit = (EditText)findViewById(R.id.etGame);
                EditText showEdit = (EditText)findViewById(R.id.etShow);
                //2. Make a listener for the event.
                String myName = nameEdit.getText().toString();
                String myGame = gameEdit.getText().toString();
                String myShow = showEdit.getText().toString();
                String uniqueKey =  mAuthSingleton.Instance().getmAuth().getCurrentUser().getUid();
                String theKey = mDatabase.child("users").push().getKey();

                User user = new User(myName, myGame, myShow,uniqueKey, theKey);
                mDatabase.child("users").child("idNum-" + theKey).setValue(user);
                //The key

                //Log.i("MyFirstApp", "You clicked the button!");
                Toast.makeText(getBaseContext(), "You've clicked the button! Key: "+theKey,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    private void searchByName()
    {
        //Gets userID


        //2. Make a listener for the event.

        mDatabase = FirebaseDatabase.getInstance().getReference();


        //1. Get a reference to the button
        //Button searchButton = (Button)findViewById(R.id.btnSearch);
        //Textboxes

        //searchButton.setOnClickListener(new View.OnClickListener() {
        //public void onClick(View v)
        //{
                /*
                String userID = mAuthSingleton.Instance().getmAuth().getCurrentUser().getUid();
                EditText nameEdit = (EditText)findViewById(R.id.etName);
                EditText gameEdit = (EditText)findViewById(R.id.etGame);
                EditText showEdit = (EditText)findViewById(R.id.etShow);
                String myName = nameEdit.getText().toString();
                String myGame = gameEdit.getText().toString();
                String myShow = showEdit.getText().toString();
                if((!myName.isEmpty()))
                {

                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
                    myRef.child("users").orderByChild("theDate").equalTo(userID).limitToLast(1).addListenerForSingleValueEvent(
                            new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Toast.makeText(getBaseContext(), "Come On!: ",
                                            Toast.LENGTH_LONG).show();
                                    //User user = dataSnapshot.getValue(User.class);
                                    if(dataSnapshot.exists()) {
                                        EditText gameEditValue = (EditText) findViewById(R.id.etGame);
                                        for(DataSnapshot finder : dataSnapshot.getChildren())
                                        {
                                            String name = (String) dataSnapshot.child("name").getValue();
                                            gameEditValue.setText(name);

                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                                }
                            }
                    );

                }
                else
                    Toast.makeText(getBaseContext(), "NO!: ",
                            Toast.LENGTH_LONG).show();

            }
        });

        */


    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
        myUsers.clear();

        for(DataSnapshot theDataShot: dataSnapshot.getChildren())
        {
            User theUser = theDataShot.getValue(User.class);
            myUsers.add(theUser);
        }
    }
    public ArrayList<User> retrieve()
    {
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return myUsers;
    }

    private boolean checkFormFields() {
        String email, password;

        email = myEmail;
        password = myPass;

        if (email.isEmpty()) {
            Toast.makeText(dbTester.this, "Email is blank", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (password.isEmpty()){
            Toast.makeText(dbTester.this, "password is blank", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }

        return true;
    }

}
