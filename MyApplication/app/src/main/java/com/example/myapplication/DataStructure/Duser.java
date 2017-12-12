package com.example.myapplication.DataStructure;

/**
 * Created by YoungJu on 2017-12-12.
 */
/*1. 배달원(Duser) : 이름(name), 금액(돈),신뢰도(trust), 위치(location), item
2. User : 이름(name), 금액(돈), item
3. Item : 이름(name), 무게(weight), 도착지(dx, dy), 도착시간(dt), 출발지(sx, sy), 출발시간(st), 접수여부(order)
----------------------db
*/
public class Duser {

    private String name;
    private int money;
    private int trust;
    private Location location;
    private Item item;

    public Duser(){}
    public Duser(String name,int money,int trust,int x,int y,Item item) {
        setName(name);
        setMoney(money);
        setItem(item);
        setLocation(x,y);
        setItem(item);
    }

    public Duser(String name,int money,int trust,Location location,Item item) {
        setName(name);
        setMoney(money);
        setItem(item);
        setLocation(location);
        setItem(item);
    }

    public void setMoney(int money) { this.money = money; }
    public void setTrust(int trust) { this.trust = trust; }
    public void setItem(Item item) { this.item = item; }
    public void setName(String name) { this.name = name; }
    public void setLocation(Location location) { this.location = location; }
    public void setLocation(int x,int y) { location=new Location(x,y); }

    public Location getLocation() { return location; }
    public String getName() { return name; }
    public int getMoney() { return money; }
    public int getTrust() { return trust; }
    public Item getItem() { return item; }

}
