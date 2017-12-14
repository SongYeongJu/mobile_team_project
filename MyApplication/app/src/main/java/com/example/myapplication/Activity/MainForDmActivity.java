package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class MainForDmActivity extends AppCompatActivity {

    private Button getRequest;
    private Button requestInfo;
    private Button mymoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_dm);

        getRequest=(Button)findViewById(R.id.selectItemButton);
        requestInfo=(Button)findViewById(R.id.myDelItemButton);
        mymoney=(Button)findViewById(R.id.myMoneyButton);

        getRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainForDmActivity.this,SelectItemActivity.class);
                startActivity(intent);
            }
        });

        requestInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainForDmActivity.this,DeliveryInfoActivity_forDM.class);
                startActivity(intent);
            }
        });

    }

}
