package com.example.myapplication.DataStructure;

/**
 * Created by YoungJu on 2017-12-12.
 */

public class Location {
    private int x;
    private int y;
    public Location (int x,int y){
        setX(x);
        setY(y);
    }
    public int getX(){ return x; }
    public int getY(){ return y; }
    public void setX(int x){ this.x=x; }
    public void setY(int y){ this.y=y; }
}
