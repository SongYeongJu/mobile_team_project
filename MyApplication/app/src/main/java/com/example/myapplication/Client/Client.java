package com.example.myapplication.Client;

import com.example.myapplication.DataStructure.Duser;
import com.example.myapplication.DataStructure.Item;
import com.example.myapplication.DataStructure.User;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by YoungJu on 2017-12-14.
 */

public class Client {

    private static Client client=null;
    private Socket mSocket;

    private User user;
    private Duser duser;

    public static Client getInstance(){ return client; }

    public boolean isUser(){
        if(user==null && duser!=null) return false;
        else return true;
    }

    public Client(){
        try {
            mSocket = IO.socket("SERVER URL");
            mSocket.connect();
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }
        client=this;
    }
/*
    login -> id%pw
    Dinfo->배달원 structure 보내기
    Uitem->db에 item 등록
    Items->배달원에게 item 정보 제공
    Comp->배달원과 user item 정보 삭제 후 돈 정보 이동
    Refresh -> 배달원의 struc을 주는데 위치가 추가되니 item들에 위치 정보 추가*/

    public boolean Login(String id,String pw){
        return true;
    }
    public void Dinfo(){}
    public void Uitem(Item item){}
    public void Comp(){}
    public void Refresh(){}

}
