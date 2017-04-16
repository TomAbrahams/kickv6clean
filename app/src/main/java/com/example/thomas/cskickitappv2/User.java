//Abrahams, Thomas
//Kick it app.
//With help from many tutorials (Lynda, Stackoverflow), it was a fun experience. Android is alright.
package com.example.thomas.cskickitappv2;

import java.text.SimpleDateFormat;

/**
 * Created by Thomas on 2/15/2017.
 */


public class User {
    //Add Encapsulation.
    public String name;
    public String game;
    public String show;
    public String userIdKey;
    public String uniqueKey;
    public String theDate;
    public User()
    {
        name = "No name";
        game = "No game";
        show = "No show";
        userIdKey = "NOPE";
        uniqueKey = "Nothing";
        theDate = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    }

    public User(String myName, String myGame, String myShow, String myKey, String myUniqueKey) {
        name = myName;
        game = myGame;
        show = myShow;
        userIdKey = myKey;
        uniqueKey = myUniqueKey;
        theDate = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    }
}
