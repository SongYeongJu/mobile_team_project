package com.example.myapplication.DataStructure;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by YoungJu on 2017-12-12.
 */
/*1. 배달원(Duser) : 이름(name), 금액(돈),신뢰도(trust), 위치(location), item
2. User : 이름(name), 금액(돈), item
3. Item : 이름(name), 무게(weight),크기 (size), 도착지(dx, dy), 도착시간(dt), 출발지(sx, sy), 출발시간(st), 접수여부(order)
----------------------db
*/
public class Item implements Serializable {
    private String name;
    private int weight;
    private String size;
    private Location des;
    private Date st;
    private Location sta;
    private Date dt;
    private boolean order;
    private String request;


    public Item() {}

    public Item(String name,int weight,String size,int dx,int dy,Date dt,int sx,int sy,Date st,String request,boolean order){
        setName(name);
        setWeight(weight);
        setSize(size);
        setDes(dx,dy);
        setDt(dt);
        setSta(sx,sy);
        setSt(st);
        setRequest(request);
        setOrder(order);
    }

    public Item(String name,int weight,String size,Location ld,Date dt,Location ls,Date st,String request,boolean order){
        setName(name);
        setWeight(weight);
        setSize(size);
        setDes(ld);
        setDt(dt);
        setSta(ls);
        setSt(st);
        setRequest(request);
        setOrder(order);
    }

    public void setSta(int x,int y) { this.sta=new Location(x,y); }
    public void setDes(int x,int y) { this.des=new Location(x,y); }
    public void setSize(String size) { this.size= size; }
    public void setName(String name) {
        this.name = name;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setDes(Location des) {
        this.des = des;
    }
    public void setSt(Date st) {
        this.st = st;
    }
    public void setSta(Location sta) {
        this.sta = sta;
    }
    public void setDt(Date dt) {
        this.dt = dt;
    }
    public void setOrder(boolean order) {
        this.order = order;
    }
    public void setRequest(String request) {this.request = request;}

    public String getRequest() {return request;}
    public String getName() {
        return name;
    }
    public int getWeight() {
        return weight;
    }
    public Location getDes() {
        return des;
    }
    public Date getSt() {
        return st;
    }
    public Location getSta() {
        return sta;
    }
    public Date getDt() {
        return dt;
    }
    public String getSize() { return size; }

    public boolean isOrder() {
        return order;
    }

}
