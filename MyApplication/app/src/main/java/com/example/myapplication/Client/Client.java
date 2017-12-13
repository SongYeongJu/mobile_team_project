package com.example.myapplication.Client;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by YoungJu on 2017-12-14.
 */

public class Client {

    private static Client client=null;
    private Socket mSocket;

    public static Client getInstance(){ return client; }
    public Client(){
        try {
            mSocket = IO.socket("SERVER URL");
            mSocket.connect();
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }
        client=this;
    }
}
