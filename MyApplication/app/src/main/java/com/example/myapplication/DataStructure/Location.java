package com.example.myapplication.DataStructure;

import java.io.Serializable;

/**
 * Created by YoungJu on 2017-12-12.
 */

public class Location implements Serializable {
    private double x;
    private double y;
    public Location (double x,double y){
        setX(x);
        setY(y);
    }
    public double getX(){ return x; }
    public double getY(){ return y; }
    public void setX(double x){ this.x=x; }
    public void setY(double y){ this.y=y; }
}
