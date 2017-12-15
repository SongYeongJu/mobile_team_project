package com.example.myapplication.DataStructure;

import java.io.Serializable;

/**
 * Created by YoungJu on 2017-12-12.
 */
/*1. 배달원(Duser) : 이름(name), 금액(돈),신뢰도(trust), 위치(location), item
2. User : 이름(name), 금액(돈), item
3. Item : 이름(name), 무게(weight), 도착지(dx, dy), 도착시간(dt), 출발지(sx, sy), 출발시간(st), 접수여부(order)
----------------------db
*/
public class User implements Serializable {
    private String id;
    private String pw;

    private String name;
    private String phone;
    private int money;
    private Item item;

    public static User returnSample(){ return new User("","","sample","010-3834-0371",1000,null);}
    public User(){}
    public User(String id,String pw,String name,String phone,int money,Item item){
        setId(id);
        setPw(pw);
        setName(name);
        setPhone(phone);
        setMoney(money);
        setItem(item);
    }

    public void setPhone(String phone){ this.phone=phone; }
    public void setId(String id) { this.id = id; }
    public void setPw(String pw) { this.pw = pw; }
    public void setName(String name) {this.name = name;}
    public void setMoney(int money) {this.money = money;}
    public void setItem(Item item) {this.item = item;}

    public String getPhone(){ return phone; }
    public String getId() { return id; }
    public String getPw() { return pw; }
    public String getName() {return name;}
    public int getMoney() {return money;}
    public Item getItem() {return item;}
}
