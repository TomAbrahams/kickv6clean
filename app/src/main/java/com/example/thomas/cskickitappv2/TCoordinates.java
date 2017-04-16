//Abrahams, Thomas
//Kick it app.
//With help from many tutorials (Lynda, Stackoverflow), it was a fun experience. Android is alright.
package com.example.thomas.cskickitappv2;

/**
 * Created by Thomas on 3/1/2017.
 */

public class TCoordinates {
    private double tLongitude = 0.0;
    private double tLatitidue = 0.0;

    public TCoordinates() {
        tLongitude = 0.0;
        tLatitidue = 0.0;

    }
    public TCoordinates(double myLongitude, double myLatitidue)
    {
        tLongitude = myLongitude;
        tLatitidue = myLatitidue;

    }
    public double getLongitude()
    {
        return tLongitude;
    }
    public double getLatitude()
    {
        return tLatitidue;

    }
    public void setLongitude(double myLongitude)
    {
        tLongitude = myLongitude;
    }
    public void setLatitude(double myLatitude)
    {
        tLatitidue = myLatitude;
    }
}
