//Abrahams, Thomas
//Kick it app.
//With help from many tutorials (Lynda, Stackoverflow), it was a fun experience. Android is alright.
package com.example.thomas.cskickitappv2;

/**
 * Created by Thomas on 2/15/2017.
 */

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Thomas on 2/11/2017.
 */


public class mAuthSingleton {
    private static mAuthSingleton instance;
    private FirebaseAuth myAuth;

    public static mAuthSingleton Instance()
    {
        if(instance == null)
            instance = new mAuthSingleton();
        return instance;
    }

    public FirebaseAuth getmAuth()
    {
        return myAuth;
    }
    public void setmAuth(FirebaseAuth myAuth)
    {
        this.myAuth = myAuth;
    }

}
