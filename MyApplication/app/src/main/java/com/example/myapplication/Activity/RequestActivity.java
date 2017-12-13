package com.example.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class RequestActivity extends AppCompatActivity {

    private TextView startTimeTextView;
    private TextView finishTimeTextView;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        startTimeTextView=(TextView)findViewById(R.id.StartTimeText);
        finishTimeTextView=(TextView)findViewById(R.id.ArrTimeText);

        startTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RequestActivity.this,StartTimeSettingActivity.class);
                startActivity(intent);
            }
        });
        finishTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RequestActivity.this, ArrTimeSettingActivity.class);
                startActivity(intent);
            }
        });

        Button requestButton=findViewById(R.id.requestButton);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RequestActivity.this,DeliveryInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
