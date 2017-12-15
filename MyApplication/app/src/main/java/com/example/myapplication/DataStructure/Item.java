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
    private int order; // 1 요청      2 매칭 완료    3 인수 완료     4 배달 완료    5 배달 완료 확인
    private String request;
    private int money;

    public static Item returnSempleItem() {
        return new Item("sample", 1, "상", 0, 0, new Date(), 0, 0, new Date(), null,1);
    }

    public Item() {
    }

    public Item(String name, int weight, String size, int dx, int dy, Date dt, int sx, int sy, Date st, String request,int order) {
        setName(name);
        setWeight(weight);
        setSize(size);
        setDes(dx, dy);
        setDt(dt);
        setSta(sx, sy);
        setSt(st);
        setRequest(request);
        setOrder(order);
        setMoney();
    }

    public Item(String name, int weight, String size, Location ld, Date dt, Location ls, Date st, String request, int order) {
        setName(name);
        setWeight(weight);
        setSize(size);
        setDes(ld);
        setDt(dt);
        setSta(ls);
        setSt(st);
        setRequest(request);
        setOrder(order);
        setMoney();
    }

    public void setMoney(){
        int m=0;
        if(size.equals("상")) m+=300;
        if(size.equals("중")) m+=200;
        if(size.equals("하")) m+=100;

        money=m;
    }

    public void setSta(int x, int y) { this.sta = new Location(x, y); }

    public void setDes(int x, int y) { this.des = new Location(x, y); }

    public void setSize(String size) { this.size = size; }

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

    public void setOrder(int order) {
        this.order = order;
    }

    public void setRequest(String request) { this.request = request; }

    public String getRequest() { return request; }

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

    public int getMoney() { return money; }

    public int isOrder() {
        return order;
    }

    public String returnInfo() {
        String s;
        String en = "\n";
        s = "크기 : " + getSize() + en + "무게 : " + getWeight() + en + "배달 시작 시간: " + getSt().toString() + en + "배달 종료 시간 :" + getDt().toString() + en + "요청 사항 : " + getRequest();
        return s;
    }

}
