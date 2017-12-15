package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class MainForDmActivity extends AppCompatActivity {

    private Button getRequest;
    private Button mymoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_dm);

        getRequest=(Button)findViewById(R.id.selectItemButton);
        mymoney=(Button)findViewById(R.id.myMoneyButton);

        getRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainForDmActivity.this,SelectItemActivity.class);
                startActivity(intent);
            }
        });

        mymoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainForDmActivity.this,MoneyForDmActivity.class);
                startActivity(intent);
            }
        });



    }

}
