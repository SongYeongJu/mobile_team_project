package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeliveryInfoActivity_forDM extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_info_for_dm);
        Button completeButton=findViewById(R.id.CompleteButton) ;
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DeliveryInfoActivity_forDM.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
