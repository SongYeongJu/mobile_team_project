package com.example.myapplication.Client;

import android.os.Handler;
import android.util.Log;

import com.example.myapplication.DataStructure.Duser;
import com.example.myapplication.DataStructure.Item;
import com.example.myapplication.DataStructure.Location;
import com.example.myapplication.DataStructure.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by YoungJu on 2017-12-14.
 */

public class Client {
    private String html = "";
    private Handler mHandler;
    private String name;
    private BufferedReader networkReader;
    private BufferedWriter networkWriter;
    private String ip = "210.89.176.134"; // IP
    private int port = 6000; // PORT번호

    private static Client client = null;
    private Socket mSocket;

    private ArrayList<Item> items;
    private Item item;
    private User user;
    private Duser duser;

    private boolean isDuser;

    private String id;
    private boolean wait = false;
    private boolean login = false;
    private int event;
    private int sum;

    public static Client getInstance() {
        return client;
    }

    public int getEvent() {
        return event;
    }

    public boolean isUser() {
        if (isDuser) return false;
        else return true;
    }

    public Client() {
        try {
            setSocket(ip, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        connect();
        client = this;
        items = new ArrayList<Item>();
    }

    public void connect() {
        mSocket.connect();
        Log.d("test", "mSocket Connect");
    }

    public void setSocket(String ip, int port) throws IOException, URISyntaxException {
        try {
            mSocket = IO.socket("http://" + ip + ":" + port);
            Log.d("test", "mSocket :" + mSocket.toString());
            Log.d("test", "mSocket Create" + " http://" + ip + ":" + port);
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

    public boolean Login(String id, String pw, boolean IsDuser) {

        Log.d("test", "client login start");
        isDuser = IsDuser;
        this.id = id;
        if (IsDuser) { //딜리버리맨 로그인
            final JSONObject jsonRecvData = new JSONObject();
            JSONObject jsonLogin = new JSONObject();
            try {
                jsonLogin.put("id", id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonLogin.put("password", pw);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            wait = true;

            mSocket.emit("deliverymanLogin", jsonLogin);
            Emitter.Listener state0Delivery = new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    wait = false;
                    login = true;
                    event = 0;
                    Log.d("test", "client login emitter listener-deliverymanState0");
                }
            };
            mSocket.on("deliverymanState0", state0Delivery);

            Emitter.Listener state1Delivery = new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    wait = false;
                    login = true;
                    event = 0;
                    Log.d("test", "client login emitter listener-deliverymanState1");
                    for (int i = 0; i < args.length; i++) {
                        JSONArray argv = (JSONArray) args[i];
                        try {
                            String start = (String) argv.getJSONObject(i).getString("starting_point");
                            String[] sarr = start.split("/");
                            double sx = Double.parseDouble(sarr[0]);
                            double sy = Double.parseDouble(sarr[1]);
                            String des = (String) argv.getJSONObject(i).getString("destination");
                            String[] darr = start.split("/");
                            double dx = Double.parseDouble(darr[0]);
                            double dy = Double.parseDouble(darr[1]);
                            items.add(new Item((String) argv.getJSONObject(i).getString("name"), argv.getJSONObject(i).getInt("weight"), (String) argv.getJSONObject(i).getString("volume"), dx, dy, null, sx, sy, null, (String) argv.getJSONObject(0).getString("customer_request"), argv.getJSONObject(i).getInt("delivery_state")));
                            Log.d("test", "get Item:" + items.get(i).getName() + " " + items.get(i).returnInfo());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            mSocket.on("deliverymanState1", state1Delivery);


            Emitter.Listener state2Delivery = new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    wait = false;
                    login = true;
                    event = 2;
                    Log.d("test", "client login emitter listener-deliverymanState2");
                    JSONArray argv = (JSONArray) args[0];
                    try {
                        String start = (String) argv.getJSONObject(0).getString("starting_point");
                        String[] sarr = start.split("/");
                        double sx = Double.parseDouble(sarr[0]);
                        double sy = Double.parseDouble(sarr[1]);
                        String des = (String) argv.getJSONObject(0).getString("destination");
                        String[] darr = start.split("/");
                        double dx = Double.parseDouble(darr[0]);
                        double dy = Double.parseDouble(darr[1]);
                        item = new Item((String) argv.getJSONObject(0).getString("name"), argv.getJSONObject(0).getInt("weight"), (String) argv.getJSONObject(0).getString("volume"), dx, dy, null, sx, sy, null, (String) argv.getJSONObject(0).getString("customer_request"), argv.getJSONObject(0).getInt("delivery_state"));
                        Log.d("test", "get Item:" + item.getName() + " " + item.returnInfo());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("test", "client login emitter listener-deliverymanState2 end");
                }
            };
            mSocket.on("deliverymanState2", state2Delivery);


            Emitter.Listener state3Delivery = new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    //args[0]이 전체 데이터를 품은 json 객체
                    wait = false;
                    login = true;
                    event = 3;
                    Log.d("test", "client login emitter listener-deliverymanState3");
                    JSONArray argv = (JSONArray) args[0];
                    try {
                        String start = (String) argv.getJSONObject(0).getString("starting_point");
                        String[] sarr = start.split("/");
                        double sx = Double.parseDouble(sarr[0]);
                        double sy = Double.parseDouble(sarr[1]);
                        String des = (String) argv.getJSONObject(0).getString("destination");
                        String[] darr = start.split("/");
                        double dx = Double.parseDouble(darr[0]);
                        double dy = Double.parseDouble(darr[1]);
                        item = new Item((String) argv.getJSONObject(0).getString("name"), argv.getJSONObject(0).getInt("weight"), (String) argv.getJSONObject(0).getString("volume"), dx, dy, null, sx, sy, null, (String) argv.getJSONObject(0).getString("customer_request"), argv.getJSONObject(0).getInt("delivery_state"));
                        Log.d("test", "get Item:" + item.getName() + " " + item.returnInfo());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("test", "client login emitter listener-deliverymanState3 end");
                }
            };
            mSocket.on("deliverymanState3", state3Delivery);


            Emitter.Listener state4Delivery = new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    //args[0]이 전체 데이터를 품은 json 객체
                    wait = false;
                    login = true;
                    event = 4;
                    Log.d("test", "client login emitter listener-deliverymanState4");

                }
            };
            mSocket.on("deliverymanState4", state4Delivery);

            Emitter.Listener loginFail = new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    //method 작성
                    wait = false;
                    Log.d("test", "client login emitter listener-login fail");

                }
            };
            mSocket.on("deliverymanLoginNO", loginFail);


        } else { // 유저 로그인
            JSONObject jsonLogin = new JSONObject();
            try {
                jsonLogin.put("id", id);
                Log.d("test", "client login put id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonLogin.put("password", pw);
                Log.d("test", "client login put pw");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            wait = true;
            Log.d("test", "wait :" + wait);

            mSocket.emit("customerLogin", jsonLogin);

            Emitter.Listener state0Delivery = new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    wait = false;
                    login = true;
                    event = 0;
                    Log.d("test", "client login emitter listener-customerState0");
                }
            };
            mSocket.on("customerState0", state0Delivery);


            Emitter.Listener state1Delivery = new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    wait = false;
                    login = true;
                    event = 1;
                    //    public Item(String name, int weight, String size, int dx, int dy, Date dt, int sx, int sy, Date st, String request,int order) {
//                    item=new Item((JSONObject)args[0].name);
                    JSONArray argv = (JSONArray) args[0];
                    try {
                        String start = (String) argv.getJSONObject(0).getString("starting_point");
                        String[] sarr = start.split("/");
                        double sx = Double.parseDouble(sarr[0]);
                        double sy = Double.parseDouble(sarr[1]);
                        String des = (String) argv.getJSONObject(0).getString("destination");
                        String[] darr = start.split("/");
                        double dx = Double.parseDouble(darr[0]);
                        double dy = Double.parseDouble(darr[1]);
                        item = new Item((String) argv.getJSONObject(0).getString("name"), argv.getJSONObject(0).getInt("weight"), (String) argv.getJSONObject(0).getString("volume"), dx, dy, null, sx, sy, null, (String) argv.getJSONObject(0).getString("customer_request"), argv.getJSONObject(0).getInt("delivery_state"));
                        Log.d("test", "get Item:" + item.getName() + " " + item.returnInfo());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("test", "client login emitter listener-customerState1");
                }
            };
            mSocket.on("customerState1", state1Delivery);


            Emitter.Listener state2Delivery = new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    //args[0]이 전체 데이터를 품은 json 객체
                    wait = false;
                    login = true;
                    event = 2;
                    Log.d("test", "client login emitter listener-customerState2");
                    JSONArray argv = (JSONArray) args[0];
                    try {
                        Log.d("test", "args[0]:" + argv.toString());

/*                        if (argv.getJSONObject(0).getString("location").length()>0) {
                            String loc = argv.getJSONObject(0).getString("location");
                            String[] larr = loc.split("/");
                           double lx = Double.parseDouble(larr[0]);
                            double ly = Double.parseDouble(larr[1]);
                           duser = new Duser((String) argv.getJSONObject(0).getString("id"), argv.getJSONObject(0).getString("password"), argv.getJSONObject(0).getString("name"), argv.getJSONObject(0).getString("phone"), argv.getJSONObject(0).getInt("money"), argv.getJSONObject(0).getInt("trust"), lx, ly, null);
                        } else
                           */
                        duser = new Duser((String) argv.getJSONObject(0).getString("id"), argv.getJSONObject(0).getString("password"), argv.getJSONObject(0).getString("name"), argv.getJSONObject(0).getString("phone"), argv.getJSONObject(0).getInt("money"), argv.getJSONObject(0).getInt("trust"), 0, 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            mSocket.on("customerState2", state2Delivery);


            Emitter.Listener state3Delivery = new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    //args[0]이 전체 데이터를 품은 json 객체
                    wait = false;
                    login = true;
                    event = 3;
                    Log.d("test", "event 3 - get args : " + args[0].toString());
                    JSONArray argv = (JSONArray) args[0];
                    try {
                        String start = (String) argv.getJSONObject(0).getString("starting_point");
                        String[] sarr = start.split("/");
                        double sx = Double.parseDouble(sarr[0]);
                        double sy = Double.parseDouble(sarr[1]);
                        String des = (String) argv.getJSONObject(0).getString("destination");
                        String[] darr = start.split("/");
                        double dx = Double.parseDouble(darr[0]);
                        double dy = Double.parseDouble(darr[1]);
                        item = new Item((String) argv.getJSONObject(0).getString("name"), (int) argv.getJSONObject(0).getInt("weight"), (String) argv.getJSONObject(0).getString("volume"), dx, dy, null, sx, sy, null, (String) argv.getJSONObject(0).getString("customer_request"), argv.getJSONObject(0).getInt("delivery_state"));
                        Log.d("test", "get Item:" + item.getName() + " " + item.returnInfo());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        String loc = argv.getJSONObject(0).getString("location");
                        String[] larr = loc.split("/");
                        double lx = Double.parseDouble(larr[0]);
                        double ly = Double.parseDouble(larr[1]);
                        duser = new Duser((String) argv.getJSONObject(0).getString("id"), argv.getJSONObject(0).getString("password"), argv.getJSONObject(0).getString("name"), argv.getJSONObject(0).getString("phone"), argv.getJSONObject(0).getInt("money"), argv.getJSONObject(0).getInt("trust"), lx, ly, null);
                    } catch (Exception e) {
                    }
                    Log.d("test", "client login emitter listener-customerState3");
                    Log.d("test", "args: " + args[0].toString());
                }
            };
            mSocket.on("customerState3", state3Delivery);
            Emitter.Listener state4Delivery = new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    //args[0]이 전체 데이터를 품은 json 객체
                    wait = false;
                    login = true;
                    event = 4;
                    Log.d("test", "client login emitter listener-customerState4");
                }
            };
            mSocket.on("customerState4", state4Delivery);
            Emitter.Listener loginFail = new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    wait = false;
                    Log.d("test", "client login emitter listener-login fail");
                }
            };
            mSocket.on("customerLoginNO", loginFail);
        }
        Log.d("test", "start wait:" + wait);
        while (wait) ;

        Log.d("test", "login return : " + login);
        return login;
    }

    public int Deposit(int cost) {
        JSONObject jsonCost = new JSONObject();
        try {
            jsonCost.put("id", id);
            jsonCost.put("cost", cost);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit("customerDeposit", jsonCost);
        Emitter.Listener depositMoney = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONArray arg = (JSONArray) args[0];
                try {
                    Log.d("test","deposit args"+args[0].toString());
                    sum = arg.getJSONObject(0).getInt("sum");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("test", "client login emitter listener-deliverymanState0");
                wait = false;
            }
        };
        mSocket.on("customerDeposit", depositMoney);
        while (wait) ;
        return sum;
    }

    public void Dinfo() {

    }

    public void Uitem(Item item) {
        JSONObject jsonItem = new JSONObject();
        try {
            jsonItem.put("name", item.getName());
            jsonItem.put("volum", item.getSize());
            jsonItem.put("starttime", item.getSt());
            jsonItem.put("deadline", item.getDt());
            jsonItem.put("weight", item.getWeight());
            jsonItem.put("destination", item.getDes());
            jsonItem.put("starting_point", item.getSta());
            jsonItem.put("customer_request", item.getRequest());
            jsonItem.put("customer_id", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mSocket.emit("requestDelivery", jsonItem);
    }

    public void AcceptDelivery(Item item) {
        JSONObject jsonItemInfo = new JSONObject();
        try {
            jsonItemInfo.put("deliveryman_id", id);//deliveryman id
            jsonItemInfo.put("delivery_id", item.getName());//item 이름
        } catch (Exception e) {
        }
        mSocket.emit("acceptDelivery", jsonItemInfo);
    }

    public void upDateLocation(double x, double y) {
        JSONObject jsonLocation = new JSONObject();
        try {
            jsonLocation.put("x", x);
            jsonLocation.put("y", y);
            jsonLocation.put("id", id);//delivery user id
        } catch (Exception e) {
        }
        mSocket.emit("updateLocation", jsonLocation);
    }

    public Location getDmLocation() {
        final JSONObject jsonDLocation = new JSONObject();
        JSONObject jsonID = new JSONObject();
        wait = true;
        try {
            jsonID.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit("requestDeliverymanLocation", jsonID);
        mSocket.on("requestDeliverymanLocation", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONArray argv = (JSONArray) args[0];
                double locationX = 0;
                double locationY = 0;
                try {
                    locationX = argv.getJSONObject(0).getDouble("x");
                    locationY = argv.getJSONObject(0).getDouble("y");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (duser != null) {
                    duser.setLocation(new Location(locationX, locationY));
                    wait = false;
                } else {
                    duser = new Duser();
                    duser.setLocation(new Location(locationX, locationY));
                    wait = false;
                }
            }
        });
        while (wait) ;
        return duser.getLocation();
    }

    public User getCustomer() {
        JSONObject jsonID4 = new JSONObject();
        try {
            jsonID4.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("test","getCustomer + id:"+id);
        wait=true;
        mSocket.emit("getCustomer", jsonID4);//배달원 id
        mSocket.on("getCustomer", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONArray arg = (JSONArray) args[0];
                Log.d("test","getCustomer args+ "+args[0].toString());
                //public User(String id,String pw,String name,String phone,int money,Item item){
                try {
                    user = new User(arg.getJSONObject(0).getString("id"), arg.getJSONObject(0).getString("password"), arg.getJSONObject(0).getString("name"), arg.getJSONObject(0).getString("phone"), arg.getJSONObject(0).getInt("money"), null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(user==null) {
                    Log.d("test","getCustomer user==null");
                } else{
                    Log.d("test","getCustomer user!=null");
                }
                wait=false;
            }
        });
        while(wait);
        return user;
    }

    public void startDelivery() {
        JSONObject jsonID1 = new JSONObject();
        try {
            jsonID1.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit("startDelivery", jsonID1);//배달원 id
    }

    public void RequestComp() {
        JSONObject jsonID2 = new JSONObject();
        try {
            jsonID2.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit("requestComp", jsonID2);//배달원 id
    }

    public void AcceptComp() {
        JSONObject jsonID3 = new JSONObject();
        try {
            jsonID3.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit("acceptComp", jsonID3);
    }

    public void Refresh() {
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNetworkReader(BufferedReader networkReader) {
        this.networkReader = networkReader;
    }

    public void setNetworkWriter(BufferedWriter networkWriter) {
        this.networkWriter = networkWriter;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static void setClient(Client client) {
        Client.client = client;
    }

    public void setmSocket(Socket mSocket) {
        this.mSocket = mSocket;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDuser(Duser duser) {
        this.duser = duser;
    }

    public void setDuser(boolean duser) {
        isDuser = duser;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public String getHtml() {
        return html;
    }

    public Handler getmHandler() {
        return mHandler;
    }

    public String getName() {
        return name;
    }

    public BufferedReader getNetworkReader() {
        return networkReader;
    }

    public BufferedWriter getNetworkWriter() {
        return networkWriter;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public static Client getClient() {
        return client;
    }

    public Socket getmSocket() {
        return mSocket;
    }

    public Item getItem() {
        return item;
    }

    public User getUser() {
        return user;
    }

    public Duser getDuser() {
        return duser;
    }

    public boolean isDuser() {
        return isDuser;
    }

    public boolean isWait() {
        return wait;
    }

    public boolean isLogin() {
        return login;
    }
}
