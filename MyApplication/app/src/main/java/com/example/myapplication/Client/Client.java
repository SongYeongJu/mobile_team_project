package com.example.myapplication.Client;

import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;

import com.example.myapplication.DataStructure.Duser;
import com.example.myapplication.DataStructure.Item;
import com.example.myapplication.DataStructure.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by YoungJu on 2017-12-14.
 */

public class Client {
    private String html = "";
    private Handler mHandler;
    private String name;
    private BufferedReader networkReader;
    private BufferedWriter networkWriter;
    private String ip = "20.20.2.206"; // IP
    private int port = 3001; // PORT번호

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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mHandler = new Handler();
        try {
            setSocket(ip,port);
            Log.d("test","socket create");
        } catch(Exception e) {
            e.printStackTrace();
        }
        client=this;
        checkUpdate.start();
    }
    private Runnable showUpdate = new Runnable() {

        public void run() {
        }

    };

    private Thread checkUpdate = new Thread() {
        public void run() {
            try {
                String line;
                Log.w("ChattingStart", "Start Thread");
                while (true) {
                    Log.w("Chatting is running", "chatting is running");
                    line = networkReader.readLine();
                    html = line;
                    mHandler.post(showUpdate);
                }
            } catch (Exception e) {
            }
        }
    };

    public void connect(){
        mSocket.connect();
        Log.d("test","mSocket Connect");
    }
    public void setSocket(String ip, int port) throws IOException, URISyntaxException {
        try {
            mSocket = IO.socket("http://"+ip+":"+port);
            Log.d("test","mSocket Create");
          //  mSocket.connect();
            Log.d("test","mSocket Connect");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    /*
    login -> id%pw
    Dinfo->배달원 structure 보내기
    Uitem->db에 item 등록
    Items->배달원에게 item 정보 제공
    Comp->배달원과 user item 정보 삭제 후 돈 정보 이동
    Refresh -> 배달원의 struc을 주는데 위치가 추가되니 item들에 위치 정보 추가*/

    public boolean Login(String id,String pw){
//        mSocket.emit("Login","id%pw");//customerLogin
//        mSocket.on("LoginOK",)
        return true;
    }
    public void Dinfo(){

    }
    public void Uitem(Item item){}
    public void Comp(){}
    public void Refresh(){}

}
