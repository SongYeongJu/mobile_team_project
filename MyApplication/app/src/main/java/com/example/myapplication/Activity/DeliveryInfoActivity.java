package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Client.Client;
import com.example.myapplication.DataStructure.Duser;
import com.example.myapplication.DataStructure.Item;
import com.example.myapplication.R;

public class DeliveryInfoActivity extends AppCompatActivity {

    private Client client;
    private Item item;
    private Duser duser;

    private TextView itemName;
    private TextView itemInfo;
    private TextView duserName;
    private TextView duserInfo;
    private TextView CompCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_info);

        client=Client.getInstance();

        itemName=(TextView)findViewById(R.id.delItemName);
        itemInfo=(TextView)findViewById(R.id.delItemInfo);
        duserName=(TextView)findViewById(R.id.delDuserName);
        duserInfo=(TextView)findViewById(R.id.delDuserInfo);
        CompCode=(TextView)findViewById(R.id.delCompCode);

        setInfo();

        Button checkButton=findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 전화 걸기로 바꿀까?

                Intent intent=new Intent(DeliveryInfoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    void setInfo(){
        if(item!=null) {
            itemName.setText(item.getName());
            itemInfo.setText(item.returnInfo());
        }
        if(duser!=null){
            duserName.setText(duser.getName());
            duserInfo.setText(duser.returnInfo());
        }
    }
}
