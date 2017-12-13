package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Client.Client;
import com.example.myapplication.DataStructure.Item;
import com.example.myapplication.DataStructure.Location;
import com.example.myapplication.R;

import java.util.Date;

public class RequestActivity extends AppCompatActivity {

    private Client client;

    private Intent Sintent;
    private Intent Aintent;

    private TextView startTimeTextView;
    private TextView finishTimeTextView;

    private TextView getNameTextView;
    private TextView getSizeTextView;
    private TextView getWeigthTextView;

    private Date startD;
    private Date arrD;

    private Location startL;
    private Location arrL;

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        client=Client.getInstance();

        startTimeTextView=(TextView)findViewById(R.id.StartTimeText);
        finishTimeTextView=(TextView)findViewById(R.id.ArrTimeText);

        getNameTextView=(TextView)findViewById(R.id.ItemNameText);
        getSizeTextView=(TextView)findViewById(R.id.ItemSizeText);
        getWeigthTextView=(TextView)findViewById(R.id.ItemwText);

        startTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Sintent==null) Sintent=new Intent(RequestActivity.this,StartTimeSettingActivity.class);
                startActivity(Sintent);
            }
        });
        finishTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aintent=new Intent(RequestActivity.this, ArrTimeSettingActivity.class);
                startActivity(Aintent);
            }
        });

        Button requestButton=findViewById(R.id.requestButton);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempSendItem();
            }
        });
    }

    void attempSendItem(){
        arrD=(Date)Aintent.getExtras().getSerializable("arrD");
        startD=(Date)Sintent.getExtras().getSerializable("startD");

        String name=getNameTextView.getText().toString();
        if(name==null || name=="") {
            Toast.makeText(getBaseContext(),"이름은 필수 입력 값입니다.",Toast.LENGTH_SHORT);
            return;
        }

        String size=getSizeTextView.getText().toString();
        if(size=="상" || size=="중" || size =="하") {
        } else {
            Toast.makeText(getBaseContext(), "크기는 필수 선택 값 입니다.", Toast.LENGTH_SHORT);
            return ;
        }

        int weight;
        try{
            String w=getWeigthTextView.getText().toString();
            weight=Integer.parseInt(w);
            if(weight<0) {
                Toast.makeText(getBaseContext(), "무게는 양수만 입력할 수 있습니다.", Toast.LENGTH_SHORT);
                return ;
            }
        }catch (Exception e) {
            Toast.makeText(getBaseContext(), "무게를 kg 단위로 입력해주세요", Toast.LENGTH_SHORT);
            return ;
        }

        if(startD==null){
            Toast.makeText(getBaseContext(),"배송 출발 가능 시간을 설정해주세요.",Toast.LENGTH_SHORT);
            return;
        }
        if(arrD==null){
            Toast.makeText(getBaseContext(),"배송 마감 시간을 설정해주세요.",Toast.LENGTH_SHORT);
            return;
        }

        if(startL==null){
            Toast.makeText(getBaseContext(),"배송 출발지를 설정해주세요.",Toast.LENGTH_SHORT);
            return;
        }
        if(arrL==null){
            Toast.makeText(getBaseContext(),"배송 도착지를 설정해주세요.",Toast.LENGTH_SHORT);
            return;
        }

        TextView requT=(TextView) findViewById(R.id.RequestedText);
        String requ=requT.getText().toString();
        //public Item(String name,int weight,String size,int dx,int dy,Date dt,int sx,int sy,Date st,boolean order)
        item=new Item(name,weight,size,arrL,arrD,startL,startD,requ,false);
        client.Uitem(item);

        Intent intent=new Intent(RequestActivity.this,DeliveryInfoActivity.class);
        startActivity(intent);
        finish();

    }
}
